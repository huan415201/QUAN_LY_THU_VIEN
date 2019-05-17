/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThuanNhanVien;
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
}
