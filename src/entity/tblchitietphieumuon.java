package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class tblchitietphieumuon implements java.io.Serializable {

    @Id
    @Column(name = "MaPhieuMuon", updatable = false, nullable = false)
    private int MaPhieuMuon;

    @Id
    @Column(name = "MaSach", updatable = false, nullable = false)
    private int MaSach;
    
    private String TenSach;

    public tblchitietphieumuon(int MaPhieuMuon, int MaSach) {
        this.MaPhieuMuon = MaPhieuMuon;
        this.MaSach = MaSach;
    }

    public tblchitietphieumuon() {
        super();
    }

    public int getMaPhieuMuon() {
        return MaPhieuMuon;
    }

    public void setMaPhieuMuon(int MaPhieuMuon) {
        this.MaPhieuMuon = MaPhieuMuon;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

     public String getTenSach() {
        return TenSach;
    }
     
     public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }
    
}
