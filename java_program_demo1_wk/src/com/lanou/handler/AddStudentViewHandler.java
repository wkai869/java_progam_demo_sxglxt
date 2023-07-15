package com.lanou.handler;

import com.lanou.entity.StudentDO;
import com.lanou.service.StudentService;
import com.lanou.service.impl.StudentServiceImpl;
import com.lanou.student.view.AddStudentView;
import com.lanou.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 处理点击事件与按键时事件
 */
public class AddStudentViewHandler implements ActionListener {

    private final AddStudentView addStudentView;
    private final MainView mainView;

    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView) {
        this.addStudentView = addStudentView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("添加".equals(text)) {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = addStudentView.buildStudentDO();
            //DONE 三、添加学生信息
            boolean addResult = studentService.add(studentDO);
            if (addResult) {
                //添加成功，重新刷新表格数据
                mainView.reloadTable();
                addStudentView.dispose();
            } else {
                JOptionPane.showMessageDialog(addStudentView, "添加失败");
            }
        }
    }
}
