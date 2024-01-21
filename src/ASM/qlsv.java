 package ASM;

import ASM.SinhVien;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.plaf.DesktopIconUI;

public class qlsv {

    static ArrayList<SinhVien> list = new ArrayList<>();
    private static DefaultTableModel model;
    private static String[] oldHeaders;
    static String checkemail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    static String pathHinh = "src\\hinh\\";
    static JTable tbsv;
    static int index = 0;
    static String User = "sa";
    static String Pass = "songlong";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=DBSinhVien;encrypt = false";
    //khai boa frame
    static JFrame fr = new JFrame("Quản Lý sinh viên");
    // khai bao label
    static JLabel LbTieuDe = new JLabel("Quản Lý sinh viên");
    static JLabel LbMaNV = new JLabel("MaNV:");
    static JLabel LbHoTen = new JLabel("Họ tên:");
    static JLabel LbEmail = new JLabel("Email:");
    static JLabel LbSDT = new JLabel("Số ĐT:");
    static JLabel LbGioiTinh = new JLabel("Giới tính:");
    static JLabel LbDiaChi = new JLabel("Địa chỉ:");
    static JLabel lbNganh = new JLabel("Ngành:");
    static JLabel LbRecoll = new JLabel();
    // khai bao textField
    static JTextField txtMaNv = new JTextField();
    static JTextField txtHoTen = new JTextField();
    static JTextField txtEmail = new JTextField();
    static JTextField txtSDT = new JTextField();
    static JTextArea txtDiaChi = new JTextArea();
    static JTextField txtNganh = new JTextField();

    //khai check box
    static JRadioButton ckNam = new JRadioButton("Nam");
    static JRadioButton ckNu = new JRadioButton("Nữ");
    //khai bao button gruop
    static ButtonGroup gr = new ButtonGroup();
    //khai bao button 
    static JButton btnAdd = new JButton();
    static JButton btnDelete = new JButton();
    static JButton btnUpdate = new JButton();
    static JButton btnSave = new JButton();
    static JButton btnFirst = new JButton();
    static JButton btnPre = new JButton();
    static JButton btnNext = new JButton();
    static JButton btnLast = new JButton();
    static JButton btnReport = new JButton();
    static JButton btnFillReport = new JButton();
    static JButton btnLogOut = new JButton();

    static Border border = BorderFactory.createCompoundBorder(
            new SoftBevelBorder(SoftBevelBorder.RAISED, Color.LIGHT_GRAY, Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10));

    static JPanel pnAvatar = new JPanel();
    static JLabel LBHinh = new JLabel();

    static JComboBox<String> cbNganh = new JComboBox<>();

    static void Table() {
        // Khai báo model nếu chưa tồn tại
        if (model == null) {
            oldHeaders = new String[]{"Mã SV", "Họ tên", "Email", "Số điện thoại", "Địa chỉ", "Giới tính", "Hinh", "Ngành"};
            model = new DefaultTableModel(oldHeaders, 0);
            tbsv = new JTable(model);
            tbsv.setDefaultEditor(Object.class, null);
            JScrollPane sp = new JScrollPane(tbsv);
            sp.setBounds(10, 380, 760, 150);
            fr.add(sp);
        }
        TaiDuLieuLenArrayList();
        updateTable();
    }

    static void fillTable(String nganh) {
        String fileName = "report_'" + nganh + "'.txt";
        File file = new File(fileName);
        if (file.exists()) {
            String fileContent = Xfile.readFile(fileName);
            String[] headers = {"Mã SV", "Họ tên", "Email", "Số điện thoại", "Địa chỉ", "Giới tính", "Hinh", "Ngành", "Điểm Java 1", "Điểm Java 2", "Điểm Java 3"};
            model.setColumnIdentifiers(headers); // Cập nhật headers cho bảng
            model.setRowCount(0); // Xóa dữ liệu cũ từ model
            String[] lines = fileContent.split("\\n");
            for (String line : lines) {
                String[] data = line.split(", ");
                model.addRow(Arrays.copyOfRange(data, 1, data.length));
            }

        } else {
            JOptionPane.showMessageDialog(null, "File report không tồn tại!");
        }
    }

    static String laythongtinbanghi() {
        return "Record " + (index + 1) + " of " + list.size();
    }

