package ASM;

import java.io.Serializable;

public class SinhVienReport implements Serializable {

    private String MaSV;
    private String HoTen;
    private String Email;
    private String SoDT;
    private String DiaChi;
    private boolean GioiTinh;
    private double DiemJava1;
    private double DiemJava2;
    private double DiemJava3;
    private String Nganh;

    public SinhVienReport() {
    }

    public SinhVienReport(String MaSV, String HoTen, String Email, String SoDT, String DiaChi, boolean GioiTinh, double DiemJava1, double DiemJava2, double DiemJava3, String Nganh) {
        this.MaSV = MaSV;
        this.HoTen = HoTen;
        this.Email = Email;
        this.SoDT = SoDT;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;
        this.DiemJava1 = DiemJava1;
        this.DiemJava2 = DiemJava2;
        this.DiemJava3 = DiemJava3;
        this.Nganh = Nganh;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public double getDiemJava1() {
        return DiemJava1;
    }

    public void setDiemJava1(double DiemJava1) {
        this.DiemJava1 = DiemJava1;
    }

    public double getDiemJava2() {
        return DiemJava2;
    }

    public void setDiemJava2(double DiemJava2) {
        this.DiemJava2 = DiemJava2;
    }

    public double getDiemJava3() {
        return DiemJava3;
    }

    public void setDiemJava3(double DiemJava3) {
        this.DiemJava3 = DiemJava3;
    }

    public String getNganh() {
        return Nganh;
    }

    public void setNganh(String Nganh) {
        this.Nganh = Nganh;
    }
    
    
    @Override
    public String toString() {
        return "Mã SV: " + this.getMaSV() + ", Tên: " + this.getHoTen() + ", Email: " 
                + this.getEmail() + ", Số điện thoai: "+this.getSoDT() +"Giới tính: "+this.isGioiTinh()
                + "Điểm Java1: "+this.getDiemJava1()+ "Điểm Java2: "+this.getDiemJava2()+ "Điểm Java3: "+this.getDiemJava3() + "Ngành: "+this.getNganh();
        
    }

}
