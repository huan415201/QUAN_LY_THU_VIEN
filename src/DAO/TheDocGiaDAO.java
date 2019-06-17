package DAO;

import entity.TheDocGia;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

public class TheDocGiaDAO {

    public static ArrayList<TheDocGia> LayDanhSachTheDocGia() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * FROM tblthedocgia WHERE TrangThaiXoa=0").addEntity(TheDocGia.class);
        List tmp = query.list();
        ArrayList<TheDocGia> dsDocGia = new ArrayList<>();
        for (int i = 0; i < tmp.size(); i++) {
            dsDocGia.add((TheDocGia) tmp.get(i));
        }
        return dsDocGia;

//        List temp = query.list();
//        for (Iterator ite = tmp.iterator(); ite.hasNext();) {
//            TheDocGia t = (TheDocGia) ite.next();
//            dsDocGia.add(t);
//        } 
    }

    public static int ThemTheDocGia(TheDocGia the) {
        int id = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            id = (int) session.save(the);
            System.out.println("id moi: " + id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            return id;
        }
//
//        Query query = session.createSQLQuery("INSERT INTO tblthedocgia (MaNhanVienLap, LoaiDocGia, HoTen, DiaChi, Email, NgaySinh, NgayLapThe, TienNo, TrangThaiXoa)"
//                + "VALUES (:MaNhanVienLap, :LoaiDocGia, :HoTen, :DiaChi, :Email, :NgaySinh, :NgayLapThe, :TienNo, :TrangThaiXoa)");
//        query.setParameter("MaNhanVienLap", the.getMaNhanVienLap());
//        query.setParameter("LoaiDocGia", the.getLoaiDocGia());
//        query.setParameter("HoTen", the.getHoTen());
//        query.setParameter("DiaChi", the.getDiaChi());
//        query.setParameter("Email", the.getEmail());
//        query.setParameter("NgaySinh", the.getNgaySinh());
//        query.setParameter("NgayLapThe", the.getNgayLapThe());
//        query.setParameter("TienNo", the.getTienNo());
//        query.setParameter("TrangThaiXoa", false);
//        return query.executeUpdate();
    }

    public static String[] layDanhSachMaTheDocGia() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblthedocgia";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(TheDocGia.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            TheDocGia employee = (TheDocGia) iterator.next();
            lst.add(String.valueOf(employee.getMaTheDocGia()));
        }
        String[] lstDSMON = lst.toArray(new String[lst.size()]);
        session.close();
        return lstDSMON;
    }

    public static String[] layDanhSachTenDocGia() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<String> lst = new ArrayList<>();

        session.beginTransaction();
        String sql = "SELECT * FROM tblthedocgia";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(TheDocGia.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            TheDocGia employee = (TheDocGia) iterator.next();
            lst.add(String.valueOf(employee.getHoTen()));
        }
        String[] lstDSMON = lst.toArray(new String[lst.size()]);
        session.close();
        return lstDSMON;
    }

    public static Date layNgayLapBangMaThe(String MaThe) {

        Date d = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String sql = "SELECT * FROM tblthedocgia where MaTheDocGia =:o";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("o", MaThe);
        query.addEntity(TheDocGia.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            TheDocGia employee = (TheDocGia) iterator.next();
            d = employee.getNgayLapThe();
        }
        session.close();
        return d;
    }

    public static int layTienNoBangMaThe(String MaThe) {

        int d = 0;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String sql = "SELECT * FROM tblthedocgia where MaTheDocGia =:o";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("o", MaThe);
        query.addEntity(TheDocGia.class);
        List employees = query.list();
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            TheDocGia employee = (TheDocGia) iterator.next();
            d = employee.getTienNo();
        }
        session.close();
        return d;
    }

    public static void updateTienNo(String MaTheDocGia, String TienNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createSQLQuery(
                "update tblthedocgia set TienNo =:TienNo where MaTheDocGia =:MaTheDocGia");
        query.setParameter("TienNo", TienNo);
        query.setParameter("MaTheDocGia", MaTheDocGia);
        query.executeUpdate();
        session.close();
    }

    public static void updateTrangThaiXoa(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createSQLQuery(
                "update tblthedocgia set TrangThaiXoa=1 where Email =:Email");
        query.setParameter("Email", email);
        query.executeUpdate();
        session.close();
    }

    public static void updateTheDocGia(TheDocGia the, String emailCu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createSQLQuery(
                "UPDATE tblthedocgia SET LoaiDocGia=:LoaiDocGia, HoTen=:HoTen, DiaChi=:DiaChi, Email=:EmailMoi, NgaySinh=:NgaySinh WHERE Email=:EmailCu");
        query.setParameter("LoaiDocGia", the.getLoaiDocGia());
        query.setParameter("HoTen", the.getHoTen());
        query.setParameter("DiaChi", the.getDiaChi());
        query.setParameter("EmailMoi", the.getEmail());
        query.setParameter("NgaySinh", the.getNgaySinh());
        query.setParameter("EmailCu", emailCu);
        query.executeUpdate();
        session.close();
    }
}
