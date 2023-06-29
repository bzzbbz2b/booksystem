package ui;

import dao.UserDao;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    // 定义组件
    private JLabel usernameLabel; // 用户名标签
    private JLabel passwordLabel; // 密码标签
    private JTextField usernameField; // 用户名文本框
    private JPasswordField passwordField; // 密码文本框
    private JButton loginButton; // 登录按钮
    private JButton registerButton; // 注册按钮

    public LoginFrame() {
        // 设置窗体标题
        this.setTitle("图书管理系统");
        // 设置窗体大小
        this.setSize(300, 200);
        // 设置窗体居中显示
        this.setLocationRelativeTo(null);
        // 设置窗体关闭时退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体不可调整大小
        this.setResizable(false);

        // 初始化组件
        usernameLabel = new JLabel("用户名：");
        passwordLabel = new JLabel("密码：");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        // 创建一个网格布局管理器，4行2列，水平间距10，垂直间距10
        GridLayout gridLayout = new GridLayout(4, 2, 10, 10);
        // 设置窗体的布局为网格布局
        this.setLayout(gridLayout);

        // 将组件添加到窗体中
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(registerButton);

        // 为登录按钮添加事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名和密码
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // 判断用户名和密码是否为空
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空！");
                    return;
                }
                if (password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "密码不能为空！");
                    return;
                }
                // 调用UserDao的loginUser方法，返回一个User对象
                UserDao userDao = new UserDao();
                User user = userDao.loginUser(username, password);
                // 判断User对象是否为空，如果为空，说明用户名或密码错误，否则登录成功
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    // 关闭登录界面
                    LoginFrame.this.dispose();
                    // 判断用户是否是管理员，如果是管理员，打开管理员界面，否则打开用户界面
                    if (user.getUsername().equals("admin")) {
                        new AdminFrame(user);
                    } else {
                        new UserFrame(user);
                    }
                }
            }
        });

        // 为注册按钮添加事件监听器
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开一个注册界面
                new RegisterFrame();
            }
        });
    }
    }