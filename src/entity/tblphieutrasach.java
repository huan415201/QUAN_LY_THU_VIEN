package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class tblphieutrasach implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuTra", updatable = false, nullable = false)
    private int MaPhieuTra;
    private int MaTheDocGia;
    private Date NgayTra;
    private int TienPhat;
    private boolean TrangThaiXoa;

    public tblphieutrasach() {
    }

    public tblphieutrasach(int MaPhieuTra, int MaTheDocGia, Date NgayTra, int TienPhat, boolean TrangThaiXoa) {
        this.MaPhieuTra = MaPhieuTra;
        this.MaTheDocGia = MaTheDocGia;
        this.NgayTra = NgayTra;
        this.TienPhat = TienPhat;
        this.TrangThaiXoa = TrangThaiXoa;
    }

    public int getMaPhieuTra() {
        return MaPhieuTra;
    }

    public void setMaPhieuTra(int MaPhieuTra) {
        this.MaPhieuTra = MaPhieuTra;
    }

    public int getMaTheDocGia() {
        return MaTheDocGia;
    }

    public void setMaTheDocGia(int MaTheDocGia) {
        this.MaTheDocGia = MaTheDocGia;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    public int getTienPhat() {
        return TienPhat;
    }

    public void setTienPhat(int TienPhat) {
        this.TienPhat = TienPhat;
    }

    public boolean isTrangThaiXoa() {
        return TrangThaiXoa;
    }

    public void setTrangThaiXoa(boolean TrangThaiXoa) {
        this.TrangThaiXoa = TrangThaiXoa;
    }

}
