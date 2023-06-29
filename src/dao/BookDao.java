package dao;

import entity.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    // 添加图书
    public boolean addBook(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO book (title, author, publisher, id) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getId());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
        return false;
    }

    // 编辑图书
    public boolean updateBook(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE book SET title = ?, author = ?, publisher = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getId());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
        return false;
    }

    // 删除图书
    public boolean deleteBook(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM book WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
        return false;
    }

    // 根据编号查询图书
    public Book getBookById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM book WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                book = new Book(title, author, publisher, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return book;
    }

    // 根据书名或作者查询图书
    public List<Book> getBooksByTitleOrAuthor(String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql =
                    "SELECT * FROM book WHERE title LIKE ? OR author LIKE ?";
            pstmt =
                    conn.prepareStatement(sql);
            // 使用百分号作为通配符
            pstmt.setString(1,
                    "%" + keyword + "%");
            pstmt.setString(2,
                    "%" + keyword + "%");
            rs =
                    pstmt.executeQuery();
            while (rs.next()) {
                String title =
                        rs.getString("title");
                String author =
                        rs.getString("author");
                String publisher =
                        rs.getString("publisher");
                int id =
                        rs.getInt("id");
                Book book =
                        new Book(title,
                                author,
                                publisher,
                                id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
        return books;
    }

}