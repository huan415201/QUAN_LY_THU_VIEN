/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThuanChiTietThanhLy;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
public class ThuanChiTietThanhLyDAO {

    public static void themChiTietPhieuThanhLy(ThuanChiTietThanhLy t) {
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

    public static int ThemCTThanhLy(ThuanChiTietThanhLy t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery("INSERT INTO tblchitietthanhly"
                + " VALUES (:MaPhieuThanhLy, :MaSach, :LyDoThanhLy)");
        query.setParameter("MaPhieuThanhLy", t.getMaPhieuThanhLy());
        query.setParameter("MaSach", t.getMaSach());
        query.setParameter("LyDoThanhLy", t.getLyDoThanhLy());
        return query.executeUpdate();
    }
}
