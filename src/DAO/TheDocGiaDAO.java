package DAO;

import entity.TheDocGia;
import java.util.ArrayList;
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery("INSERT INTO tblthedocgia (MaNhanVienLap, LoaiDocGia, HoTen, DiaChi, Email, NgaySinh, NgayLapThe, TienNo, TrangThaiXoa)"
                + "VALUES (:MaNhanVienLap, :LoaiDocGia, :HoTen, :DiaChi, :Email, :NgaySinh, :NgayLapThe, :TienNo, :TrangThaiXoa)");
        query.setParameter("MaNhanVienLap", the.getMaNhanVienLap());
        query.setParameter("LoaiDocGia", the.getLoaiDocGia());
        query.setParameter("HoTen", the.getHoTen());
        query.setParameter("DiaChi", the.getDiaChi());
        query.setParameter("Email", the.getEmail());
        query.setParameter("NgaySinh", the.getNgaySinh());
        query.setParameter("NgayLapThe", the.getNgayLapThe());
        query.setParameter("TienNo", the.getTienNo());
        query.setParameter("TrangThaiXoa", false);
        return query.executeUpdate();
    }
    
    public static String[] layDanhSachTenDocGia() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArrayList<String> lst = new ArrayList<>();
		
		session.beginTransaction();
		String sql = "SELECT * FROM tblthedocgia";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TheDocGia.class);
		List employees = query.list();
		// System.out.println("size employees" + employees.iterator());

		// System.out.println("Loi ngay day");
		for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
			TheDocGia employee = (TheDocGia) iterator.next();
			System.out.println(employee.getMaTheDocGia());
			lst.add(String.valueOf(employee.getMaTheDocGia()));

			
			
			// lstDSMON.add(employee.getMaMonHoc());

			/*
			 * System.out.print("First Name: " + employee.getMaMonHoc());
			 * System.out.print("  Last Name: " + employee.getTenMonHoc());
			 * System.out.println("  Salary: " + employee.getThuTrongTuan());
			 */
		}
		String[] lstDSMON = lst.toArray(new String[lst.size()]);
		session.close();
		return lstDSMON;
	}
}
