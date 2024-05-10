/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class SQLConnection {
    private String user;
    private String pass;
    
    private SQLServerDataSource ds; // thiết lập thông tin kết nối
    private Connection conn; // sử dụng để kết nối với CSDL

    // Constructor: nhận vào tên, mật khẩu. Sau đó thiết lập thông tin kết nối và thực hiện kết nối với CSDL.
    public SQLConnection(String user, String pass) {
        
        if (user.isBlank()) {
            this.user = "sa";
        }
        else {
            this.user = user;
        }
        
        if (pass.isBlank()) {
            this.pass = "sa";
        }
        else {
            this.pass = pass;
        }
        
        ds = new SQLServerDataSource();
        
        //ds.setUser("sa");
        //ds.setPassword("sa");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("PandaERP");
        
        try {
            conn = ds.getConnection(this.user, this.pass);
            System.out.println("Ket noi SQL Server thanh cong: " + conn);
            
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }              
    }  
    
    // Phương thức nhận vào 1 chuỗi SQL, thực hiện truy vấn về trả về kết quả dưới dạng 1 đối tượng Resultset
    public ResultSet query(String sql) throws SQLException {
            // Callabstatement để thực hiện truy vấn
            CallableStatement cstmt = conn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();

            return rs;
        }
    
    // Phương thức getConnection() trả về đối tượng Connection để thực hiện các thao tác với CSDL như thêm, sửa, xóa dữ liệu.
    public Connection getConnection() {
        return this.conn;
    }
    
    // Phương thức close() đóng kết nối CSDL
    public void close() throws SQLException{
        this.conn.close();
    }    
    
}
