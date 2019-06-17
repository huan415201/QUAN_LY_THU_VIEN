package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class mainmain {

    public static void main() {
        JasperReport jasperReport;
        int maTheDocGia = 0;
        try {
            jasperReport = JasperCompileManager
                    .compileReport("D:\\Study_Files\\Lien_Thong_Dai_Hoc\\Hoc_Ky_4\\Java\\Do_An\\QUAN_LY_THU_VIEN\\src\\tools\\jasper_report_template.jrxml");

            Map parameters = new HashMap();

            parameters.put("ReportTitle", "bao cao doanh thu theo ngay");
            parameters.put("Thang", "aaa");

            JasperPrint jasperPrint = null;

            DataBeanList DataBeanList = new DataBeanList();
            ArrayList<DataBean> dataList = DataBeanList.getDataBeanList(maTheDocGia);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

            JasperViewer.viewReport(jasperPrint, false);

            System.out.println("Done!");
        } catch (JRException e1) {
            e1.printStackTrace();
        }

    }
}