    static void updateTable() {
        DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
        model.setRowCount(0);  // Xóa các dòng hiện tại
        for (SinhVien sv : list) {
            String fileName = new File(sv.getHinh()).getAbsolutePath();
            Object[] row = {
                sv.getMaSV(),
                sv.getHoTen(),
                sv.getEmail(),
                sv.getSoDT(),
                sv.isGioiTinh() ? "Nam" : "Nữ",
                sv.getDiaChi(),
                fileName,
                sv.getNganh()
            };
            model.addRow(row);
        }
    }

    static void Frame() {
        fr.setSize(800, 630);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        fr.setLayout(null);
    }

    static void Label() {
        //tieu de
        LbTieuDe.setForeground(Color.blue);
        LbTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
        LbTieuDe.setBounds(300, 20, 350, 40);

        // noi dung
        LbMaNV.setBounds(40, 80, 70, 20);
        LbMaNV.setFont(new Font("Arial", Font.BOLD, 20));
        LbHoTen.setBounds(40, 120, 70, 20);
        LbHoTen.setFont(new Font("Arial", Font.BOLD, 20));
        LbEmail.setBounds(40, 160, 70, 20);
        LbEmail.setFont(new Font("Arial", Font.BOLD, 20));
        LbSDT.setBounds(40, 200, 70, 20);
        LbSDT.setFont(new Font("Arial", Font.BOLD, 20));
        LbGioiTinh.setBounds(40, 240, 90, 20);
        LbGioiTinh.setFont(new Font("Arial", Font.BOLD, 20));
        LbDiaChi.setBounds(40, 280, 80, 20);
        LbDiaChi.setFont(new Font("Arial", Font.BOLD, 20));
        LbRecoll.setBounds(90, 320, 190, 40);
        LbRecoll.setFont(new Font("Arial", 1, 25));
        LbRecoll.setForeground(Color.red);

        pnAvatar.add(LBHinh);
        pnAvatar.setBorder(border);
        pnAvatar.setBounds(500, 80, 240, 180);
    }

    static void TextField() {
        txtMaNv.setBounds(130, 80, 350, 20);
        txtHoTen.setBounds(130, 120, 350, 20);
        txtEmail.setBounds(130, 160, 350, 20);
        txtSDT.setBounds(130, 200, 350, 20);
        txtDiaChi.setBounds(130, 280, 350, 20);

    }

    static void CheckBox() {
        ckNam.setBounds(150, 230, 60, 40);
        ckNu.setBounds(220, 230, 60, 40);
        gr.add(ckNam);
        gr.add(ckNu);
    }

    static void Button() {
        btnAdd.setBounds(500, 280, 50, 40);
        ImageIcon IconAdd = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\add-icon.png");
        Image SizeAdd = IconAdd.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconAdd = new ImageIcon(SizeAdd);
        btnAdd.setIcon(resizedIconAdd);
        btnDelete.setBounds(680, 280, 50, 40);
        ImageIcon IconDelete = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\delete-icon.png");
        Image SizeDelete = IconDelete.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconDelete = new ImageIcon(SizeDelete);
        btnDelete.setIcon(resizedIconDelete);
        btnUpdate.setBounds(620, 280, 50, 40);
        ImageIcon IconUpdate = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\edit-icon.png");
        Image SizeUpdate = IconUpdate.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconUpdate = new ImageIcon(SizeUpdate);
        btnUpdate.setIcon(resizedIconUpdate);
        btnSave.setBounds(560, 280, 50, 40);
        ImageIcon IconSave = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\content-save-edit-icon.png");
        Image SizeSave = IconSave.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSave = new ImageIcon(SizeSave);
        btnSave.setIcon(resizedIconSave);

        btnFirst.setBounds(300, 540, 40, 40);
        ImageIcon iconFirst = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-First-icon.png");
        Image SizeFirst = iconFirst.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFirst = new ImageIcon(SizeFirst);
        btnFirst.setIcon(resizedIconFirst);

        btnPre.setBounds(360, 540, 40, 40);
        ImageIcon iconPre = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-left-box-icon.png");
        Image SizePre = iconPre.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconPre = new ImageIcon(SizePre);
        btnPre.setIcon(resizedIconPre);

        btnNext.setBounds(420, 540, 40, 40);
        ImageIcon iconNext = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-right-box-icon.png");
        Image SizeNext = iconNext.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconNext = new ImageIcon(SizeNext);
        btnNext.setIcon(resizedIconNext);

        btnLast.setBounds(480, 540, 40, 40);
        ImageIcon iconLast = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-Last-icon.png");
        Image SizeLast = iconLast.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLast = new ImageIcon(SizeLast);
        btnLast.setIcon(resizedIconLast);

        btnReport.setBounds(500, 330, 50, 40);
        ImageIcon iconReport = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\Statistics.png");
        Image SizeReport = iconReport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconReprot = new ImageIcon(SizeReport);
        btnReport.setIcon(resizedIconReprot);

        btnLogOut.setBounds(560, 330, 50, 40);
        ImageIcon iconLogOut = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\Exit.png");
        Image SizeLogOut = iconLogOut.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLogOut = new ImageIcon(SizeLogOut);
        btnLogOut.setIcon(resizedIconLogOut);

        btnFillReport.setBounds(620, 330, 50, 40);
        ImageIcon iconFillReport = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\List.png");
        Image SizeFillReport = iconFillReport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFillReport = new ImageIcon(SizeFillReport);
        btnFillReport.setIcon(resizedIconFillReport);

        cbNganh.setBounds(330, 330, 150, 30);
        cbNganh.addItem("CNTT");
        cbNganh.addItem("TKDH");
        cbNganh.addItem("LTG");
    }

