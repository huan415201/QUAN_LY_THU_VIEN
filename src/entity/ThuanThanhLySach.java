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
public class ThuanThanhLySach {

    private int MaPhieuThanhLy;
    private int MaNhanVienLap;
    private Date NgayThanhLy;
    private String SoDienThoai;
    private boolean TrangThaiXoa;

    public ThuanThanhLySach() {
    }

    public int getMaPhieuThanhLy() {
        return MaPhieuThanhLy;
    }

    public void setMaPhieuThanhLy(int MaPhieuThanhLy) {
        this.MaPhieuThanhLy = MaPhieuThanhLy;
    }

    public int getMaNhanVienLap() {
        return MaNhanVienLap;
    }

    public void setMaNhanVienLap(int MaNhanVienLap) {
        this.MaNhanVienLap = MaNhanVienLap;
    }

    public Date getNgayThanhLy() {
        return NgayThanhLy;
    }

    public void setNgayThanhLy(Date NgayThanhLy) {
        this.NgayThanhLy = NgayThanhLy;
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

    public ThuanThanhLySach(int MaPhieuThanhLy, int MaNhanVienLap, Date NgayThanhLy, String SoDienThoai, boolean TrangThaiXoa) {
        this.MaPhieuThanhLy = MaPhieuThanhLy;
        this.MaNhanVienLap = MaNhanVienLap;
        this.NgayThanhLy = NgayThanhLy;
        this.SoDienThoai = SoDienThoai;
        this.TrangThaiXoa = TrangThaiXoa;
    }

    
}
