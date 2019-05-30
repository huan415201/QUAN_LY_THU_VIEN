package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class tblchitietphieutra implements java.io.Serializable {

    @Id
    @Column(name = "MaPhieuTra", updatable = false, nullable = false)
    private int MaPhieuTra;

    @Id
    @Column(name = "MaSach", updatable = false, nullable = false)
    private int MaSach;

    public int getMaPhieuTra() {
        return MaPhieuTra;
    }

    public void setMaPhieuTra(int MaPhieuTra) {
        this.MaPhieuTra = MaPhieuTra;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

}
