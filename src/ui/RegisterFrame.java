package ui;

import dao.UserDao;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    // 定义组件
    private JLabel usernameLabel; // 用户名标签
    private JLabel passwordLabel; // 密码标签
    private JLabel nameLabel; // 姓名标签
    private JTextField usernameField; // 用户名文本框
    private JPasswordField passwordField; // 密码文本框
    private JTextField nameField; // 姓名文本框
    private JButton registerButton; // 注册按钮
    private JButton cancelButton; // 取消按钮

    public RegisterFrame() {
        // 设置窗体标题
        this.setTitle("图书管理系统-注册");
        // 设置窗体大小
        this.setSize(300, 250);
        // 设置窗体居中显示
        this.setLocationRelativeTo(null);
        // 设置窗体关闭时不退出程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 设置窗体不可调整大小
        this.setResizable(false);

        // 初始化组件
        usernameLabel = new JLabel("用户名：");
        passwordLabel = new JLabel("密码：");
        nameLabel = new JLabel("姓名：");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        nameField = new JTextField(20);
        registerButton = new JButton("注册");
        cancelButton = new JButton("取消");

        // 创建一个网格布局管理器，5行2列，水平间距10，垂直间距10
        GridLayout gridLayout = new GridLayout(5, 2, 10, 10);
        // 设置窗体的布局为网格布局
        this.setLayout(gridLayout);

        // 将组件添加到窗体中
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(nameLabel);
        this.add(nameField);
        this.add(registerButton);
        this.add(cancelButton);

        // 为注册按钮添加事件监听器
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名、密码和姓名
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                // 判断用户名、密码和姓名是否为空
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空！");
                    return;
                }
                if (password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "密码不能为空！");
                    return;
                }
                if (name == null || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空！");
                    return;
                }
                // 创建一个User对象，设置其属性
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setName(name);
                // 调用UserDao的registerUser方法，返回一个布尔值
                UserDao userDao = new UserDao();
                boolean result = userDao.registerUser(user);
                // 判断结果是否为真，如果为真，说明注册成功，否则说明注册失败
                if (result) {
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    // 关闭注册界面
                    RegisterFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "注册失败！");
                }
            }
        });
// 为取消按钮添加事件监听器
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭注册界面
                RegisterFrame.this.dispose();
            }
        });
    }
    }