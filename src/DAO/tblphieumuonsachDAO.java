/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.tblphieumuonsach;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hau
 */
public class tblphieumuonsachDAO {
    public static String[] layDSPhieuMuonSachBangMaTheDocGia(String MaTheDocGia) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblphieumuonsach where MaTheDocGia =:m and TrangThaiXoa = 0";
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
}
