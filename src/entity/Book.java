package entity;

public class Book {
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社
    private int id; // 编号

    public Book() {}

    public Book(String title, String author, String publisher, int id) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
