/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserTableModel;

import entity.TheDocGia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TheDocGiaTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 6105842825518764825L;
    private ArrayList<TheDocGia> PersonList;

    public TheDocGiaTableModel(String sql) {
        super();
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        Query q = session.createSQLQuery(sql).addEntity(TheDocGia.class);
        
        List tmp = q.list();
        PersonList = new ArrayList<TheDocGia>();
        for (int i = 0; i < tmp.size(); i++) {
            PersonList.add((TheDocGia) tmp.get(i));
        }
        
        session.close();
        sf.close();
    }

    public int getRowCount() {
        return PersonList.size();
    }

    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TheDocGia p = PersonList.get(rowIndex);
        Object[] values = new Object[]{rowIndex+1, p.getHoTen(), p.getLoaiDocGia(), p.getDiaChi(), p.getEmail(), p.getNgaySinh(), p.getNgayLapThe()};
        return values[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = new String[]{"STT", "Họ Tên", "Loại Đọc Giả", "Địa Chỉ", "Email", "Ngày Sinh", "Ngày Lập Thẻ"};
        return columnNames[column];
    }
}
