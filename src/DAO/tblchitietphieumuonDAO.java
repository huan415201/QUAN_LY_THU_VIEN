package DAO;

import entity.tblchitietphieumuon;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

public class tblchitietphieumuonDAO {

    public static void ThemChiTieuPhieuMuon(tblchitietphieumuon t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public static void updateXoaMuon(String MaPhieuMuon) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        Query query = session.createSQLQuery(
                "update tblphieumuonsach set TrangThaiXoa =1 where MaPhieuMuon =:MaPhieuMuon");
        query.setParameter("MaPhieuMuon", MaPhieuMuon);
        query.executeUpdate();
        session.close();
    }

    public static List<tblchitietphieumuon> LayCTPMTheoMaPM(int maPhieuMuon) {
        List<tblchitietphieumuon> kq = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();

        String sql = "SELECT ctpm.*, s.TenSach FROM tblchitietphieumuon ctpm INNER JOIN tblsach s ON ctpm.MaSach=s.MaSach where ctpm.MaPhieuMuon=:m";
        SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("m", maPhieuMuon);
        query.addEntity(tblchitietphieumuon.class);
        kq = query.list();
        session.close();
        return kq;
    }
}
