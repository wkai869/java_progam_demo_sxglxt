package com.lanou.handler;

import com.lanou.entity.StudentDO;
import com.lanou.service.StudentService;
import com.lanou.service.impl.StudentServiceImpl;
import com.lanou.student.view.MainView;
import com.lanou.student.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 处理点击事件与按键时事件
 */
public class UpdateStudentViewHandler implements ActionListener {

    private final UpdateStudentView updateStudentView;
    private final MainView mainView;

    public UpdateStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView) {
        this.updateStudentView = updateStudentView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("修改".equals(text)) {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = updateStudentView.buildUpdateStudentDO();
            //DONE 五、修改学生信息到数据库
            boolean updateResult = studentService.updateStudent(studentDO);
            if (updateResult) {
                //修改成功，重新刷新表格数据
                mainView.reloadTable();
                updateStudentView.dispose();
            } else {
                JOptionPane.showMessageDialog(updateStudentView, "修改失败");
            }
        }
    }
}
