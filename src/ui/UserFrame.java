package ui;

import dao.BookDao;
import dao.BorrowDao;
import entity.Book;
import entity.Borrow;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserFrame extends JFrame {
    // 定义组件
    private JLabel welcomeLabel; // 欢迎标签
    private JButton logoutButton; // 注销按钮
    private JLabel titleLabel; // 书名标签
    private JLabel authorLabel; // 作者标签
    private JTextField titleField; // 书名文本框
    private JTextField authorField; // 作者文本框
    private JButton searchButton; // 搜索按钮
    private JTable bookTable; // 图书表格
    private JScrollPane bookScrollPane; // 图书滚动面板
    private JButton borrowButton; // 借阅按钮
    private JButton returnButton; // 归还按钮
    private JLabel borrowLabel; // 借阅信息标签

    public UserFrame(User user) {
        // 设置窗体标题
        this.setTitle("图书管理系统-用户");
        // 设置窗体大小
        this.setSize(800, 600);
        // 设置窗体居中显示
        this.setLocationRelativeTo(null);
        // 设置窗体关闭时退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体不可调整大小
        this.setResizable(false);

        // 初始化组件
        welcomeLabel = new JLabel("欢迎您，" + user.getName());
        logoutButton = new JButton("注销");
        titleLabel = new JLabel("书名：");
        authorLabel = new JLabel("作者：");
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        searchButton = new JButton("搜索");
        bookTable = new JTable();
        bookScrollPane = new JScrollPane(bookTable);
        borrowButton = new JButton("借阅");
        returnButton = new JButton("归还");
        borrowLabel = new JLabel();
        // 创建一个流式布局管理器，水平间距10，垂直间距10，左对齐
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
// 设置窗体的布局为流式布局
        this.setLayout(flowLayout);

// 创建一个面板，用于放置欢迎标签和注销按钮
        JPanel topPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，右对齐
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
// 将欢迎标签和注销按钮添加到面板中
        topPanel.add(welcomeLabel);
        topPanel.add(logoutButton);
// 将面板添加到窗体中
        this.add(topPanel);

// 创建一个面板，用于放置书名标签和书名文本框
        JPanel titlePanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将书名标签和书名文本框添加到面板中
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);
// 将面板添加到窗体中
        this.add(titlePanel);

// 创建一个面板，用于放置作者标签和作者文本框
        JPanel authorPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        authorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将作者标签和作者文本框添加到面板中
        authorPanel.add(authorLabel);
        authorPanel.add(authorField);
// 将面板添加到窗体中
        this.add(authorPanel);

// 创建一个面板，用于放置搜索按钮
        JPanel searchPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将搜索按钮添加到面板中
        searchPanel.add(searchButton);
// 将面板添加到窗体中
        this.add(searchPanel);

// 创建一个面板，用于放置图书滚动面板
        JPanel bookPanel = new JPanel();
// 设置面板的大小为760*400像素
        bookPanel.setPreferredSize(new Dimension(760, 400));
// 设置面板的布局为边界布局
        bookPanel.setLayout(new BorderLayout());
// 将图书滚动面板添加到面板的中央位置
        bookPanel.add(bookScrollPane, BorderLayout.CENTER);
// 将面板添加到窗体中
        this.add(bookPanel);

// 创建一个面板，用于放置借阅按钮和归还按钮
        JPanel buttonPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将借阅按钮和归还按钮添加到面板中
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
// 将面板添加到窗体中
        this.add(buttonPanel);

// 创建一个面板，用于放置借阅信息标签
        JPanel borrowPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        borrowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将借阅信息标签添加到面板中
        borrowPanel.add(borrowLabel);
// 将面板添加到窗体中
        this.add(borrowPanel);
}
}