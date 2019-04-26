package entity;

import java.util.Date;

public class TheDocGia implements java.io.Serializable {

    private int MaTheDocGia;
    private int MaNhanVienLap;
    private String LoaiDocGia;
    private String HoTen;
    private String DiaChi;
    private String Email;
    private Date NgaySinh;
    private Date NgayLapThe;
    private int TienNo;
    private boolean TrangThaiXoa;

    public void setMaTheDocGia(int MaTheDocGia) {
        this.MaTheDocGia = MaTheDocGia;
    }

    public void setMaNhanVienLap(int MaNhanVienLap) {
        this.MaNhanVienLap = MaNhanVienLap;
    }

    public void setLoaiDocGia(String LoaiDocGia) {
        this.LoaiDocGia = LoaiDocGia;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setNgayLapThe(Date NgayLapThe) {
        this.NgayLapThe = NgayLapThe;
    }

    public void setTienNo(int TienNo) {
        this.TienNo = TienNo;
    }

    public void setTrangThaiXoa(boolean TrangThaiXoa) {
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public int getMaTheDocGia() {
        return MaTheDocGia;
    }

    public int getMaNhanVienLap() {
        return MaNhanVienLap;
    }

    public String getLoaiDocGia() {
        return LoaiDocGia;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public Date getNgayLapThe() {
        return NgayLapThe;
    }

    public int getTienNo() {
        return TienNo;
    }

    public boolean isTrangThaiXoa() {
        return TrangThaiXoa;
    }

}
