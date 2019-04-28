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
public class ThuanSach {
    private int MaSach;
    private int MaNhanVienTiepNhan;
    private String TenSach;
    private String TheLoai;
    private String TacGia;
    private int  NamXuatBan;
    private String NhaXuatBan;
    private Date  NgayNhap;
    private int TriGia;
    private int TrangThaiXoa;

    public int getMaSach() {
        return MaSach;
    }

    public int getMaNhanVienTiepNhan() {
        return MaNhanVienTiepNhan;
    }

    public String getTenSach() {
        return TenSach;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public String getTacGia() {
        return TacGia;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public int getTriGia() {
        return TriGia;
    }

    public int getTrangThaiXoa() {
        return TrangThaiXoa;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public void setMaNhanVienTiepNhan(int MaNhanVienTiepNhan) {
        this.MaNhanVienTiepNhan = MaNhanVienTiepNhan;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public void setNamXuatBan(int NamXuatBan) {
        this.NamXuatBan = NamXuatBan;
    }

    public void setNhaXuatBan(String NhaXuatBan) {
        this.NhaXuatBan = NhaXuatBan;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public void setTriGia(int TriGia) {
        this.TriGia = TriGia;
    }

    public void setTrangThaiXoa(int TrangThaiXoa) {
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public ThuanSach(int MaSach, int MaNhanVienTiepNhan, String TenSach, String TheLoai, String TacGia, int NamXuatBan, String NhaXuatBan, Date NgayNhap, int TriGia, int TrangThaiXoa) {
        this.MaSach = MaSach;
        this.MaNhanVienTiepNhan = MaNhanVienTiepNhan;
        this.TenSach = TenSach;
        this.TheLoai = TheLoai;
        this.TacGia = TacGia;
        this.NamXuatBan = NamXuatBan;
        this.NhaXuatBan = NhaXuatBan;
        this.NgayNhap = NgayNhap;
        this.TriGia = TriGia;
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public ThuanSach() {
        super();
    }
}
