/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThuanNhanVien;
import java.util.Iterator;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
public class ThuanNhanVienDAO {

    public static void ThemNhanVien(ThuanNhanVien t) {
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
    
    public static ThuanNhanVien layNhanVienBangMaNhanVien(int MaNhanVien) {
        ThuanNhanVien t = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String sql = "SELECT * FROM tblnhanvien where MaNhanVien =:m";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("m", MaNhanVien);
        query.addEntity(ThuanNhanVien.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            t = (ThuanNhanVien) iterator.next();
        }
        session.close();
        return t;
    }
}