    static void addCtn() {
        // add label
        fr.add(LbTieuDe);
        fr.add(LbMaNV);
        fr.add(LbHoTen);
        fr.add(LbEmail);
        fr.add(LbSDT);
        fr.add(LbGioiTinh);
        fr.add(LbDiaChi);

        fr.add(LbRecoll);
        // add textfeild
        fr.add(txtMaNv);
        fr.add(txtHoTen);
        fr.add(txtEmail);
        fr.add(txtSDT);
        fr.add(txtDiaChi);

        fr.add(pnAvatar);

        fr.add(ckNam);
        fr.add(ckNu);
// ADD Button 
        fr.add(btnAdd);
        fr.add(btnDelete);
        fr.add(btnUpdate);
        fr.add(btnSave);
        fr.add(btnFirst);
        fr.add(btnPre);
        fr.add(btnNext);
        fr.add(btnLast);
        fr.add(btnReport);
        fr.add(btnFillReport);
        fr.add(btnLogOut);

        fr.add(cbNganh);
    }

    static void updateInFo() {
        tbsv.setRowSelectionInterval(index, index);
        LbRecoll.setText(laythongtinbanghi());
    }

    static void chonHinh() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn hình");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                if (selectedFile.exists()) {
                    String imagePath = selectedFile.getAbsolutePath();
                    // Lưu đường dẫn hình ảnh vào đối tượng SinhVien
                    list.get(index).setHinh(imagePath);
                    // Hiển thị hình ảnh trong giao diện
                    Uphinh(imagePath);
                } else {
                    JOptionPane.showMessageDialog(null, "File không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void XuLiSuKien() {
        pnAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonHinh();

            }
        });
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                First();
                index = tbsv.getSelectedRow();
                tbsv.setRowSelectionInterval(index, index);
                if (index >= 0 && index < list.size()) {
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Last();
                index = tbsv.getSelectedRow();
                tbsv.setRowSelectionInterval(index, index);

                if (index >= 0 && index < list.size()) {
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
                tbsv.setRowSelectionInterval(index, index);

                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pre();
                index = tbsv.getSelectedRow();
                tbsv.setRowSelectionInterval(index, index);
                if (index >= 0 && index < list.size()) {
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });
        tbsv.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbsv.getSelectedRow();

                    if (selectedRow >= 0 && selectedRow < list.size()) {
                        index = selectedRow;
                        HienThiDSlenFrame(index);
                    }
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table();
                // Restore lại header cũ khi nhấn nút "Add"
                if (oldHeaders != null) {
                    DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
                    model.setColumnIdentifiers(oldHeaders);
                }
                addSV();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkvalidate()) {
                    UpdateSV();
                    updateInFo();
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkvalidate()) {
                    SaveSV();
                    updateInFo();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int OK = JOptionPane.showConfirmDialog(fr, "Bạn chắc chắn xoa?", "Delete", JOptionPane.YES_NO_OPTION);
                if (OK == JOptionPane.YES_OPTION) {
                    DeleteSV();
                    updateInFo();
                }
            }
        });
        btnReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedNganh = (String) cbNganh.getSelectedItem();
                generateReportByNganh(selectedNganh);

            }
        });
        btnFillReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Table();
               updateInFo();
               updateTable();
            }
        });
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Login login = new Login();
                fr.dispose();
                login.showLoginFrame();

            }
        });
         cbNganh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) cbNganh.getSelectedItem();
                filterTableByType(selectedType);
            }
        });
    }

      static void filterTableByType(String selectedType) {
        DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

        for (SinhVien sp : list) {
            if (sp.getNganh().equals(selectedType)) {
                String fileName = new File(sp.getHinh()).getAbsolutePath();
                Object[] row = {
                    sp.getMaSV(),
                    sp.getHoTen(),
                    sp.getEmail(),
                    sp.getSoDT(),
                    sp.getDiaChi(),
                    sp.isGioiTinh()?"Nam":"Nữ",
                    fileName,
                    sp.getNganh()
                };
                model.addRow(row);
            }
        }
          if (tbsv.getRowCount() > 0) {
        index = tbsv.getRowCount();
        updateInFo();
    }
    }
    static void HienThiDSlenFrame(int i) {
        if (i >= 0 && i < tbsv.getRowCount()) {
            txtMaNv.setText((String) tbsv.getValueAt(i, 0));
            txtHoTen.setText((String) tbsv.getValueAt(i, 1));
            txtEmail.setText((String) tbsv.getValueAt(i, 2));
            txtSDT.setText((String) tbsv.getValueAt(i, 3));
            String gioiTinh = (String) tbsv.getValueAt(i, 4);

            if (gioiTinh.equals("Nam")) {
                ckNam.setSelected(true);
                ckNu.setSelected(false);
            } else if (gioiTinh.equals("Nữ")) {
                ckNam.setSelected(false);
                ckNu.setSelected(true);
            } else {
                ckNam.setSelected(false);
                ckNu.setSelected(false);
            }
            txtDiaChi.setText((String) tbsv.getValueAt(i, 5));
            String hinh = (String) tbsv.getValueAt(i, 6);
            Uphinh(hinh);
            Rectangle rect = tbsv.getCellRect(index, 0, true);
            tbsv.scrollRectToVisible(rect);
        }
        updateInFo();
    }

    static void Uphinh(String hinh) {
        int width = 150; // Thay đổi kích thước mong muốn
        int height = 200;

        File file = new File(hinh);

        if (file.exists()) {
            ImageIcon img = new ImageIcon(hinh);
            Image im = img.getImage();
            ImageIcon icon = new ImageIcon(im.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            LBHinh.setIcon(icon);

        } else {
            System.out.println("File không tồn tại: " + hinh);
        }
    }

    static void TaiDuLieuLenArrayList() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            Statement stm = con.createStatement();
            String sql = "Select * from SinhVien";
            ResultSet rs = stm.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String MaNV = rs.getString(1);
                String HoTen = rs.getString(2);
                String Email = rs.getString(3);
                String SoDT = rs.getString(4);
                String DiaChi = rs.getString(5);
                boolean gt = rs.getBoolean(6);
                String Hinh = rs.getString(7);
                String Nganh = rs.getString(8);
                SinhVien sv = new SinhVien(MaNV, HoTen, Email, SoDT, DiaChi, gt, Hinh, Nganh);
                list.add(sv);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void First() {
        if (tbsv.getRowCount() > 0) {
            tbsv.setRowSelectionInterval(0, 0);
        }
    }

    static void Last() {
        int lastRow = tbsv.getRowCount() - 1;
        if (lastRow >= 0) {
            tbsv.setRowSelectionInterval(lastRow, lastRow);
        }
    }

    static void next() {
        int selectedRow = tbsv.getSelectedRow();
        if (selectedRow < tbsv.getRowCount() - 1 && selectedRow >= 0) {
            int nextRow = selectedRow + 1;
            tbsv.setRowSelectionInterval(nextRow, nextRow);
        }
    }

    static void pre() {
        int selectedRow = tbsv.getSelectedRow();
        if (selectedRow > 0) {
            int previousRow = selectedRow - 1;
            tbsv.setRowSelectionInterval(previousRow, previousRow);
        }
    }

    static boolean checkvalidate() {
        if (txtMaNv.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Mã sinh viên không được bỏ trống");
            return false;
        }
        if (txtHoTen.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(fr, "Tên không được chứa số");
            return false;
        }
        if (!txtEmail.getText().matches(checkemail)) {
            JOptionPane.showMessageDialog(fr, "Email không hợp lệ");
            return false;
        }
        if (!txtSDT.getText().matches("^(\\+84|0[0-9])[0-9]{8,9}$")) {
            JOptionPane.showMessageDialog(fr, "Số điện thoại không hợp lệ");
            return false;
        }

        return true;
    }

    static void addSV() {
        txtMaNv.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtMaNv.requestFocus();
        updateTable();

    }

    static void UpdateSV() {
        if (txtMaNv.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Nhập mã Sinh viên");
            txtMaNv.requestFocus();
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "update SinhVien set TenNV=?, Email=?, SoDT=?, "
                    + "DiaChi=?, GioiTinh=?, Hinh = ? ,Nganh = ? where MaNV = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtHoTen.getText());
            st.setString(2, txtEmail.getText());
            st.setString(3, txtSDT.getText());
            st.setString(4, txtDiaChi.getText());
            boolean gt;
            if (ckNam.isSelected()) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(5, gt);
            st.setString(6, list.get(index).getHinh()); // Lưu tên hình
            st.setString(7, cbNganh.getSelectedItem().toString());
            st.setString(8, txtMaNv.getText());
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Update Thành Công!");
            con.close();
            TaiDuLieuLenArrayList();
            updateTable();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static void DeleteSV() {
        if (txtMaNv.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Nhập mã Sinh viên cần xóa");
            txtMaNv.requestFocus();
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "delete SinhVien where MaNV = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, txtMaNv.getText());

            if (st.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(fr, "Xóa Thành Công!");
            } else {
                JOptionPane.showMessageDialog(fr, "Xóa không Thành Công!");
            }

            con.close();
            index = 0;
            TaiDuLieuLenArrayList();
            updateTable();

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static void SaveSV() {
        if (CheckTrung(txtMaNv.getText())) {
            JOptionPane.showMessageDialog(fr, "Mã Sinh viên đã tồn tại trong cơ sở dữ liệu.");
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "insert into SinhVien (TenNV, Email, SoDT, DiaChi, GioiTinh, Hinh, MaNV,Nganh) "
                    + "values (?, ?, ?, ?, ?, ?, ?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtHoTen.getText());
            st.setString(2, txtEmail.getText());
            st.setString(3, txtSDT.getText());
            st.setString(4, txtDiaChi.getText());
            boolean gt;
            if (ckNam.isSelected()) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(5, gt);

            // Lưu tên hình vào cơ sở dữ liệu
            st.setString(6, list.get(index).getHinh()); // Đường dẫn hình ảnh

            st.setString(7, txtMaNv.getText());
            st.setString(8, cbNganh.getSelectedItem().toString());
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Save Thành Công!");
            con.close();
            TaiDuLieuLenArrayList();
            updateTable();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static boolean CheckTrung(String maSV) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT MaNV FROM SinhVien WHERE MaNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maSV);

            ResultSet rs = stmt.executeQuery();

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

    static void generateReportByNganh(String nganh) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            // Truy vấn sinh viên theo ngành và thông tin liên quan từ các bảng
            String sql = "SELECT sv.*,DiemJava1,DiemJava2,DiemJava3\n"
                    + "from sinhvien sv\n"
                    + "inner join Diem d on d.MaNV = sv.MaNV\n"
                    + "where Nganh = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nganh);

            ResultSet rs = pstmt.executeQuery();

            // Tạo và mở file để ghi thông tin
            File file = new File("report_'" + nganh + "'.txt");

            FileWriter writer = new FileWriter(file);

            // Ghi thông tin của sinh viên vào file
            while (rs.next()) {

                String studentInfo
                        = ", " + rs.getString(1)
                        + ", " + rs.getString(2)
                        + ", " + rs.getString(3)
                        + ", " + rs.getString(4)
                        + ", " + rs.getString(5)
                        + ", " + (rs.getBoolean(6) ? "Nam" : "Nữ")
                        + ", " + rs.getString(7)
                        + ", " + rs.getString(8)
                        + ", " + rs.getFloat(9)
                        + ", " + rs.getFloat(10)
                        + ", " + rs.getFloat(11)
                        + "\n";
                writer.write(studentInfo);
            }
            // Đóng file sau khi ghi xong
            writer.close();
            Desktop.getDesktop().open(file);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        Frame();
        addCtn();
        Label();
        TextField();
        CheckBox();
        Button();
        Table();
        updateInFo();
        TaiDuLieuLenArrayList();
        HienThiDSlenFrame(index);
        XuLiSuKien();
        fr.setVisible(true);
    }
}
