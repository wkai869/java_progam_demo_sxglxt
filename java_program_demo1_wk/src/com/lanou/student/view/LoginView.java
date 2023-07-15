package com.lanou.student.view;

import com.lanou.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("蓝鸥学生管理系统", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel usernameLabel = new JLabel("用户名:");
    JTextField userField = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");

    TrayIcon trayIcon;

    SystemTray systemTray;

    LoginHandler loginhandler;

    public LoginView() {
        setTitle("蓝鸥学生管理系统");

        loginhandler = new LoginHandler(this);

        Container contentPane = getContentPane();

        nameLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        nameLabel.setPreferredSize(new Dimension(0, 80));


        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        usernameLabel.setFont(centerFont);
        userField.setFont(centerFont);
        userField.setPreferredSize(new Dimension(200, 30));
        pwdLabel.setFont(centerFont);
        pwdField.setFont(centerFont);
        pwdField.setEchoChar('*');
        pwdField.setPreferredSize(new Dimension(200, 30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);

        //把组件加入面板
        centerPanel.add(usernameLabel);
        centerPanel.add(userField);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(loginBtn);
        loginBtn.addActionListener(loginhandler);
        //增加按键事件
        loginBtn.addKeyListener(loginhandler);

        centerPanel.add(resetBtn);
        resetBtn.addActionListener(loginhandler);

        //弹簧布局
        layoutCenter();

        contentPane.add(nameLabel, BorderLayout.NORTH);

        contentPane.add(centerPanel, BorderLayout.CENTER);

        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(new ImageIcon(Objects.requireNonNull(LoginView.class.getClassLoader().getResource("lanou.png"))).getImage());
            //设置图标自动缩放
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            //托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1) {
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }
        //设置loginBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);

        //给窗设置图标
        Image image = new ImageIcon(Objects.requireNonNull(LoginView.class.getClassLoader().getResource("lanou.png"))).getImage();
        setIconImage(image);

        //设置默认账号密码
        userField.setText("admin");
        pwdField.setText("123456");

        //调节窗口大小
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void layoutCenter() {
        /**
         * 设置约束的第二种写法，相对简单
         * e1: 要设置组件的那个边界
         * C1 ：要设置的组件
         * pad：距离值
         * e2：参照的组件的边界名
         * c2：参照的组件
         */
        //弹簧布局
        //布局usernameLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(userField)), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);

        //布局 userField
        springLayout.putConstraint(SpringLayout.WEST, userField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userField, 0, SpringLayout.NORTH, usernameLabel);

        //布局 pwdLabel
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, usernameLabel);

        //布局 pwdField
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);

        //布局 loginBtn
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 0, SpringLayout.WEST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 30, SpringLayout.SOUTH, pwdField);

        //布局 resetBtn
        springLayout.putConstraint(SpringLayout.EAST, resetBtn, 0, SpringLayout.EAST, pwdField);
        springLayout.putConstraint(SpringLayout.NORTH, resetBtn, 30, SpringLayout.SOUTH, pwdField);
    }

    public JTextField getUserField() {
        return userField;
    }

    public void setUserField(JTextField userField) {
        this.userField = userField;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

    public void setPwdField(JPasswordField pwdField) {
        this.pwdField = pwdField;
    }
}
