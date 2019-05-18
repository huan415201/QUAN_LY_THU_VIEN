/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserTableModel;

import entity.ThuanSach;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hau
 */
public class SachTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 6105842825518764825L;
    private ArrayList<ThuanSach> PersonList;

    public SachTableModel(String sql) {
        super();
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        Query q = session.createSQLQuery(sql).addEntity(ThuanSach.class);
        
        List tmp = q.list();
        PersonList = new ArrayList<ThuanSach>();
        for (int i = 0; i < tmp.size(); i++) {
            PersonList.add((ThuanSach) tmp.get(i));
        }
        
        session.close();
        sf.close();
    }

    public int getRowCount() {
        return PersonList.size();
    }

    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ThuanSach p = PersonList.get(rowIndex);
        String TinhTrangSach = "";
        if(p.getTrangThaiXoa() == 0){
            TinhTrangSach = "Đang sữ dụng";
        }else{
            TinhTrangSach = "Hết sữ dụng";
        }
        Object[] values = new Object[]{rowIndex+1, p.getMaSach(), p.getTenSach(), p.getTheLoai(), p.getTacGia(), TinhTrangSach};
        return values[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = new String[]{"STT", "Mã Sách", "Tên Sach", "Thể Loại", "Tác gia", "Tình Trạng"};
        return columnNames[column];
    }
}
