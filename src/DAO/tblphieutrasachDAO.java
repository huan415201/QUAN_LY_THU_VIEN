/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.tblphieutrasach;
import org.hibernate.Session;
import util.HibernateUtil;

public class tblphieutrasachDAO {
    public static int themPhieuTra(tblphieutrasach t) {
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
