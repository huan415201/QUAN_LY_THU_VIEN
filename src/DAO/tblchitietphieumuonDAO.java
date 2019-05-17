/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.tblchitietphieumuon;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
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
}
