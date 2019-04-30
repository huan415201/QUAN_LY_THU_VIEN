package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class tblphieumuonsach implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuMuon", updatable = false, nullable = false)
    private int MaPhieuMuon;
    
    private int MaTheDocGia;
    private Date NgayMuon;
    private boolean TrangThaiXoa;

    public tblphieumuonsach() {
    }

    public tblphieumuonsach(int MaPhieuMuon, int MaTheDocGia, Date NgayMuon, boolean TrangThaiXoa) {
        this.MaPhieuMuon = MaPhieuMuon;
        this.MaTheDocGia = MaTheDocGia;
        this.NgayMuon = NgayMuon;
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public int getMaPhieuMuon() {
        return MaPhieuMuon;
    }

    public void setMaPhieuMuon(int MaPhieuMuon) {
        this.MaPhieuMuon = MaPhieuMuon;
    }

    public int getMaTheDocGia() {
        return MaTheDocGia;
    }

    public void setMaTheDocGia(int MaTheDocGia) {
        this.MaTheDocGia = MaTheDocGia;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    public boolean isTrangThaiXoa() {
        return TrangThaiXoa;
    }

    public void setTrangThaiXoa(boolean TrangThaiXoa) {
        this.TrangThaiXoa = TrangThaiXoa;
    }

}
