import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Thay thế thông tin kết nối của bạn
                String url = "jdbc:mysql://localhost:3306/DBSinhVien";
                String user = "your_username";
                String password = "your_password";

                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
