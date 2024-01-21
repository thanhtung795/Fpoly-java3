package ASM;

import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class qldiem {

    static DefaultTableModel model;
    static ArrayList<Diem> list = new ArrayList<>();
    static String User = "sa";
    static String Pass = "12345";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";
    //khai boa frame
    static int index = 0;
    static JFrame fr = new JFrame();

    static JPanel pn1 = new JPanel();
    static JPanel pn2 = new JPanel();

    static JTable tbsv = new JTable();
    static JLabel lbTitle = new JLabel("Quản lý điểm sinh viên");
    static JLabel lbTimKiem = new JLabel("Tìm kiếm");

    static JLabel lbMaSVPn1 = new JLabel("Mã Sv:");
    static JTextField txtMaSVPn1 = new JFormattedTextField();
    static JButton btnFind = new JButton();
    static JButton btnAdd = new JButton();
    static JButton btnDelete = new JButton();
    static JButton btnUpdate = new JButton();
    static JButton btnSave = new JButton();
    static JButton btnFirst = new JButton();
    static JButton btnPre = new JButton();
    static JButton btnNext = new JButton();
    static JButton btnLast = new JButton();
    static JButton btnLogOut = new JButton();
    static JLabel lbhotensv = new JLabel("Họ tên SV:");
    static JLabel lbMaSvpn2 = new JLabel("Ma SV:");
    static JLabel lbJava1 = new JLabel("Java 1");
    static JLabel lbJava2 = new JLabel("Java 2");
    static JLabel lbJava3 = new JLabel("Java 3");
    static JLabel lbdiemtb = new JLabel("Điểm TB");
    static JLabel lbtongdiem = new JLabel("0");
    static JLabel lbtsv = new JLabel("?");

    static JTextField txtMaSVpn2 = new JTextField();
    static JTextField txtJava1 = new JTextField();
    static JTextField txtJava2 = new JTextField();
    static JTextField txtJava3 = new JTextField();

    static JLabel lbSoLuongSV = new JLabel();

    static void table() {
        int data = list.size();
        String Column[] = {"MaSV", "Họ tên", "Java1", "Java2", "Java3", "Điểm TB"};

        // Use the static model variable
        model = new DefaultTableModel(Column, data);
        tbsv = new JTable(model);
        tbsv.setDefaultEditor(Object.class, null);
        JScrollPane sp = new JScrollPane(tbsv);
        sp.setBounds(10, 500, 570, 120);
        fr.add(sp);

    }

    static void Uptotable() {
        model = (DefaultTableModel) tbsv.getModel();
        if (!list.isEmpty()) {
            model.setRowCount(0);

            for (Diem d : list) {
                Object[] row = {
                    d.getMaSV(),
                    d.getHoTen(),
                    d.getDiemJava1(),
                    d.getDiemJava2(),
                    d.getDiemJava3(),
                    d.getDiemTB()
                };
                model.addRow(row);
            }
        }

    }

    static void UI() {

        fr.add(lbTitle);
        lbTitle.setBounds(150, 0, 300, 50);
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        lbTitle.setForeground(Color.BLUE);

        fr.add(lbTimKiem);
        lbTimKiem.setBounds(70, 50, 80, 50);
        lbTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));

        pn1.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout để tự động sắp xếp các thành phần

        pn1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Đặt khung viền
        pn1.setBounds(70, 90, 450, 50); // Đặt vị trí và kích thước cho JPanel
        fr.add(pn1);

        pn1.add(lbMaSVPn1);

        lbMaSVPn1.setPreferredSize(new Dimension(50, 40));
        pn1.add(txtMaSVPn1);
        txtMaSVPn1.setPreferredSize(new Dimension(265, 40));

        pn1.add(btnFind);
        btnFind.setText("Search");
        btnFind.setBounds(600, 80, 80, 50);
        ImageIcon IconFind = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Zoom-icon.png");
        Image SizeFind = IconFind.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFind = new ImageIcon(SizeFind);
        btnFind.setIcon(resizedIconFind);

        fr.add(btnAdd);
        btnAdd.setText("New");
        btnAdd.setBounds(430, 150, 120, 50);
        ImageIcon IconAdd = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\add-icon.png");
        Image SizeAdd = IconAdd.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconAdd = new ImageIcon(SizeAdd);
        btnAdd.setIcon(resizedIconAdd);

        fr.add(btnSave);
        btnSave.setText("Save");
        btnSave.setBounds(430, 210, 120, 50);
        ImageIcon IconSave = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\content-save-edit-icon.png");
        Image SizeSave = IconSave.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSave = new ImageIcon(SizeSave);
        btnSave.setIcon(resizedIconSave);

        fr.add(btnDelete);
        btnDelete.setText("Delete");
        btnDelete.setBounds(430, 270, 120, 50);
        ImageIcon IconDelete = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\delete-icon.png");
        Image SizeDelete = IconDelete.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconDelete = new ImageIcon(SizeDelete);
        btnDelete.setIcon(resizedIconDelete);

        fr.add(btnUpdate);
        btnUpdate.setText("Update");
        btnUpdate.setBounds(430, 330, 120, 50);
        ImageIcon IconUpdate = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\edit-icon.png");
        Image SizeUpdate = IconUpdate.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconUpdate = new ImageIcon(SizeUpdate);
        btnUpdate.setIcon(resizedIconUpdate);

        fr.add(btnLogOut);
        btnLogOut.setText("Log out");
        btnLogOut.setBounds(430, 390, 120, 50);
        ImageIcon iconLogOut = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\Exit.png");
        Image SizeLogOut = iconLogOut.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLogOut = new ImageIcon(SizeLogOut);
        btnLogOut.setIcon(resizedIconLogOut);

        fr.add(btnFirst);
        fr.add(btnPre);
        fr.add(btnNext);
        fr.add(btnLast);
        btnFirst.setBounds(80, 380, 70, 50);
        ImageIcon iconFirst = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-First-icon.png");
        Image SizeFirst = iconFirst.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFirst = new ImageIcon(SizeFirst);
        btnFirst.setIcon(resizedIconFirst);

        btnPre.setBounds(160, 380, 70, 50);
        ImageIcon iconPre = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-left-box-icon.png");
        Image SizePre = iconPre.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconPre = new ImageIcon(SizePre);
        btnPre.setIcon(resizedIconPre);

        btnNext.setBounds(240, 380, 70, 50);
        ImageIcon iconNext = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-right-box-icon.png");
        Image SizeNext = iconNext.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconNext = new ImageIcon(SizeNext);
        btnNext.setIcon(resizedIconNext);
        btnLast.setBounds(320, 380, 70, 50);
        ImageIcon iconLast = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-Last-icon.png");
        Image SizeLast = iconLast.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLast = new ImageIcon(SizeLast);
        btnLast.setIcon(resizedIconLast);

        fr.add(pn2);
        pn2.setLayout(null);
        pn2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn2.setBounds(60, 150, 350, 220);

        pn2.add(lbhotensv);
        pn2.add(lbMaSvpn2);
        pn2.add(lbJava1);
        pn2.add(lbJava2);
        pn2.add(lbJava3);
        pn2.add(lbdiemtb);
        pn2.add(lbtongdiem);
        pn2.add(lbtsv);

        pn2.add(txtMaSVpn2);
        pn2.add(txtJava1);
        pn2.add(txtJava2);
        pn2.add(txtJava3);

        lbhotensv.setFont(new Font("Tahoma", 1, 15));
        lbtsv.setFont(new Font("Tahoma", 1, 15));
        lbtsv.setForeground(Color.BLUE);
        lbMaSvpn2.setFont(new Font("Tahoma", 1, 15));
        lbJava1.setFont(new Font("Tahoma", 1, 15));
        lbJava1.setFont(new Font("Tahoma", 1, 15));
        lbJava2.setFont(new Font("Tahoma", 1, 15));
        lbJava3.setFont(new Font("Tahoma", 1, 15));

        lbdiemtb.setFont(new Font("Tahoma", 1, 15));
        lbtongdiem.setFont(new Font("Tahoma", 1, 30));

        lbtongdiem.setForeground(Color.BLUE);
        lbhotensv.setBounds(15, 10, 100, 40);
        lbtsv.setBounds(110, 10, 170, 40);
        lbMaSvpn2.setBounds(15, 50, 70, 40);
        lbJava1.setBounds(15, 90, 70, 40);
        lbJava2.setBounds(15, 130, 70, 40);
        lbJava3.setBounds(15, 170, 70, 40);
        lbdiemtb.setBounds(270, 30, 70, 40);
        lbtongdiem.setBounds(270, 70, 100, 90);

        fr.add(lbSoLuongSV);
        lbSoLuongSV.setBounds(10, 460, 300, 40);
        lbSoLuongSV.setFont(new Font("Tahoma", 1, 15));
        lbSoLuongSV.setForeground(Color.BLUE);

        txtMaSVpn2.setBounds(85, 55, 100, 30);
        txtJava1.setBounds(85, 95, 100, 30);
        txtJava2.setBounds(85, 135, 100, 30);
        txtJava3.setBounds(85, 175, 100, 30);

    }

    static void Frame() {
        fr.setSize(600, 670);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setLayout(null);
        fr.setVisible(true);
    }

    static void HienThiSvLen(int i) {
        if (i >= 0 && i < tbsv.getRowCount()) {
            String maSV = tbsv.getValueAt(i, 0).toString();
            String hoTen = tbsv.getValueAt(i, 1).toString();
            double diemJava1 = Double.parseDouble(tbsv.getValueAt(i, 2).toString());
            double diemJava2 = Double.parseDouble(tbsv.getValueAt(i, 3).toString());
            double diemJava3 = Double.parseDouble(tbsv.getValueAt(i, 4).toString());
            double diemTB = Double.parseDouble(tbsv.getValueAt(i, 5).toString());

            txtMaSVpn2.setText(maSV);
            lbtsv.setText(hoTen);
            txtJava1.setText(String.valueOf(diemJava1));
            txtJava2.setText(String.valueOf(diemJava2));
            txtJava3.setText(String.valueOf(diemJava3));
            lbtongdiem.setText(String.valueOf(diemTB));
        }
    }

    static void TaiDuLieuLenArrayList() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            Statement stm = con.createStatement();
            String sql = "select * from diem";
            ResultSet rs = stm.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String MaSV = rs.getString(1);
                String HoTen = rs.getString(2);
                double Java1 = rs.getDouble(3);
                double Java2 = rs.getDouble(4);
                double Java3 = rs.getDouble(5);
                Diem d = new Diem(MaSV, HoTen, Java1, Java2, Java3);
                list.add(d);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Lỗi kết  nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    static void ACL() {
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                findSVByMaSV(txtMaSVPn1.getText().toLowerCase());
                HienThiSvLen(index);
            }
        });

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                First();
                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {

                    HienThiSvLen(index);
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Last();

                if (index >= 0 && index < list.size()) {
                    index = tbsv.getSelectedRow();
                    HienThiSvLen(index);
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {

                    HienThiSvLen(index);
                }
            }
        });
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pre();

                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {

                    HienThiSvLen(index);
                }
            }
        });
        tbsv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = tbsv.getSelectedRow();
                HienThiSvLen(index);
            }
        }
        );

        btnAdd.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                addSV();
                lbSoLuongSV.setText(laythongtinbanghi());
            }
        }
        );
        btnUpdate.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (checkvalidate()) {
                    if (checkmark()) {
                        UpdateSV();
                        lbSoLuongSV.setText(laythongtinbanghi());
                    }
                }
            }
        }
        );
        btnSave.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (checkvalidate() && checkmark()) {
                    SaveSV();
                }
            }
        }
        );

        btnDelete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int OK = JOptionPane.showConfirmDialog(fr, "Bạn chắc chắn xoa?", "Delete", JOptionPane.YES_NO_OPTION);
                if (OK == JOptionPane.YES_OPTION) {
                    DeleteSV();
                }
            }
        }
        );
        btnLogOut.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Login login = new Login();
                login.showLoginFrame();
                fr.dispose();

            }
        }
        );
    }

    static void First() {
        if (!list.isEmpty()) {
            index = 0;
            updateInFo();
        }
    }

    static void Last() {
        if (!list.isEmpty()) {
            index = list.size() - 1;
            updateInFo();
        }
    }

    static void pre() {
        if (!list.isEmpty()) {
            if (index == 0) {
                Last();
            } else {
                index--;
            }
            updateInFo();
        }
    }

    static void next() {
        if (!list.isEmpty()) {
            if (index == list.size() - 1) {
                First();
            } else {
                index++;
            }
            updateInFo();
        }
    }

    static void updateInFo() {
        tbsv.setRowSelectionInterval(index, index);
        HienThiSvLen(index);

    }

    static Diem timtheoID(String id) {
        for (Diem d : list) {
            if (d.getMaSV().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    static void findSVByMaSV(String maSV) {
        String find = txtMaSVPn1.getText();
        Diem finded = timtheoID(find);
        if (finded != null) {
            DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
            model.setRowCount(0);
            Object row[] = new Object[]{finded.getMaSV(), finded.getHoTen(), finded.getDiemJava1(), finded.getDiemJava2(), finded.getDiemJava3(), finded.getDiemTB()};
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(fr, "Không tìm thấy sinh viên!");
        }
    }

    static String laythongtinbanghi() {
        return "Tổng top sinh viên cao dến thấp: " + list.size();
    }

    static void addSV() {
        txtMaSVPn1.setText("");
        txtJava1.setText("");
        txtJava2.setText("");
        txtJava3.setText("");
        lbtsv.setText("");
        lbtongdiem.setText("0");

        SapXepTheoDiem();
        Uptotable();
    }

    static void UpdateSV() {
        if (txtMaSVpn2.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Nhập mã Sinh viên");
            txtMaSVpn2.requestFocus();
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "update Diem set DiemJava1=?, DiemJava2=?, DiemJava3=? "
                    + "where MaNV = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtJava1.getText());
            st.setString(2, txtJava2.getText());
            st.setString(3, txtJava3.getText());
            st.setString(4, txtMaSVpn2.getText());
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Update Thành Công!");
            con.close();

            TaiDuLieuLenArrayList();
            HienThiSvLen(index);
            Uptotable();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static void DeleteSV() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);

            // Xóa từ bảng SinhVien 
            String deleteSinhVienSQL = "delete SinhVien where MaNV = ?";
            PreparedStatement stSinhVien = con.prepareStatement(deleteSinhVienSQL);
            stSinhVien.setString(1, txtMaSVpn2.getText());
            int deletedRows = stSinhVien.executeUpdate();
            // Xóa từ bảng Diem 
            String deleteDiemSQL = "delete Diem where MaNV = ?";
            PreparedStatement stDiem = con.prepareStatement(deleteDiemSQL);
            stDiem.setString(1, txtMaSVpn2.getText());
            stDiem.executeUpdate();

            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(fr, "Xóa Thành Công!");
            } else {
                JOptionPane.showMessageDialog(fr, "Xóa không Thành Công!");
            }

            con.close();
            index = 0;
            SapXepTheoDiem();
            TaiDuLieuLenArrayList();
            Uptotable();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static void SaveSV() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);

            // Câu lệnh SQL đã sửa
            String sql = "UPDATE Diem SET DiemJava1 = ?, DiemJava2 = ?, DiemJava3 = ? WHERE MaNV = ?";

            PreparedStatement st = con.prepareStatement(sql);

            // Đặt giá trị cho các trường điểm
            st.setDouble(1, Double.parseDouble(txtJava1.getText()));
            st.setDouble(2, Double.parseDouble(txtJava2.getText()));
            st.setDouble(3, Double.parseDouble(txtJava3.getText()));

            // Đặt MaNV cho điều kiện WHERE
            st.setString(4, txtMaSVpn2.getText());

            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Lưu thành công!");
            con.close();
            SapXepTheoDiem();
            TaiDuLieuLenArrayList();
            Uptotable();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static boolean checkmark() {
        double diem1 = Double.parseDouble(txtJava1.getText());
        double diem2 = Double.parseDouble(txtJava2.getText());
        double diem3 = Double.parseDouble(txtJava3.getText());

        if ((diem1 < 0 || diem1 > 10) || (diem2 < 0 || diem2 > 10) || (diem3 < 0 || diem3 > 10)) {
            JOptionPane.showMessageDialog(fr, "Điểm phải nhập từ 0 - 10");
            return false;
        }
        return true;

    }

    static boolean checkvalidate() {
        if (!txtJava1.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(fr, "Điểm không được nhập kí tự");
            return false;
        }
        if (!txtJava2.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(fr, "Điểm không được nhập kí tự");
            return false;
        }
        if (!txtJava3.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(fr, "Điểm không được nhập kí tự");
            return false;
        }
        return true;
    }

    static void SapXepTheoDiem() {
        Comparator<Diem> com = new Comparator<Diem>() {
            @Override
            public int compare(Diem o2, Diem o1) {
                Double d1 = o1.getDiemTB();
                Double d2 = o2.getDiemTB();
                return d1.compareTo(d2);
            }
        };
        Collections.sort(list, com);
    }

    public static void main() {
        UI();
        table();
        ACL();
        TaiDuLieuLenArrayList();
        Uptotable();
        SapXepTheoDiem();
        HienThiSvLen(index);
        lbSoLuongSV.setText(laythongtinbanghi());
        Frame();
    }
}
