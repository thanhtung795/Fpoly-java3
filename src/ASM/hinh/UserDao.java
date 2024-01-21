package ASM.hinh;

import ASM.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    static List<User> list = new ArrayList<User>();
    static String User = "sa";
    static String Pass = "12345";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";

    public UserDao() {
        TaiDuLieuLenArrayList();
    }

    static void TaiDuLieuLenArrayList() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);
            Statement stm = con.createStatement();
            String sql = "select * from Users"; 
            ResultSet rs = stm.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                String role = rs.getString(3);
                User user = new User(username, password, role);
                list.add(user);
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public User CheckRoll(String Username) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);
            Statement stm = con.createStatement();
            String sql = "SELECT role FROM Users WHERE Username = '" + Username + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String role = rs.getString("role");
                return new User(Username, "", role);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }

        return null;
    }

    public boolean CheckLogin(String Username, String Password) {
        for (User u : list) {
            if (u.getUsername().equals(Username) && u.getPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }
}
