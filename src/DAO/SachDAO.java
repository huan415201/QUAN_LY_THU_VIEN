package DAO;

import entity.ThuanSach;
import org.hibernate.Session;
import util.HibernateUtil;

public class SachDAO {
    public static int ThemSach(ThuanSach sach) {
        int id = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            id = (int) session.save(sach);
            System.out.println("id moi: " + id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            return id;
        }
    }
}
