package ASM;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Natsu
 */
public class FromLayLaiMatkhau {

    static String User = "sa";
    static String Pass = "12345";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";

    static JFrame fr = new JFrame("Tìm mật khẩu");
    static JLabel lbtitle = new JLabel("Tìm mật khẩu");
    static JLabel lbTenDangNhap = new JLabel("Tên đăng nhập");
    static JLabel lbMatkhau = new JLabel("Mật khẩu mới");
    static JLabel lbnhaplaimatkhau = new JLabel("Xác nhận lại");

    static JTextField txtTenDangNhap = new JTextField();
    static JPasswordField txtMatKhau = new JPasswordField();
    static JPasswordField txtNhapLaiMatKhau = new JPasswordField();

    static JButton btnDoi = new JButton("Đổi mật khẩu");
    static JButton btnHuy = new JButton("Hủy");

    static void UI() {
        fr.add(lbtitle);
        lbtitle.setBounds(130, 10, 200, 30);
        lbtitle.setFont(new Font("Tahoma", 1, 20));
        lbtitle.setForeground(Color.BLUE);

        fr.add(lbTenDangNhap);
        fr.add(lbMatkhau);
        fr.add(lbnhaplaimatkhau);

        lbTenDangNhap.setBounds(20, 50, 100, 30);
        lbMatkhau.setBounds(20, 90, 100, 30);
        lbnhaplaimatkhau.setBounds(20, 130, 100, 30);

        fr.add(txtTenDangNhap);
        fr.add(txtMatKhau);
        fr.add(txtNhapLaiMatKhau);

        txtTenDangNhap.setBounds(140, 50, 200, 30);
        txtMatKhau.setBounds(140, 90, 200, 30);
        txtNhapLaiMatKhau.setBounds(140, 130, 200, 30);

        fr.add(btnDoi);
        fr.add(btnHuy);

        btnDoi.setBounds(130, 200, 120, 50);
        btnHuy.setBounds(260, 200, 80, 50);
    }

    static void XuliSuKien() {
        btnDoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checknull() && checkpass()) {
                   ChangePass();
                    txtTenDangNhap.setText("");
                    txtMatKhau.setText("");
                    txtNhapLaiMatKhau.setText("");
                }
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HuyDangKy();
            }
        });
    }

    static void HuyDangKy() {
        int confirm = JOptionPane.showConfirmDialog(fr, "Bạn có chắc chắn muốn hủy đăng ký?", "Xác nhận hủy đăng ký", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Đặt tất cả các trường về trạng thái mặc định hoặc làm các xử lý khác nếu cần.
            txtTenDangNhap.setText("");
            txtMatKhau.setText("");
            txtNhapLaiMatKhau.setText("");

        }
    }

    static boolean checknull() {
        String taikhoan = txtTenDangNhap.getText();
        String pass = new String(txtMatKhau.getPassword());
        String pass1 = new String(txtNhapLaiMatKhau.getPassword());

        if (taikhoan.equals("") || pass.equals("") || pass1.equals("")) {
            JOptionPane.showMessageDialog(fr, "Hãy nhập đầy đủ thông tin");
            return false; // Có ít nhất một trường trống
        }
        return true; // Tất cả các trường không trống
    }

    static boolean checkpass() {
        String pass = new String(txtMatKhau.getPassword());
        String pass1 = new String(txtNhapLaiMatKhau.getPassword());

        if (pass.equals(pass1)) {
            return true; // Mật khẩu hợp lệ
        }
        JOptionPane.showMessageDialog(fr, "Mật khẩu không khớp");
        return false; // Mật khẩu không khớp
    }

    static void Frame() {
        fr.setSize(400, 300);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }

    static void ChangePass (){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);

            // Câu lệnh SQL đã sửa
            String sql = "UPDATE Users SET password = ? WHERE Username = ?";

            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, new String(txtMatKhau.getPassword()));
            st.setString(2,txtTenDangNhap.getText());
           
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Đổi mật khẩu thành công!");
            con.close();
           
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    public static void main() {
        UI();
        XuliSuKien();
        Frame();
    }
}
