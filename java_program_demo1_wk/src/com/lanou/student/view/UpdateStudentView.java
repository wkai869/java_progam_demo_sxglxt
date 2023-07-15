package com.lanou.student.view;

import com.lanou.entity.StudentDO;
import com.lanou.handler.UpdateStudentViewHandler;
import com.lanou.service.StudentService;
import com.lanou.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel idLabel = new JLabel("学生编号:", JLabel.RIGHT);
    JTextField idTxt = new JTextField();
    JLabel nameLabel = new JLabel("姓名:", JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号:", JLabel.RIGHT);
    JTextField noTxt = new JTextField();
    JLabel homeLabel = new JLabel("家乡:", JLabel.RIGHT);
    JTextField homeTxt = new JTextField();
    JLabel cnLabel = new JLabel("语文成绩:", JLabel.RIGHT);
    JTextField cnTxt = new JTextField();
    JLabel enLabel = new JLabel("英语成绩:", JLabel.RIGHT);
    JTextField enTxt = new JTextField();
    JLabel mathLabel = new JLabel("数学成绩:", JLabel.RIGHT);
    JTextField mathTxt = new JTextField();

    JButton updateBtn = new JButton("修改");

    UpdateStudentViewHandler updateStudentViewHandler;

    public UpdateStudentView(MainView mainView, int selectedStudentId) {
        super(mainView, "修改学生", true);

        updateStudentViewHandler = new UpdateStudentViewHandler(this,mainView);
        //查询selectedStudentId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        //done 四、根据ID查询待修改的学生信息
        StudentDO studentDO = studentService.searchStudentById(selectedStudentId);
        //StudentDO studentDO = new StudentDO();
        idLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idLabel);

        idTxt.setPreferredSize(new Dimension(200, 30));
        idTxt.setText(studentDO.getId() + "");
        idTxt.setEnabled(false);
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);

        nameTxt.setPreferredSize(new Dimension(200, 30));
        nameTxt.setText(studentDO.getName());
        jPanel.add(nameTxt);

        noLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(noLabel);

        noTxt.setPreferredSize(new Dimension(200, 30));
        noTxt.setText(studentDO.getNo());
        jPanel.add(noTxt);

        homeLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(homeLabel);

        homeTxt.setPreferredSize(new Dimension(200, 30));
        homeTxt.setText(studentDO.getHomeTown());
        jPanel.add(homeTxt);

        cnLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(cnLabel);

        cnTxt.setPreferredSize(new Dimension(200, 30));
        cnTxt.setText(String.valueOf(studentDO.getCnScore()));
        jPanel.add(cnTxt);

        enLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(enLabel);

        enTxt.setPreferredSize(new Dimension(200, 30));
        enTxt.setText(String.valueOf(studentDO.getEnScore()));
        jPanel.add(enTxt);

        mathLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(mathLabel);

        mathTxt.setPreferredSize(new Dimension(200, 30));
        mathTxt.setText(String.valueOf(studentDO.getMathScore()));
        jPanel.add(mathTxt);

        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350, 500);
        setLocationRelativeTo(null);
        //DISPOSE_ON_CLOSE:只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public StudentDO buildUpdateStudentDO() {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(Integer.valueOf(idTxt.getText()));
        studentDO.setName(nameTxt.getText());
        studentDO.setNo(noTxt.getText());
        studentDO.setHomeTown(homeTxt.getText());
        studentDO.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDO.setEnScore(Double.valueOf(enTxt.getText()));
        studentDO.setMathScore(Double.valueOf(mathTxt.getText()));
        return studentDO;
    }
}
