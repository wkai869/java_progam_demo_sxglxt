package com.lanou.handler;

import com.lanou.service.StudentService;
import com.lanou.service.impl.StudentServiceImpl;
import com.lanou.student.view.AddStudentView;
import com.lanou.student.view.MainView;
import com.lanou.student.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 处理点击事件与按键时事件
 */
public class MainViewHandler implements ActionListener {

    private final MainView mainView;

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("增加".equals(text)) {
            new AddStudentView(mainView);
        } else if ("修改".equals(text)) {
            int[] selectedStudentIds = mainView.getSelectedStudentIds();
            if (selectedStudentIds.length != 1) {
                JOptionPane.showMessageDialog(mainView, "一次只能修改一行！");
                return;
            }
            new UpdateStudentView(mainView, selectedStudentIds[0]);
        } else if ("删除".equals(text)) {
            int[] selectedStudentIds = mainView.getSelectedStudentIds();
            if (selectedStudentIds.length == 0) {
                JOptionPane.showMessageDialog(mainView, "请选择要删除的行！");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView, "您确认要删除选择的" + selectedStudentIds.length + "行吗？ ", "确认删除", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                StudentService studentService = new StudentServiceImpl();
                //TODO 六、根据ID数组，删除多条数据
                boolean deleteResult =  studentService.delStudentsByIds(selectedStudentIds);
                if (deleteResult) {
                    //删除成功，重新刷新表格数据
                    mainView.reloadTable();
                } else {
                    JOptionPane.showMessageDialog(mainView, "删除失败");
                }
            }
        } else if ("查询".equals(text)) {
            mainView.setPageNow(1);
            mainView.reloadTable();
        } else if ("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() - 1);

            mainView.reloadTable();
        } else if ("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() + 1);

            mainView.reloadTable();
        }
    }
}
