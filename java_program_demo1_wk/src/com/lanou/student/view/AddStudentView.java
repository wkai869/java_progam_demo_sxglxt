package com.lanou.student.view;

import com.lanou.entity.StudentDO;
import com.lanou.handler.AddStudentViewHandler;

import javax.swing.*;
import java.awt.*;

public class AddStudentView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel nameLabel = new JLabel("姓名:",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号:",JLabel.RIGHT);
    JTextField noTxt = new JTextField();
    JLabel homeLabel = new JLabel("家乡:",JLabel.RIGHT);
    JTextField homeTxt = new JTextField();
    JLabel cnLabel = new JLabel("语文成绩:",JLabel.RIGHT);
    JTextField cnTxt = new JTextField();
    JLabel enLabel = new JLabel("英语成绩:",JLabel.RIGHT);
    JTextField enTxt = new JTextField();
    JLabel mathLabel = new JLabel("数学成绩:",JLabel.RIGHT);
    JTextField mathTxt = new JTextField();

    JButton addBtn = new JButton("添加");

    AddStudentViewHandler addStudentViewHandler;

    public AddStudentView(MainView mainView) {
        super(mainView, "添加学生", true);

        addStudentViewHandler = new AddStudentViewHandler(this,mainView);

        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);

        nameTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameTxt);

        noLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(noLabel);

        noTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(noTxt);

        homeLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(homeLabel);

        homeTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(homeTxt);

        cnLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(cnLabel);

        cnTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(cnTxt);

        enLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(enLabel);

        enTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(enTxt);

        mathLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(mathLabel);

        mathTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(mathTxt);

        addBtn.addActionListener(addStudentViewHandler);
        jPanel.add(addBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350, 500);
        setLocationRelativeTo(null);
        //DISPOSE_ON_CLOSE:只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    //返回StudentDO对象
    public StudentDO  buildStudentDO(){
        StudentDO studentDO = new StudentDO();
        studentDO.setName(nameTxt.getText());
        studentDO.setNo(noTxt.getText());
        studentDO.setHomeTown(homeTxt.getText());
        studentDO.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDO.setEnScore(Double.valueOf(enTxt.getText()));
        studentDO.setMathScore(Double.valueOf(mathTxt.getText()));
        return studentDO;
    }
}
