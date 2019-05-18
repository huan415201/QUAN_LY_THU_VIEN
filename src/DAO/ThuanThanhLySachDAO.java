/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThuanThanhLySach;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
public class ThuanThanhLySachDAO {
    public static int themPhieuThanhLy(ThuanThanhLySach t) {
        int id = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            id = (int) session.save(t);
            // System.out.println("id moi: " + id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            return id;
        }
    }
    public static List<Object[]> layDanhSachDaThanhLy(String sql) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> o = query.list();

        session.close();
        return o;
    }
}
