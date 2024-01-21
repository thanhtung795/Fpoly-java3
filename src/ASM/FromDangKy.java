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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FromDangKy {

    static String User = "sa";
    static String Pass = "12345";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";

    static JFrame fr = new JFrame("Đăng ký");
    static JLabel lbtitle = new JLabel("Đăng ký");
    static JLabel lbTenDangNhap = new JLabel("Tên đăng nhập");
    static JLabel lbMatkhau = new JLabel("Mật khẩu");
    static JLabel lbnhaplaimatkhau = new JLabel("Nhập lại mật khẩu");

    static JTextField txtTenDangNhap = new JTextField();
    static JPasswordField txtMatKhau = new JPasswordField();
    static JPasswordField txtNhapLaiMatKhau = new JPasswordField();

    static JButton btnDangKy = new JButton("Đăng ký");
    static JButton btnHuy = new JButton("Hủy");

    static ButtonGroup gr = new ButtonGroup();
    static JRadioButton rdoDaoTao = new JRadioButton("Đào tạo");
    static JRadioButton rdoGiangVien = new JRadioButton("Giảng Viên");

    static void UI() {
        fr.add(lbtitle);
        lbtitle.setBounds(150, 10, 100, 30);
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

        fr.add(btnDangKy);
        fr.add(btnHuy);
        fr.add(rdoDaoTao);
        fr.add(rdoGiangVien);
        gr.add(rdoDaoTao);
        gr.add(rdoGiangVien);

        rdoGiangVien.setBounds(250, 160, 120, 30);
        rdoDaoTao.setBounds(180, 160, 70, 30);
        rdoDaoTao.setSelected(true);
        btnDangKy.setBounds(170, 200, 80, 50);
        btnHuy.setBounds(260, 200, 80, 50);
    }

    static void Frame() {
        fr.setSize(400, 300);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }

    static void SuKien() {
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checknull() && checkpass()) {
                    Dangkytaikhoan();
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
            rdoDaoTao.setSelected(true);
        }
    }

    static void Dangkytaikhoan() {
        if (CheckTrung(txtTenDangNhap.getText())) {
            JOptionPane.showMessageDialog(fr, " Tên đã tồn tại!");
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "insert into Users  (Username, Password, role) VALUES (?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtTenDangNhap.getText());
            st.setString(2, new String(txtMatKhau.getPassword()));
            if (rdoDaoTao.isSelected()) {
                st.setString(3, "dao tao");
            }
            if (rdoGiangVien.isSelected()) {
                st.setString(3, "giang vien");
            }
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Đăng ký Thành Công!");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static boolean CheckTrung(String ten) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT Username FROM Users WHERE Username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ten);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
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
        JOptionPane.showMessageDialog(fr,"Mật khẩu không khớp");
        return false; // Mật khẩu không khớp
    }

    public static void main() {
        UI();
        SuKien();

        Frame();
    }

}
