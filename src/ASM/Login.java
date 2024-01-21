package ASM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class Login {

    static ArrayList<Remenber> list = new ArrayList<>();
    static String User = "sa";
    static String Pass = "songlong";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";
    static int index = 0;
    static JFrame fr = new JFrame("Login");

    static JLabel lbtitle = new JLabel("ĐĂNG NHẬP HỆ THốNG");
    static JLabel lbHinh = new JLabel();
    static JLabel lbTenDangNhap = new JLabel("Tên đăng nhập:");
    static JLabel lbMatkhau = new JLabel("Mật khẩu:");

    static Border border = BorderFactory.createCompoundBorder(
            new SoftBevelBorder(SoftBevelBorder.RAISED, Color.LIGHT_GRAY, Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10));

    static ImageIcon icon = new ImageIcon("D:\\FPT\\java3\\Icon\\security.png");

    static JPanel pn1 = new JPanel();

    static JPanel pn2 = new JPanel();
    static JPanel pn3 = new JPanel();

    static JPanel pnLogin = new JPanel();

    static JTextField txtTenDangNhap = new JTextField();
    static JPasswordField txtMatKhau = new JPasswordField();

    static JButton btnDangNhap = new JButton("Đăng nhập");
    static JButton btnHuyBo = new JButton("Thoát");
    static JButton btnDangky = new JButton("Đăng ký");
    static JButton btnQuenmk = new JButton("Quên mật khẩu");

    static JRadioButton rdoGhiNho = new JRadioButton("nhớ mật khẩu");

    
    
    static void Label() {

        lbtitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbtitle.setBounds(230, 5, 260, 40);

        pn1.setLayout(null);
        pn1.add(lbtitle);

        lbHinh.setIcon(icon);
        pnLogin.setBorder(border);
        pnLogin.setLayout(null);
        pnLogin.setPreferredSize(new Dimension(400, 110));
        lbTenDangNhap.setBounds(30, 5, 100, 50);
        lbMatkhau.setBounds(30, 65, 100, 50);

        txtTenDangNhap.setBounds(120, 15, 250, 30);
        txtMatKhau.setBounds(120, 75, 250, 30);

        btnDangNhap.setBounds(190, 120, 100, 50);
        btnHuyBo.setBounds(300, 120, 70, 50);

        pnLogin.add(lbTenDangNhap);
        pnLogin.add(txtTenDangNhap);
        pnLogin.add(lbMatkhau);
        pnLogin.add(txtMatKhau);

        pnLogin.add(btnDangNhap);
        pnLogin.add(btnHuyBo);
        pnLogin.add(rdoGhiNho);
        rdoGhiNho.setBounds(30, 120, 120, 50);

        pn2.setLayout(null);
        pn2.add(lbHinh);
        lbHinh.setBounds(10, 0, 180, 180);
        pn2.add(pnLogin);
        pnLogin.setBounds(200, 0, 400, 180);
        pn2.setPreferredSize(new Dimension(0, 100));

        pn3.add(btnDangky);
        pn3.add(btnQuenmk);
        pn3.add(Box.createHorizontalStrut(7));
        pn3.setPreferredSize(new Dimension(0, 0));
        pn3.setLayout(new FlowLayout(FlowLayout.RIGHT));

    }

    static void Frame() {
        fr.setSize(630, 400);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setLayout(new BoxLayout(fr.getContentPane(), BoxLayout.Y_AXIS));
        fr.setVisible(true);

        fr.add(pn1);
        fr.add(pn2);
        fr.add(pn3);
    }

    static void XuLiSuKien() {
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Checknull()) {
                    Login();
                    if (rdoGhiNho.isSelected()) {
                        Remenber();
                    } else {
                        UnRemenber();
                    }
                }
            }
        });
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnDangky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromDangKy dky = new FromDangKy();
                dky.main();
            }
        });
        btnQuenmk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromLayLaiMatkhau LLai = new FromLayLaiMatkhau();
                LLai.main();
            }
        });

        txtTenDangNhap.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (Checknull()) {
                        Login();
                        if (rdoGhiNho.isSelected()) {
                            Remenber();
                        } else {
                            UnRemenber();
                        }
                    }
                }
            }
        });
        txtMatKhau.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (Checknull()) {
                        Login();
                        if (rdoGhiNho.isSelected()) {
                            Remenber();
                        } else {
                            UnRemenber();
                        }
                    }
                }
            }
        });
    }

    static void Login() {
        String username = txtTenDangNhap.getText();
        String password = new String(txtMatKhau.getPassword());

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT Role FROM Users WHERE Username = ? AND Password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");
                switch (role) {
                    case "dao tao":
                        // Hiển thị frame cho vai trò "dao tao"
                        qlsv qlsvFrame = new qlsv();
                        qlsvFrame.main();
                        break;
                    case "giang vien":
                        // Hiển thị frame cho vai trò "giang vien"
                        qldiem qlDiemFrame = new qldiem();
                        qlDiemFrame.main();
                        break;
                    default:
                        JOptionPane.showMessageDialog(fr, "Vai trò không hợp lệ!");
                }
                fr.dispose();
            } else {
                JOptionPane.showMessageDialog(fr, "Tên đăng nhập hoặc mật khẩu sai!");
                return;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void loadFrame(int i) {
        if (i >= 0 && i < list.size()) {
            Remenber rmb = list.get(i);
            txtTenDangNhap.setText(rmb.getUsername());
            txtMatKhau.setText(rmb.getPassword());
            rdoGhiNho.setSelected(rmb.isRemenber());
        }
    }

    static void LoadData() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            Statement st = con.createStatement();
            String sql = "Select * from Remember";
            ResultSet rs = st.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                boolean remenber = rs.getBoolean(3);
                Remenber rmb = new Remenber(username, password, remenber);
                list.add(rmb);
            }
            st.execute(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void Remenber() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String newUsername = txtTenDangNhap.getText();
            String newPassword = new String(txtMatKhau.getPassword());

            // Xóa tất cả các thông tin đăng nhập hiện có
            String deleteQuery = "DELETE FROM Remember";
            PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
            deleteStmt.executeUpdate();

            // Thêm thông tin đăng nhập mới vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO Remember (Username, Password, Remenber) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertQuery);
            insertStmt.setString(1, newUsername);
            insertStmt.setString(2, newPassword);

            // Kiểm tra xem nút radio được chọn hay không và thiết lập giá trị cho cột Remenber
            boolean rememberSelected = rdoGhiNho.isSelected();
            insertStmt.setBoolean(3, rememberSelected);
            insertStmt.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void UnRemenber() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "DELETE FROM Remember";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            con.close();
            LoadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        showLoginFrame();

    }

    static boolean Checknull() {
        if (txtTenDangNhap.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Chưa nhập tên đăng nhập !");
            return false;
        }
        if (txtMatKhau.getPassword().length == 0) {
            JOptionPane.showMessageDialog(fr, "Chưa nhập mật khẩu !");
            return false;
        }

        return true;
    }

    static public void showLoginFrame() {
        Label();
        LoadData();
        loadFrame(index);
        XuLiSuKien();
        Frame();
    }

}
