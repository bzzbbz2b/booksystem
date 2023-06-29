package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

// 借阅数据访问对象类
public class BorrowDao {
    // 添加借阅
    public boolean addBorrow(int userId, int bookId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "INSERT INTO borrow (user_id, book_id) VALUES (?, ?)"; // 定义sql语句
            ps = conn.prepareStatement(sql); // 预编译sql语句
            ps.setInt(1, userId); // 设置参数
            ps.setInt(2, bookId);
            int result = ps.executeUpdate(); // 执行sql语句，返回影响的行数
            return result > 0; // 如果影响的行数大于0，说明添加成功，返回true；否则返回false
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null); // 关闭数据库连接、语句和结果集对象
        }
        return false; // 如果发生异常，返回false
    }

    // 删除借阅
    public boolean deleteBorrow(int userId, int bookId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "DELETE FROM borrow WHERE user_id = ? AND book_id = ?"; // 定义sql语句
            ps = conn.prepareStatement(sql); // 预编译sql语句
            ps.setInt(1, userId); // 设置参数
            ps.setInt(2, bookId);
            int result = ps.executeUpdate(); // 执行sql语句，返回影响的行数
            return result > 0; // 如果影响的行数大于0，说明删除成功，返回true；否则返回false
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null); // 关闭数据库连接、语句和结果集对象
        }
        return false; // 如果发生异常，返回false
    }

    // 根据用户编号和图书编号查询借阅
    public boolean getBorrowByUserIdAndBookId(int userId, int bookId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "SELECT * FROM borrow WHERE user_id = ? AND book_id = ?"; // 定义sql语句
            ps = conn.prepareStatement(sql); // 预编译sql语句
            ps.setInt(1, userId); // 设置参数
            ps.setInt(2, bookId);
            rs = ps.executeQuery(); // 执行sql语句，返回结果集对象
            return rs.next(); // 如果结果集有下一条记录，说明查询到了借阅，返回true；否则返回false
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs); // 关闭数据库连接、语句和结果集对象
        }
        return false; // 如果发生异常，返回false
    }


    // 根据用户编号查询借阅数量
    public int getBorrowCountByUserId(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0; // 定义一个变量，用于存放借阅数量
        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "SELECT COUNT(*) FROM borrow WHERE user_id = ?"; // 定义sql语句
            ps = conn.prepareStatement(sql); // 预编译sql语句
            ps.setInt(1, userId); // 设置参数
            rs = ps.executeQuery(); // 执行sql语句，返回结果集对象
            if (rs.next()) { // 如果结果集有下一条记录，说明查询到了借阅数量
                count = rs.getInt(1); // 获取结果集中的第一列的值，即借阅数量，并赋值给变量count
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs); // 关闭数据库连接、语句和结果集对象
        }
        return count; // 返回借阅数量
    }
}
