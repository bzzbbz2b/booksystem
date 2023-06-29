package dao;

import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 注册用户
    public boolean registerUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO user (username, password, name, borrow_list) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getBorrowList());
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

    // 登录用户
    public User loginUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String borrowList = rs.getString("borrow_list");
                user = new User(username, password, name, borrowList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }

    // 更新用户信息
    public boolean updateUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql =
                    "UPDATE user SET password = ?, name = ?, borrow_list = ? WHERE username = ?";
            pstmt =
                    conn.prepareStatement(sql);
            pstmt.setString(1,
                    user.getPassword());
            pstmt.setString(2,
                    user.getName());
            pstmt.setString(3,
                    user.getBorrowList());
            pstmt.setString(4,
                    user.getUsername());
            int count =
                    pstmt.executeUpdate();
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

}
