package entity;

public class User {
    private String username; // 用户名
    private String password; // 密码
    private String name; // 姓名
    private String borrowList; // 借阅列表

    public User() {}

    public User(String username, String password, String name, String borrowList) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.borrowList = borrowList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(String borrowList) {
        this.borrowList = borrowList;
    }

}
