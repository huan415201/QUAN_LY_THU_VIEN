/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.tblphieumuonsach;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
public class tblphieumuonsachDAO {

    public static String[] layDSKiemTraNgayMuon(String MaTheDocGia, Date now) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblphieumuonsach m inner join tblchitietphieumuon ct on m.MaPhieuMuon = ct.MaPhieuMuon where m.MaTheDocGia =:m and m.TrangThaiXoa = 0 and DATEDIFF(:o,m.NgayMuon) >4";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("m", MaTheDocGia);
        query.setParameter("o", now);
        query.addEntity(tblphieumuonsach.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            tblphieumuonsach employee = (tblphieumuonsach) iterator.next();
            lst.add(String.valueOf(employee.getMaPhieuMuon()));
        }
        String[] lstDSMON = lst.toArray(new String[lst.size()]);
        session.close();
        return lstDSMON;
    }

    public static String[] layDSPhieuMuonSachBangMaTheDocGia(String MaTheDocGia, Date now) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblphieumuonsach m inner join tblchitietphieumuon ct on m.MaPhieuMuon = ct.MaPhieuMuon where m.MaTheDocGia =:m and m.TrangThaiXoa = 0";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("m", MaTheDocGia);
        query.addEntity(tblphieumuonsach.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            tblphieumuonsach employee = (tblphieumuonsach) iterator.next();
            lst.add(String.valueOf(employee.getMaPhieuMuon()));
        }
        String[] lstDSMON = lst.toArray(new String[lst.size()]);
        session.close();
        return lstDSMON;
    }

    public static int themPhieuMuon(tblphieumuonsach t) {
        int id = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            id = (int) session.save(t);
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
