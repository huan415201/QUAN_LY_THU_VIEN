/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class ThuanChiTietThanhLy implements java.io.Serializable{
   private int MaPhieuThanhLy;
   private int MaSach;
   private String LyDoThanhLy;

    public ThuanChiTietThanhLy(int MaPhieuThanhLy, int MaSach, String LyDoThanhLy) {
        this.MaPhieuThanhLy = MaPhieuThanhLy;
        this.MaSach = MaSach;
        this.LyDoThanhLy = LyDoThanhLy;
    }

    public ThuanChiTietThanhLy() {
    }

    public int getMaPhieuThanhLy() {
        return MaPhieuThanhLy;
    }

    public void setMaPhieuThanhLy(int MaPhieuThanhLy) {
        this.MaPhieuThanhLy = MaPhieuThanhLy;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public String getLyDoThanhLy() {
        return LyDoThanhLy;
    }

    public void setLyDoThanhLy(String LyDoThanhLy) {
        this.LyDoThanhLy = LyDoThanhLy;
    }
   
   
}
