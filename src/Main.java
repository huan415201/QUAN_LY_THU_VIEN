
import DAO.TheDocGiaDAO;
import entity.TheDocGia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParseException {
        ArrayList<TheDocGia> dsDocGia = TheDocGiaDAO.LayDanhSachTheDocGia();
        for (int i = 0; i < dsDocGia.size(); i++) {
            System.out.println("Họ tên: " + dsDocGia.get(i).getHoTen());
        }
        
        TheDocGia the = new TheDocGia();
        the.setMaNhanVienLap(1);
        the.setLoaiDocGia("A");
        the.setHoTen("Thất Lạc");
        the.setDiaChi("5 đường số 5 P.5 Q.5");
        the.setEmail("lac@gmail.com");
        the.setNgaySinh(new SimpleDateFormat("dd-MM-YYYY").parse("22-01-1990"));
        the.setNgayLapThe(new SimpleDateFormat("dd-MM-YYYY").parse("23-01-1990"));
        the.setTienNo(0);
        the.setTrangThaiXoa(false);
        
        int ketQuaThem = TheDocGiaDAO.ThemTheDocGia(the);
        if (ketQuaThem == 1) {
            System.out.println("Thêm thành công.");
        }
    }
}
