/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserTableModel;

import entity.ThuanNhanVien;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;

public class ThuanTableModelNhanVien extends AbstractTableModel {

    private static final long serialVersionUID = 6105842825518764825L;
    private ArrayList<ThuanNhanVien> PersonList;

    public ThuanTableModelNhanVien(String sql) {
        super();
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        Query q = session.createSQLQuery(sql).addEntity(ThuanNhanVien.class);
        
        List tmp = q.list();
        PersonList = new ArrayList<ThuanNhanVien>();
        for (int i = 0; i < tmp.size(); i++) {
            PersonList.add((ThuanNhanVien) tmp.get(i));
        }
        
        session.close();
        sf.close();
    }

    public int getRowCount() {
        return PersonList.size();
    }

    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ThuanNhanVien p = PersonList.get(rowIndex);
        Object[] values = new Object[]{rowIndex+1, p.getHoTen(), p.getNgaySinh(), p.getLoaiBangCap(), p.getChucVu(), p.getDiaChi(), p.getSoDienThoai(), p.getBoPhan()};
        return values[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = new String[]{"STT", "Họ Tên", "NgàySinh", "Loại Bằng Cấp", "Chức vụ", "Địa Chỉ", "Số Điện Thoại", "Bộ Phận"};
        return columnNames[column];
    }
}
