package ui;

import entity.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    // 定义表格列名数组
    private String[] columnNames = {"书名", "作者", "出版社", "编号"};
    // 定义表格数据列表
    private List<Book> books;

    public BookTableModel(List<Book> books) {
        // 设置表格数据列表
        this.books = books;
    }

    // 获取表格列数
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // 获取表格行数
    @Override
    public int getRowCount() {
        return books.size();
    }

    // 获取表格列名
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // 获取指定单元格的值
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // 根据行索引获取对应的图书对象
        Book book = books.get(rowIndex);
        // 根据列索引返回对应的属性值
        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getPublisher();
            case 3:
                return book.getId();
            default:
                return null;
        }
    }

}
