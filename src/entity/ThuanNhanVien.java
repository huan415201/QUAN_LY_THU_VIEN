/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author hau
 */
public class ThuanNhanVien {

    private int MaNhanVien;
    private String MatKhau;
    private String BoPhan;
    private String ChucVu;
    private String LoaiBangCap;
    private String HoTen;
    private String DiaChi;
    private Date NgaySinh;
    private String SoDienThoai;
    private boolean TrangThaiXoa;

    public ThuanNhanVien() {
    }

    public ThuanNhanVien(int MaNhanVien, String MatKhau, String BoPhan, String ChucVu, String LoaiBangCap, String HoTen, String DiaChi, Date NgaySinh, String SoDienThoai, boolean TrangThaiXoa) {
        this.MaNhanVien = MaNhanVien;
        this.MatKhau = MatKhau;
        this.BoPhan = BoPhan;
        this.ChucVu = ChucVu;
        this.LoaiBangCap = LoaiBangCap;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.SoDienThoai = SoDienThoai;
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getBoPhan() {
        return BoPhan;
    }

    public void setBoPhan(String BoPhan) {
        this.BoPhan = BoPhan;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getLoaiBangCap() {
        return LoaiBangCap;
    }

    public void setLoaiBangCap(String LoaiBangCap) {
        this.LoaiBangCap = LoaiBangCap;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public boolean isTrangThaiXoa() {
        return TrangThaiXoa;
    }

    public void setTrangThaiXoa(boolean TrangThaiXoa) {
        this.TrangThaiXoa = TrangThaiXoa;
    }
    
}
