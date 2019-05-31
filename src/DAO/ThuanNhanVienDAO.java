/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.ThuanNhanVien;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

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
    
    public static String[] layDanhSachTenNhanVien() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblnhanvien";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(ThuanNhanVien.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            ThuanNhanVien employee = (ThuanNhanVien) iterator.next();
            lst.add(String.valueOf(employee.getHoTen()));
        }
        String[] lstDSMON = lst.toArray(new String[lst.size()]);
        session.close();
        return lstDSMON;
    }
    
    public static String LayMatKhauBangMaNhanVien(int maNhanVien) {
        String matKhau = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "SELECT * FROM tblnhanvien WHERE MaNhanVien =:m";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("m", maNhanVien);
        query.addEntity(ThuanNhanVien.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            ThuanNhanVien employee = (ThuanNhanVien) iterator.next();
            matKhau = employee.getMatKhau();
        }
        session.close();
        return matKhau;
    }
}
