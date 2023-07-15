package com.lanou.handler;

import com.lanou.entity.AdminDO;
import com.lanou.service.AdminService;
import com.lanou.service.impl.AdminServiceImpl;
import com.lanou.student.view.LoginView;
import com.lanou.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * 处理点击事件与按键时事件
 */
public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("重置".equals(text)) {
            loginView.getUserField().setText("");
            loginView.getPwdField().setText("");
        } else if ("登录".equals(text)) {
            login();
        }
    }


    //按键监听
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (KeyEvent.VK_ENTER == keyCode) {
            login();
        }
    }

    private void login() {
        String user = loginView.getUserField().getText();
        if (user == null || "".equals(user.trim())) {
            JOptionPane.showMessageDialog(loginView, "请输入用户名");
            return;
        }
        char[] chars = loginView.getPwdField().getPassword();
        if (chars == null || chars.length == 0) {
            JOptionPane.showMessageDialog(loginView, "请输入密码");
            return;
        }
        String pwd = new String(chars);
        System.out.println(user + ":" + pwd);
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPassword(pwd);
        //DONE 一、验证用户是否登录成功
        boolean flag = adminService.login(adminDO);
        if (flag) {
            new MainView();
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "用户名或密码错误");
        }
    }
}
