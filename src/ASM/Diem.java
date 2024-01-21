package ASM;

import ASM.*;
import java.text.DecimalFormat;

public class Diem {

    private String MaSV;
    private String HoTen;
    private double DiemJava1;
    private double DiemJava2;
    private double DiemJava3;

    public Diem() {
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

    public Diem(String MaSV, String HoTen, double DiemJava1, double DiemJava2, double DiemJava3) {
        this.MaSV = MaSV;
        this.HoTen = HoTen;
        this.DiemJava1 = DiemJava1;
        this.DiemJava2 = DiemJava2;
        this.DiemJava3 = DiemJava3;
    }
   
    public double getDiemTB() {
        double diemTB = (getDiemJava1() + getDiemJava2() + getDiemJava3()) / 3;
        // Định dạng kết quả để giữ hai chữ số thập phân
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(diemTB));
    }

}
