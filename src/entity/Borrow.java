package entity;

// 借阅实体类
public class Borrow {
    private int userId; // 用户编号
    private int bookId; // 图书编号

    // 无参构造方法
    public Borrow() {
    }

    // 有参构造方法
    public Borrow(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    // 获取用户编号
    public int getUserId() {
        return userId;
    }

    // 设置用户编号
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // 获取图书编号
    public int getBookId() {
        return bookId;
    }

    // 设置图书编号
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // 重写toString方法，用于显示借阅信息
    @Override
    public String toString() {
        return "Borrow{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
