package ui;

import dao.BookDao;
import entity.Book;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminFrame extends JFrame {
    // 定义组件
    private final JLabel welcomeLabel; // 欢迎标签
    private final JButton logoutButton; // 注销按钮
    private final JLabel titleLabel; // 书名标签
    private final JLabel authorLabel; // 作者标签
    private final JLabel publisherLabel; // 出版社标签
    private final JLabel idLabel; // 编号标签
    private final JTextField titleField; // 书名文本框
    private final JTextField authorField; // 作者文本框
    private final JTextField publisherField; // 出版社文本框
    private final JTextField idField; // 编号文本框
    private final JButton addButton; // 添加按钮
    private final JButton updateButton; // 编辑按钮
    private final JButton deleteButton; // 删除按钮
    private final JButton searchButton; // 搜索按钮
    private final JTable bookTable; // 图书表格
    private final JScrollPane bookScrollPane; // 图书滚动面板

    public AdminFrame(User user) {
        // 设置窗体标题
        this.setTitle("图书管理系统-管理员");
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
        publisherLabel = new JLabel("出版社：");
        idLabel = new JLabel("编号：");
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        publisherField = new JTextField(20);
        idField = new JTextField(20);
        addButton = new JButton("添加");
        updateButton = new JButton("编辑");
        deleteButton = new JButton("删除");
        searchButton = new JButton("搜索");
        bookTable = new JTable();
        bookScrollPane = new JScrollPane(bookTable);

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

// 创建一个面板，用于放置出版社标签和出版社文本框
        JPanel publisherPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        publisherPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将出版社标签和出版社文本框添加到面板中
        publisherPanel.add(publisherLabel);
        publisherPanel.add(publisherField);
// 将面板添加到窗体中
        this.add(publisherPanel);

// 创建一个面板，用于放置编号标签和编号文本框
        JPanel idPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将编号标签和编号文本框添加到面板中
        idPanel.add(idLabel);
        idPanel.add(idField);
// 将面板添加到窗体中
        this.add(idPanel);

// 创建一个面板，用于放置添加、编辑、删除和搜索按钮
        JPanel buttonPanel = new JPanel();
// 设置面板的布局为流式布局，水平间距10，垂直间距10，左对齐
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
// 将添加、编辑、删除和搜索按钮添加到面板中
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
// 将面板添加到窗体中
        this.add(buttonPanel);

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
    }

    public void updateBookTable() {
        // 调用BookDao的getBooksByTitleOrAuthor方法，传入空字符串，返回所有图书的列表
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooksByTitleOrAuthor("");
        // 创建一个图书表格模型对象，传入图书列表
        BookTableModel bookTableModel = new BookTableModel(books);
        // 设置图书表格的模型为图书表格模型对象
        bookTable.setModel(bookTableModel);// 为添加按钮添加事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取书名、作者、出版社和编号
                String title = titleField.getText();
                String author = authorField.getText();
                String publisher = publisherField.getText();
                String idStr = idField.getText();
                // 判断书名、作者、出版社和编号是否为空
                if (title == null || title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "书名不能为空！");
                    return;
                }
                if (author == null || author.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "作者不能为空！");
                    return;
                }
                if (publisher == null || publisher.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "出版社不能为空！");
                    return;
                }
                if (idStr == null || idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "编号不能为空！");
                    return;
                }
                // 将编号转换为整数，如果出现异常，说明编号不合法
                int id = 0;
                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "编号必须是整数！");
                    return;
                }
                // 创建一个Book对象，设置其属性
                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setId(id);
                // 调用BookDao的addBook方法，返回一个布尔值
                BookDao bookDao = new BookDao();
                boolean result = bookDao.addBook(book);
// 判断结果是否为真，如果为真，说明添加成功，否则说明添加失败
                if (result) {
                    JOptionPane.showMessageDialog(null, "添加成功！");
                    // 更新图书表格的数据
                    updateBookTable();
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败！");
                }
                // 为编辑按钮添加事件监听器
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 获取用户选择的图书表格的行索引
                        int rowIndex = bookTable.getSelectedRow();
                        // 判断行索引是否有效，如果无效，说明没有选择任何行，提示用户选择一行
                        if (rowIndex == -1) {
                            JOptionPane.showMessageDialog(null, "请选择一行！");
                            return;
                        }
                        // 获取该行的数据，并填充到文本框中
                        String title = (String) bookTable.getValueAt(rowIndex, 0);
                        String author = (String) bookTable.getValueAt(rowIndex, 1);
                        String publisher = (String) bookTable.getValueAt(rowIndex, 2);
                        int id = (int) bookTable.getValueAt(rowIndex, 3);
                        titleField.setText(title);
                        authorField.setText(author);
                        publisherField.setText(publisher);
                        idField.setText(String.valueOf(id));
                        // 为添加按钮添加事件监听器
                        addButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 获取书名、作者、出版社和编号
                                String title = titleField.getText();
                                String author = authorField.getText();
                                String publisher = publisherField.getText();
                                String idStr = idField.getText();
                                // 判断书名、作者、出版社和编号是否为空
                                if (title == null || title.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "书名不能为空！");
                                    return;
                                }
                                if (author == null || author.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "作者不能为空！");
                                    return;
                                }
                                if (publisher == null || publisher.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "出版社不能为空！");
                                    return;
                                }
                                if (idStr == null || idStr.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "编号不能为空！");
                                    return;
                                }// 将编号转换为整数，如果出现异常，说明编号不合法
                                int id = 0;
                                try {
                                    id = Integer.parseInt(idStr);
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "编号必须是整数！");
                                    return;
                                }
// 创建一个Book对象，设置其属性
                                Book book = new Book();
                                book.setTitle(title);
                                book.setAuthor(author);
                                book.setPublisher(publisher);
                                book.setId(id);
// 调用BookDao的getBookById方法，根据编号查询图书，返回一个Book对象
                                BookDao bookDao = new BookDao();
                                Book oldBook = bookDao.getBookById(id);
// 判断旧的Book对象是否为空，如果为空，说明编号不存在，执行添加操作，否则执行编辑操作
                                if (oldBook == null) {
                                    // 调用BookDao的addBook方法，返回一个布尔值
                                    boolean result = bookDao.addBook(book);
                                    // 判断结果是否为真，如果为真，说明添加成功，否则说明添加失败
                                    if (result) {
                                        JOptionPane.showMessageDialog(null, "添加成功！");
                                        // 更新图书表格的数据
                                        updateBookTable();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "添加失败！");
                                    }
                                } else {
                                    // 调用BookDao的updateBook方法，返回一个布尔值
                                    boolean result = bookDao.updateBook(book);
                                    // 判断结果是否为真，如果为真，说明编辑成功，否则说明编辑失败
                                    if (result) {
                                        JOptionPane.showMessageDialog(null, "编辑成功！");
                                        // 更新图书表格的数据
                                        updateBookTable();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "编辑失败！");
                                    }
                                }


                            }
                        });
                    }
                });
            }

        });
    }
}