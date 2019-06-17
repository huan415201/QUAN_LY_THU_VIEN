package tools;

import DAO.tblchitietphieumuonDAO;
import DAO.tblphieumuonsachDAO;
import entity.tblchitietphieumuon;
import java.util.ArrayList;
import java.util.List;

public class DataBeanList {

    public ArrayList<DataBean> getDataBeanList(int maTheDocGia) {
        ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

        int maPhieuMuonMoiNhat = tblphieumuonsachDAO.LayMaPhieuMuonMoiNhatTheoMaDocGia(maTheDocGia);
        List<tblchitietphieumuon> ctpm = tblchitietphieumuonDAO.LayCTPMTheoMaPM(maPhieuMuonMoiNhat);
        for (int i = 0; i < ctpm.size(); i++) {
            dataBeanList.add(produce(Integer.toString(ctpm.get(i).getMaPhieuMuon()), Integer.toString(ctpm.get(i).getMaSach()), ctpm.get(i).getTenSach()));
        }

        return dataBeanList;
    }

    private DataBean produce(String name, String country, String tenSach) {
        DataBean dataBean = new DataBean();
        dataBean.setName(name);
        dataBean.setCountry(country);
        dataBean.setTenSach(tenSach);
        return dataBean;
    }
}
