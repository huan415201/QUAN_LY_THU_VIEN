
import DAO.TheDocGiaDAO;
import DAO.ThuanChiTietThanhLyDAO;
import DAO.ThuanNhanVienDAO;
import DAO.ThuanThanhLySachDAO;
import DAO.tblchitietphieumuonDAO;
import DAO.tblchitietphieutrasachDAO;
import DAO.tblphieumuonsachDAO;
import DAO.tblphieutrasachDAO;
import UserTableModel.SachTableModel;
import UserTableModel.ThuanTableModelNhanVien;
import entity.ThuanChiTietThanhLy;
import entity.ThuanNhanVien;
import entity.ThuanThanhLySach;
import entity.tblchitietphieumuon;
import entity.tblchitietphieutra;
import entity.tblphieumuonsach;
import entity.tblphieutrasach;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import tools.TheDocGiaComboModel;
import tools.TinhThang;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hau
 */
public class frmGUI extends javax.swing.JFrame {

    public static String bundlea = "abc";
    /**
     * Creates new form frmTimTraMuonSach
     *
     */
    TableModel tableModel = createTableModel();

    DefaultTableModel dm = new DefaultTableModel();

    String[] dsTheDocGia = TheDocGiaDAO.layDanhSachTenDocGia();
    TheDocGiaComboModel cbModelTheDocGia;

    TableModel tmChon = createTableModelSachChuaMuon();
    String[] dsMaTheDocGia = TheDocGiaDAO.layDanhSachMaTheDocGia();
    int SoSachDaMuon = 0;
    String[] dsTheMuonBangMaTheDocGia = null;
    Boolean coTraCoPhieuMuonSach = false;
    //phieu tra
    TheDocGiaComboModel cbModelPhieuTraSachHoTen = new TheDocGiaComboModel(dsTheDocGia);

    //Thanh ly sach
    int MaNhanVienDangNhap = 0;
    ThuanNhanVien ThuanNhanVienDangNhap = null;

    public frmGUI(int MaNhanVien) {
        this.MaNhanVienDangNhap = MaNhanVien;
        ThuanNhanVienDangNhap = ThuanNhanVienDAO.layNhanVienBangMaNhanVien(MaNhanVienDangNhap);
        cbModelTheDocGia = new TheDocGiaComboModel(dsTheDocGia);
        dm.setColumnIdentifiers(new Object[]{"STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả"});
        initComponents();
        txtLoi.setVisible(false);
        Date date = new Date();
        dtpNgayMuon.setFormats(new String[]{"dd/MM/yyyy"});
        dtpNgayMuon.setDate(date);
        tbMuon.setDefaultEditor(Object.class, null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Hien sach con lai
        String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], new Date());
        SoSachDaMuon = DSPhieuMuonHienTai.length;
        txtSoSachConLai.setText(String.valueOf(SoSachDaMuon));

        cbbMaTheDocGia.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (kiemTraHan()) {
                    txtSoSachConLai.setText("0");
                    txtSoSachDangChon.setText("0");
                    return;
                }
                if (kiemTra4Ngay()) {
                    return;
                }
                txtSoSachDangChon.setText(String.valueOf(tbMuon.getRowCount()));
                JComboBox comboBox = (JComboBox) event.getSource();

                String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], new Date());
                SoSachDaMuon = DSPhieuMuonHienTai.length;
                txtSoSachConLai.setText(String.valueOf(DSPhieuMuonHienTai.length + dm.getRowCount()));
            }
        });

        //Phieu tra
        dtpPhieuTraSachNgayTra.setFormats(new String[]{"dd/MM/yyyy"});
        dtpPhieuTraSachNgayTra.setDate(new Date());

        TheDocGiaComboModel combomodeldsTheMuonBangMaTheDocGia;
        dsTheMuonBangMaTheDocGia = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGiaDistinct(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]);
        if (dsTheMuonBangMaTheDocGia.length == 0) {
            txtTraSachLoi.setVisible(true);
            txtTraSachLoi.setForeground(Color.red);
            txtTraSachLoi.setText("Đọc giả không có phiếu mượn");
            coTraCoPhieuMuonSach = false;
//            System.out.println("Tien no: " + TheDocGiaDAO.layTienNoBangMaThe(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]));
            txtTienNo.setText(String.valueOf(TheDocGiaDAO.layTienNoBangMaThe(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()])));
            combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
            cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);
            btnTraSach.setVisible(false);
        } else {
            btnTraSach.setVisible(true);
            coTraCoPhieuMuonSach = true;
            txtTraSachLoi.setVisible(false);
            txtTraSachLoi.setForeground(Color.green);
            txtTraSachLoi.setText("");

            combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
            cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);
            int TienPhatKyNay = 0;
            DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
            int i = 1;
            List<Object[]> o = tblphieumuonsachDAO.layMaSachNgayMuonByMaPhieuMuon(cbbPhieuTraPhieuMuon.getSelectedItem().toString());
            for (Object[] countResult : o) {
                System.out.println("NgayMuon:" + countResult[0] + "MaSach:" + countResult[1] + " TieNo: " + countResult[2]);
                //TableModel n = new UserTableModel.SachTableModel("select * from tblsach");
                txtTienNo.setText(countResult[1].toString());

                Date NgayMuonNew = (Date) countResult[0];
                long diff = new Date().getTime() - NgayMuonNew.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                long TienPhat = 0;
                if (diffDays > 4) {
                    TienPhat = (diffDays - 4) * 1000;
                }
                TienPhatKyNay += TienPhat;
                modelTraSach.addRow(new Object[]{i, countResult[1], countResult[0], String.valueOf(diffDays), String.valueOf(TienPhat)});
                i++;
            }
            txtTienPhatKyNay.setText(String.valueOf(TienPhatKyNay));
            int TongNo = Integer.parseInt(txtTienPhatKyNay.getText()) + Integer.parseInt(txtTienNo.getText());
            txtTongNo.setText(String.valueOf(TongNo));
            tbPhieuTraSach.setModel(modelTraSach);
        }

        //Su kien phieutrasach combo
        //Combo 1 doi
        cbbPhieuTraSachHoTen.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                TheDocGiaComboModel combomodeldsTheMuonBangMaTheDocGia;
                dsTheMuonBangMaTheDocGia = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGiaDistinct(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]);
                if (dsTheMuonBangMaTheDocGia.length == 0) {
                    txtTraSachLoi.setVisible(true);
                    txtTraSachLoi.setForeground(Color.red);
                    txtTraSachLoi.setText("Đọc giả không có phiếu mượn");
                    coTraCoPhieuMuonSach = false;
                    combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
                    cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);
                    DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
                    tbPhieuTraSach.setModel(modelTraSach);

                    txtTienPhatKyNay.setText("0");
                    txtTienNo.setText(String.valueOf(TheDocGiaDAO.layTienNoBangMaThe(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()])));
                    int TongNo = Integer.parseInt(txtTienPhatKyNay.getText()) + Integer.parseInt(txtTienNo.getText());
                    txtTongNo.setText(String.valueOf(TongNo));
                    btnTraSach.setVisible(false);
                } else {
                    btnTraSach.setVisible(true);
                    txtTraSachLoi.setVisible(false);
                    txtTraSachLoi.setForeground(Color.green);
                    txtTraSachLoi.setText("");
                    coTraCoPhieuMuonSach = true;
                    int TienPhatKyNay = 0;
                    combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
                    cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);

                    DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
                    int i = 1;
                    List<Object[]> o = tblphieumuonsachDAO.layMaSachNgayMuonByMaPhieuMuon(cbbPhieuTraPhieuMuon.getSelectedItem().toString());
                    for (Object[] countResult : o) {
                        System.out.println("NgayMuon:" + countResult[0] + "MaSach:" + countResult[1]);
                        txtTienNo.setText(countResult[2].toString());
                        Date NgayMuon = (Date) countResult[0];
                        long diff = new Date().getTime() - NgayMuon.getTime();
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        long TienPhat = 0;
                        if (diffDays > 4) {
                            TienPhat = (diffDays - 4) * 1000;
                        }
                        TienPhatKyNay += TienPhat;
                        //TableModel n = new UserTableModel.SachTableModel("select * from tblsach");
                        modelTraSach.addRow(new Object[]{i, countResult[1], countResult[0], String.valueOf(diffDays), String.valueOf(TienPhat)});
                        i++;
                    }
                    int TongNo = Integer.parseInt(txtTienPhatKyNay.getText()) + Integer.parseInt(txtTienNo.getText());
                    txtTongNo.setText(String.valueOf(TongNo));
                    txtTienPhatKyNay.setText(String.valueOf(TienPhatKyNay));
                    tbPhieuTraSach.setModel(modelTraSach);
                }

            }
        });
        //Combo 2 thay doi
        cbbPhieuTraPhieuMuon.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                //lay ma sach ngay muon theo ma phieumuon
                DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
                int i = 1;
                int TienPhatKyNay = 0;
                List<Object[]> o = tblphieumuonsachDAO.layMaSachNgayMuonByMaPhieuMuon(cbbPhieuTraPhieuMuon.getSelectedItem().toString());
                for (Object[] countResult : o) {
                    System.out.println("NgayMuon:" + countResult[0] + "MaSach:" + countResult[1]);
                    txtTienNo.setText(countResult[2].toString());
                    //TableModel n = new UserTableModel.SachTableModel("select * from tblsach");
                    Date NgayMuon = (Date) countResult[0];
                    long diff = new Date().getTime() - NgayMuon.getTime();
                    long diffDays = diff / (24 * 60 * 60 * 1000);
                    long TienPhat = 0;
                    if (diffDays > 4) {
                        TienPhat = (diffDays - 4) * 1000;
                    }
                    TienPhatKyNay += TienPhat;
                    modelTraSach.addRow(new Object[]{i, countResult[1], countResult[0], String.valueOf(diffDays), String.valueOf(TienPhat)});
                    i++;
                }
                int TongNo = Integer.parseInt(txtTienPhatKyNay.getText()) + Integer.parseInt(txtTienNo.getText());
                txtTongNo.setText(String.valueOf(TongNo));
                txtTienPhatKyNay.setText(String.valueOf(TienPhatKyNay));
                tbPhieuTraSach.setModel(modelTraSach);
                btnTraSach.setVisible(true);
            }
        });

        //Lam tiep nhan vien
        dtpNgaySinh4.setFormats(new String[]{"dd/MM/yyyy"});
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String dateString = format.format(new Date());
            Date NgayMacDinh = format.parse("1995-01-01");
            dtpNgaySinh4.setDate(NgayMacDinh);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        KhoiTaoBangTiepNhanNhanVien(tblNhanVien4);

        //Thanh ly sach
        lbThuanNhanVienThanhLy.setText(ThuanNhanVienDangNhap.getHoTen() + " - " + ThuanNhanVienDangNhap.getBoPhan());
        if (!ThuanNhanVienDangNhap.getBoPhan().equals("Thủ Kho")) {
            btnThuanThanhLySach.setVisible(false);
        }

        //Load sach thanh ly;
        ResetThanhLy(tblDanhSachSach10, tblDanhSachSachThanhLy10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jFrame1 = new javax.swing.JFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable(tableModel);
        jTextField2 = RowFilterUtil.createRowFilter(tbSach);
        pnlMuonSach = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbMaTheDocGia = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbMuon = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLuuPhieu = new javax.swing.JButton();
        dtpNgayMuon = new org.jdesktop.swingx.JXDatePicker();
        txtLoi = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoSachConLai = new javax.swing.JLabel();
        txtConLai1 = new javax.swing.JLabel();
        txtSoSachDangChon = new javax.swing.JLabel();
        pnlLapTheDocGia = new javax.swing.JPanel();
        lblTitle3 = new javax.swing.JLabel();
        pnlThongTinThe3 = new javax.swing.JPanel();
        lblLoaiDocGia3 = new javax.swing.JLabel();
        cboLoaiDocGia3 = new javax.swing.JComboBox<>();
        lblHoTen3 = new javax.swing.JLabel();
        txtHoTen3 = new javax.swing.JTextField();
        txtDiaChi3 = new javax.swing.JTextField();
        lblDiaChi3 = new javax.swing.JLabel();
        txtEmail3 = new javax.swing.JTextField();
        lblEmail3 = new javax.swing.JLabel();
        lblNgaySinh3 = new javax.swing.JLabel();
        btnThem3 = new javax.swing.JButton();
        dtpNgaySinh3 = new org.jdesktop.swingx.JXDatePicker();
        pnlDanhSachTheDocGia3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTheDocGia3 = new javax.swing.JTable();
        pnlTiepNhanNhanVien = new javax.swing.JPanel();
        lblTitle4 = new javax.swing.JLabel();
        pnlThongTinNhanVien4 = new javax.swing.JPanel();
        lblHoTen4 = new javax.swing.JLabel();
        txtHoTen4 = new javax.swing.JTextField();
        txtDiaChi4 = new javax.swing.JTextField();
        lblDiaChi4 = new javax.swing.JLabel();
        lblNgaySinh4 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JButton();
        dtpNgaySinh4 = new org.jdesktop.swingx.JXDatePicker();
        lblBangCap4 = new javax.swing.JLabel();
        txtSDT4 = new javax.swing.JTextField();
        cboBangCap4 = new javax.swing.JComboBox<>();
        lblSDT4 = new javax.swing.JLabel();
        cboBoPhan4 = new javax.swing.JComboBox<>();
        lblBoPhan4 = new javax.swing.JLabel();
        cboChucVu4 = new javax.swing.JComboBox<>();
        lblChucVu4 = new javax.swing.JLabel();
        pblDanhSachNhanVien4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNhanVien4 = new javax.swing.JTable();
        lblGhiChu = new javax.swing.JLabel();
        pnlTiepNhanSach = new javax.swing.JPanel();
        lblTitle5 = new javax.swing.JLabel();
        pnlThongTinSach5 = new javax.swing.JPanel();
        lblTenSach5 = new javax.swing.JLabel();
        txtTenSach5 = new javax.swing.JTextField();
        lblTheLoai5 = new javax.swing.JLabel();
        lblNgaySinh5 = new javax.swing.JLabel();
        btnThem5 = new javax.swing.JButton();
        lblNgayNhap5 = new javax.swing.JLabel();
        txtNhaXuatBan5 = new javax.swing.JTextField();
        lblNhaXuatBan5 = new javax.swing.JLabel();
        lblTriGia5 = new javax.swing.JLabel();
        cboTheLoai5 = new javax.swing.JComboBox<>();
        txtNamXuatBan6 = new javax.swing.JTextField();
        dtpNgayNhap5 = new org.jdesktop.swingx.JXDatePicker();
        txtTriGia5 = new javax.swing.JTextField();
        cboNguoiTiepNhan5 = new javax.swing.JComboBox<>();
        lblTriGia6 = new javax.swing.JLabel();
        pblDanhSachSach5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSach5 = new javax.swing.JTable();
        pnlLapPhieuPhat = new javax.swing.JPanel();
        lblTitle6 = new javax.swing.JLabel();
        pnlThongTinPhieuPhat6 = new javax.swing.JPanel();
        lblHoTen6 = new javax.swing.JLabel();
        cboHoTenDocGia6 = new javax.swing.JComboBox<>();
        lblTienNo6 = new javax.swing.JLabel();
        txtSoTienThu6 = new javax.swing.JTextField();
        lblSoTienThu6 = new javax.swing.JLabel();
        lblConNo6 = new javax.swing.JLabel();
        lblNguoiThu6 = new javax.swing.JLabel();
        btnThem6 = new javax.swing.JButton();
        lblSoTienNo6 = new javax.swing.JLabel();
        lblSoTienConNo6 = new javax.swing.JLabel();
        cboNguoiThu6 = new javax.swing.JComboBox<>();
        pnlDanhSachPhieuPhat6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPhieuPhat6 = new javax.swing.JTable();
        pnlMatSach = new javax.swing.JPanel();
        lblTitle7 = new javax.swing.JLabel();
        pnlThongTinSachMat6 = new javax.swing.JPanel();
        lblTenSach7 = new javax.swing.JLabel();
        txtTenSach7 = new javax.swing.JTextField();
        btnThem7 = new javax.swing.JButton();
        lblNgayGhiNhan7 = new javax.swing.JLabel();
        dtpNgayNhap6 = new org.jdesktop.swingx.JXDatePicker();
        lblHoTen7 = new javax.swing.JLabel();
        cboHoTenDocGia7 = new javax.swing.JComboBox<>();
        txtTienPhat7 = new javax.swing.JTextField();
        lblTienPhat7 = new javax.swing.JLabel();
        cboNguoiGhiNhan7 = new javax.swing.JComboBox<>();
        lblNguoiGhiNhan7 = new javax.swing.JLabel();
        pnlDanhSachPhieuPhat7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPhieuPhat7 = new javax.swing.JTable();
        pnlThayMatKhau = new javax.swing.JPanel();
        lblTitle8 = new javax.swing.JLabel();
        lblMatKhauCu8 = new javax.swing.JLabel();
        psfMatKhauCu8 = new javax.swing.JPasswordField();
        lblMatKhauMoi8 = new javax.swing.JLabel();
        psfMatKhauMoi8 = new javax.swing.JPasswordField();
        btnThayMatKhau8 = new javax.swing.JButton();
        pnlThanhLySach = new javax.swing.JPanel();
        lbThuanNhanVienThanhLy = new javax.swing.JLabel();
        pnlDanhSachSach10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDanhSachSach10 = new javax.swing.JTable();
        lblLyDoThanhLy10 = new javax.swing.JLabel();
        cboLyDoThanhLy10 = new javax.swing.JComboBox<>();
        btnThuanThanhLySach = new javax.swing.JButton();
        pnlDanhSachSachThanhLy10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDanhSachSachThanhLy10 = new javax.swing.JTable();
        lblTitle11 = new javax.swing.JLabel();
        pnlNhanTraSach = new javax.swing.JPanel();
        lblTitle9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbbPhieuTraSachHoTen = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        dtpPhieuTraSachNgayTra = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        txtTienPhatKyNay = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTienNo = new javax.swing.JTextField();
        txtTongNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbPhieuTraSach = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbbPhieuTraPhieuMuon = new javax.swing.JComboBox<>();
        btnTraSach = new javax.swing.JButton();
        txtTraSachLoi = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tìm kiếm");

        tbSach.setModel(
            tableModel
        );
        jScrollPane1.setViewportView(tbSach);

        jTextField2.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addGap(79, 79, 79))
        );

        jTabbedPane1.addTab("Tra cứu sách", jPanel1);

        jLabel2.setText("Họ tên đọc giả");

        cbbMaTheDocGia.setModel(cbModelTheDocGia);

        jLabel3.setText("Ngày mượn");

        tbMuon.setModel(dm);
        jScrollPane3.setViewportView(tbMuon);

        btnXoa.setText("Xóa sách");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm sách");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuuPhieu.setText("Lưu phiếu");
        btnLuuPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPhieuActionPerformed(evt);
            }
        });

        txtLoi.setForeground(new java.awt.Color(255, 0, 0));
        txtLoi.setText("Lỗi");

        txtConLai.setForeground(new java.awt.Color(0, 0, 255));
        txtConLai.setText("Số sách mượn:");

        txtSoSachConLai.setText("jLabel5");

        txtConLai1.setForeground(new java.awt.Color(0, 0, 255));
        txtConLai1.setText("Số sách đang chọn:");

        txtSoSachDangChon.setText("0");

        javax.swing.GroupLayout pnlMuonSachLayout = new javax.swing.GroupLayout(pnlMuonSach);
        pnlMuonSach.setLayout(pnlMuonSachLayout);
        pnlMuonSachLayout.setHorizontalGroup(
            pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMuonSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(pnlMuonSachLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMuonSachLayout.createSequentialGroup()
                        .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMuonSachLayout.createSequentialGroup()
                                .addComponent(txtLoi)
                                .addGap(177, 177, 177)
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12)
                                .addComponent(txtConLai)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoSachConLai)
                                .addGap(18, 18, 18)
                                .addComponent(txtConLai1)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoSachDangChon))
                            .addGroup(pnlMuonSachLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbMaTheDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(dtpNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMuonSachLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(26, 26, 26)
                        .addComponent(btnXoa)
                        .addGap(35, 35, 35)
                        .addComponent(btnLuuPhieu)
                        .addGap(190, 190, 190))))
        );
        pnlMuonSachLayout.setVerticalGroup(
            pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMuonSachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbMaTheDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dtpNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtConLai1)
                        .addComponent(txtSoSachDangChon))
                    .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLoi)
                        .addComponent(txtConLai)
                        .addComponent(jLabel4)
                        .addComponent(txtSoSachConLai)))
                .addGap(18, 18, 18)
                .addGroup(pnlMuonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnThem)
                    .addComponent(btnLuuPhieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu mượn sách", pnlMuonSach);

        lblTitle3.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle3.setText("Lập Thẻ Độc Giả");

        pnlThongTinThe3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Thẻ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlThongTinThe3.setToolTipText("");
        pnlThongTinThe3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblLoaiDocGia3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoaiDocGia3.setText("Loại đọc giả:");

        cboLoaiDocGia3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboLoaiDocGia3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        lblHoTen3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblHoTen3.setText("Họ tên:");

        txtHoTen3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtDiaChi3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblDiaChi3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDiaChi3.setText("Địa chỉ:");

        txtEmail3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblEmail3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblEmail3.setText("Email:");

        lblNgaySinh3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNgaySinh3.setText("Ngày sinh:");

        btnThem3.setBackground(new java.awt.Color(204, 255, 204));
        btnThem3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem3.setText("Lập Thẻ");
        btnThem3.setToolTipText("");

        javax.swing.GroupLayout pnlThongTinThe3Layout = new javax.swing.GroupLayout(pnlThongTinThe3);
        pnlThongTinThe3.setLayout(pnlThongTinThe3Layout);
        pnlThongTinThe3Layout.setHorizontalGroup(
            pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinThe3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThongTinThe3Layout.createSequentialGroup()
                        .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLoaiDocGia3)
                            .addComponent(lblHoTen3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDiaChi3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgaySinh3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi3)
                            .addComponent(cboLoaiDocGia3, 0, 150, Short.MAX_VALUE)
                            .addComponent(txtHoTen3)
                            .addComponent(txtEmail3)
                            .addComponent(dtpNgaySinh3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinThe3Layout.setVerticalGroup(
            pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinThe3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiDocGia3)
                    .addComponent(cboLoaiDocGia3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoTen3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinThe3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh3)
                    .addComponent(dtpNgaySinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlDanhSachTheDocGia3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Thẻ Đọc Giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblTheDocGia3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTheDocGia3);

        javax.swing.GroupLayout pnlDanhSachTheDocGia3Layout = new javax.swing.GroupLayout(pnlDanhSachTheDocGia3);
        pnlDanhSachTheDocGia3.setLayout(pnlDanhSachTheDocGia3Layout);
        pnlDanhSachTheDocGia3Layout.setHorizontalGroup(
            pnlDanhSachTheDocGia3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachTheDocGia3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDanhSachTheDocGia3Layout.setVerticalGroup(
            pnlDanhSachTheDocGia3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachTheDocGia3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLapTheDocGiaLayout = new javax.swing.GroupLayout(pnlLapTheDocGia);
        pnlLapTheDocGia.setLayout(pnlLapTheDocGiaLayout);
        pnlLapTheDocGiaLayout.setHorizontalGroup(
            pnlLapTheDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLapTheDocGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTinThe3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDanhSachTheDocGia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLapTheDocGiaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle3)
                .addGap(249, 249, 249))
        );
        pnlLapTheDocGiaLayout.setVerticalGroup(
            pnlLapTheDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLapTheDocGiaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle3)
                .addGap(18, 18, 18)
                .addGroup(pnlLapTheDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinThe3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhSachTheDocGia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lập thẻ đọc giả", pnlLapTheDocGia);

        lblTitle4.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle4.setText("Tiếp Nhận Nhân Viên");

        pnlThongTinNhanVien4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlThongTinNhanVien4.setToolTipText("");
        pnlThongTinNhanVien4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblHoTen4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblHoTen4.setText("Họ tên:");

        txtHoTen4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtDiaChi4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblDiaChi4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDiaChi4.setText("Địa chỉ:");

        lblNgaySinh4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNgaySinh4.setText("Ngày sinh:");

        btnThem4.setBackground(new java.awt.Color(204, 255, 204));
        btnThem4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem4.setText("Tiếp Nhận Nhân Viên");
        btnThem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem4ActionPerformed(evt);
            }
        });

        lblBangCap4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblBangCap4.setText("Bằng cấp:");

        txtSDT4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        cboBangCap4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboBangCap4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tú Tài", "Trung Cấp", "Cao Đẳng", "Đại Học", "Thạc Sĩ", "Tiến Sĩ" }));

        lblSDT4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSDT4.setText("SĐT:");

        cboBoPhan4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboBoPhan4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thủ Thư", "Thủ Kho", "Thủ Quỹ", "Ban Giám Đốc" }));

        lblBoPhan4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblBoPhan4.setText("Bộ phận:");

        cboChucVu4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboChucVu4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Phó Phòng", "Trưởng Phòng", "Phó Giám Đốc", "Giám Đốc" }));

        lblChucVu4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblChucVu4.setText("Chức vụ:");

        javax.swing.GroupLayout pnlThongTinNhanVien4Layout = new javax.swing.GroupLayout(pnlThongTinNhanVien4);
        pnlThongTinNhanVien4.setLayout(pnlThongTinNhanVien4Layout);
        pnlThongTinNhanVien4Layout.setHorizontalGroup(
            pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVien4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlThongTinNhanVien4Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDiaChi4)
                                .addComponent(lblHoTen4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDiaChi4, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addComponent(txtHoTen4)))
                        .addGroup(pnlThongTinNhanVien4Layout.createSequentialGroup()
                            .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblBangCap4)
                                .addComponent(lblNgaySinh4)
                                .addComponent(lblSDT4)
                                .addComponent(lblBoPhan4)
                                .addComponent(lblChucVu4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtpNgaySinh4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSDT4)
                                .addComponent(cboBangCap4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboBoPhan4, 0, 166, Short.MAX_VALUE)
                                .addComponent(cboChucVu4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(btnThem4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlThongTinNhanVien4Layout.setVerticalGroup(
            pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVien4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoTen4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh4)
                    .addComponent(dtpNgaySinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDT4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboBangCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBangCap4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboBoPhan4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBoPhan4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboChucVu4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChucVu4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pblDanhSachNhanVien4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblNhanVien4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblNhanVien4);

        javax.swing.GroupLayout pblDanhSachNhanVien4Layout = new javax.swing.GroupLayout(pblDanhSachNhanVien4);
        pblDanhSachNhanVien4.setLayout(pblDanhSachNhanVien4Layout);
        pblDanhSachNhanVien4Layout.setHorizontalGroup(
            pblDanhSachNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblDanhSachNhanVien4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pblDanhSachNhanVien4Layout.setVerticalGroup(
            pblDanhSachNhanVien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblDanhSachNhanVien4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(255, 51, 51));
        lblGhiChu.setText("* Mật khẩu mặc định là 123456, nhân viên phải thay đổi mật khẩu sau khi được cấp tài khoản.");
        lblGhiChu.setToolTipText("");

        javax.swing.GroupLayout pnlTiepNhanNhanVienLayout = new javax.swing.GroupLayout(pnlTiepNhanNhanVien);
        pnlTiepNhanNhanVien.setLayout(pnlTiepNhanNhanVienLayout);
        pnlTiepNhanNhanVienLayout.setHorizontalGroup(
            pnlTiepNhanNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiepNhanNhanVienLayout.createSequentialGroup()
                .addGroup(pnlTiepNhanNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTiepNhanNhanVienLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(lblTitle4)
                        .addGap(0, 413, Short.MAX_VALUE))
                    .addGroup(pnlTiepNhanNhanVienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlThongTinNhanVien4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pblDanhSachNhanVien4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlTiepNhanNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGhiChu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTiepNhanNhanVienLayout.setVerticalGroup(
            pnlTiepNhanNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiepNhanNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle4)
                .addGap(18, 18, 18)
                .addGroup(pnlTiepNhanNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinNhanVien4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pblDanhSachNhanVien4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblGhiChu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tiếp nhận nhân viên", pnlTiepNhanNhanVien);

        lblTitle5.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle5.setText("Tiếp Nhận Sách");

        pnlThongTinSach5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlThongTinSach5.setToolTipText("");
        pnlThongTinSach5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblTenSach5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTenSach5.setText("Tên sách:");

        txtTenSach5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblTheLoai5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTheLoai5.setText("Thể loại:");

        lblNgaySinh5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNgaySinh5.setText("Năm xuất bản:");

        btnThem5.setBackground(new java.awt.Color(204, 255, 204));
        btnThem5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem5.setText("Tiếp Nhận Sách");

        lblNgayNhap5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNgayNhap5.setText("Ngày nhập:");

        txtNhaXuatBan5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblNhaXuatBan5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNhaXuatBan5.setText("Nhà xuất bản:");

        lblTriGia5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTriGia5.setText("Trị giá:");

        cboTheLoai5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboTheLoai5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        txtNamXuatBan6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtTriGia5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        cboNguoiTiepNhan5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblTriGia6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTriGia6.setText("Người tiếp nhận:");

        javax.swing.GroupLayout pnlThongTinSach5Layout = new javax.swing.GroupLayout(pnlThongTinSach5);
        pnlThongTinSach5.setLayout(pnlThongTinSach5Layout);
        pnlThongTinSach5Layout.setHorizontalGroup(
            pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinSach5Layout.createSequentialGroup()
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThongTinSach5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThem5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThongTinSach5Layout.createSequentialGroup()
                        .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTriGia5)
                            .addComponent(lblTriGia6)
                            .addComponent(lblNgayNhap5)
                            .addComponent(lblNhaXuatBan5)
                            .addComponent(lblNgaySinh5)
                            .addComponent(lblTheLoai5)
                            .addComponent(lblTenSach5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTriGia5)
                            .addComponent(dtpNgayNhap5, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtNhaXuatBan5)
                            .addComponent(cboNguoiTiepNhan5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNamXuatBan6)
                            .addComponent(cboTheLoai5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSach5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinSach5Layout.setVerticalGroup(
            pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinSach5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSach5)
                    .addComponent(txtTenSach5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTheLoai5)
                    .addComponent(cboTheLoai5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh5)
                    .addComponent(txtNamXuatBan6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhaXuatBan5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNhaXuatBan5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayNhap5)
                    .addComponent(dtpNgayNhap5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTriGia5)
                    .addComponent(txtTriGia5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNguoiTiepNhan5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTriGia6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pblDanhSachSach5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblSach5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblSach5);

        javax.swing.GroupLayout pblDanhSachSach5Layout = new javax.swing.GroupLayout(pblDanhSachSach5);
        pblDanhSachSach5.setLayout(pblDanhSachSach5Layout);
        pblDanhSachSach5Layout.setHorizontalGroup(
            pblDanhSachSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblDanhSachSach5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pblDanhSachSach5Layout.setVerticalGroup(
            pblDanhSachSach5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblDanhSachSach5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTiepNhanSachLayout = new javax.swing.GroupLayout(pnlTiepNhanSach);
        pnlTiepNhanSach.setLayout(pnlTiepNhanSachLayout);
        pnlTiepNhanSachLayout.setHorizontalGroup(
            pnlTiepNhanSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiepNhanSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTinSach5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pblDanhSachSach5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlTiepNhanSachLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(lblTitle5)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        pnlTiepNhanSachLayout.setVerticalGroup(
            pnlTiepNhanSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTiepNhanSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTiepNhanSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinSach5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pblDanhSachSach5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tiếp nhận sách", pnlTiepNhanSach);

        lblTitle6.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle6.setText("Lập Phiếu Phạt");

        pnlThongTinPhieuPhat6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Phiếu Phạt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlThongTinPhieuPhat6.setToolTipText("");
        pnlThongTinPhieuPhat6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblHoTen6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblHoTen6.setText("Họ tên đọc giả:");

        cboHoTenDocGia6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblTienNo6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTienNo6.setText("Tiền nợ:");

        txtSoTienThu6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblSoTienThu6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSoTienThu6.setText("Số tiền thu:");

        lblConNo6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblConNo6.setText("Còn nợ:");

        lblNguoiThu6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNguoiThu6.setText("Người thu:");

        btnThem6.setBackground(new java.awt.Color(204, 255, 204));
        btnThem6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem6.setText("Lưu lại");
        btnThem6.setToolTipText("");

        lblSoTienNo6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSoTienNo6.setText("số tiền nợ");

        lblSoTienConNo6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSoTienConNo6.setText("số tiền còn nợ");

        cboNguoiThu6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnlThongTinPhieuPhat6Layout = new javax.swing.GroupLayout(pnlThongTinPhieuPhat6);
        pnlThongTinPhieuPhat6.setLayout(pnlThongTinPhieuPhat6Layout);
        pnlThongTinPhieuPhat6Layout.setHorizontalGroup(
            pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinPhieuPhat6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThem6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThongTinPhieuPhat6Layout.createSequentialGroup()
                        .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoTen6)
                            .addComponent(lblTienNo6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblConNo6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSoTienThu6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNguoiThu6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoTienThu6)
                            .addComponent(cboHoTenDocGia6, 0, 150, Short.MAX_VALUE)
                            .addComponent(lblSoTienNo6)
                            .addComponent(lblSoTienConNo6)
                            .addComponent(cboNguoiThu6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinPhieuPhat6Layout.setVerticalGroup(
            pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinPhieuPhat6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen6)
                    .addComponent(cboHoTenDocGia6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienNo6)
                    .addComponent(lblSoTienNo6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoTienThu6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoTienThu6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConNo6)
                    .addComponent(lblSoTienConNo6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNguoiThu6)
                    .addComponent(cboNguoiThu6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDanhSachPhieuPhat6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Phiếu Phạt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblPhieuPhat6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tblPhieuPhat6);

        javax.swing.GroupLayout pnlDanhSachPhieuPhat6Layout = new javax.swing.GroupLayout(pnlDanhSachPhieuPhat6);
        pnlDanhSachPhieuPhat6.setLayout(pnlDanhSachPhieuPhat6Layout);
        pnlDanhSachPhieuPhat6Layout.setHorizontalGroup(
            pnlDanhSachPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachPhieuPhat6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDanhSachPhieuPhat6Layout.setVerticalGroup(
            pnlDanhSachPhieuPhat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachPhieuPhat6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLapPhieuPhatLayout = new javax.swing.GroupLayout(pnlLapPhieuPhat);
        pnlLapPhieuPhat.setLayout(pnlLapPhieuPhatLayout);
        pnlLapPhieuPhatLayout.setHorizontalGroup(
            pnlLapPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLapPhieuPhatLayout.createSequentialGroup()
                .addGroup(pnlLapPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLapPhieuPhatLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(lblTitle6)
                        .addGap(0, 454, Short.MAX_VALUE))
                    .addGroup(pnlLapPhieuPhatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlThongTinPhieuPhat6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDanhSachPhieuPhat6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlLapPhieuPhatLayout.setVerticalGroup(
            pnlLapPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLapPhieuPhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle6)
                .addGap(18, 18, 18)
                .addGroup(pnlLapPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinPhieuPhat6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhSachPhieuPhat6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lập phiếu phạt", pnlLapPhieuPhat);

        lblTitle7.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle7.setText("Ghi Nhận Mất Sách");

        pnlThongTinSachMat6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sách Bị Mất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlThongTinSachMat6.setToolTipText("");
        pnlThongTinSachMat6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblTenSach7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTenSach7.setText("Tên sách:");

        txtTenSach7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnThem7.setBackground(new java.awt.Color(204, 255, 204));
        btnThem7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem7.setText("Ghi Nhận");

        lblNgayGhiNhan7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNgayGhiNhan7.setText("Ngày ghi nhận:");

        lblHoTen7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblHoTen7.setText("Họ tên đọc giả:");

        cboHoTenDocGia7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtTienPhat7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblTienPhat7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTienPhat7.setText("Tiền phạt:");

        cboNguoiGhiNhan7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        lblNguoiGhiNhan7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNguoiGhiNhan7.setText("Người ghi nhận:");

        javax.swing.GroupLayout pnlThongTinSachMat6Layout = new javax.swing.GroupLayout(pnlThongTinSachMat6);
        pnlThongTinSachMat6.setLayout(pnlThongTinSachMat6Layout);
        pnlThongTinSachMat6Layout.setHorizontalGroup(
            pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinSachMat6Layout.createSequentialGroup()
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinSachMat6Layout.createSequentialGroup()
                        .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoTen7)
                            .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNgayGhiNhan7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTenSach7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblTienPhat7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNguoiGhiNhan7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienPhat7)
                            .addComponent(cboHoTenDocGia7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtpNgayNhap6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtTenSach7)
                            .addComponent(cboNguoiGhiNhan7, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(btnThem7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlThongTinSachMat6Layout.setVerticalGroup(
            pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinSachMat6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSach7)
                    .addComponent(txtTenSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayGhiNhan7)
                    .addComponent(dtpNgayNhap6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHoTenDocGia7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoTen7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienPhat7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienPhat7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinSachMat6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNguoiGhiNhan7)
                    .addComponent(cboNguoiGhiNhan7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThem7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDanhSachPhieuPhat7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sách Bị Mất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblPhieuPhat7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tblPhieuPhat7);

        javax.swing.GroupLayout pnlDanhSachPhieuPhat7Layout = new javax.swing.GroupLayout(pnlDanhSachPhieuPhat7);
        pnlDanhSachPhieuPhat7.setLayout(pnlDanhSachPhieuPhat7Layout);
        pnlDanhSachPhieuPhat7Layout.setHorizontalGroup(
            pnlDanhSachPhieuPhat7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachPhieuPhat7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDanhSachPhieuPhat7Layout.setVerticalGroup(
            pnlDanhSachPhieuPhat7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachPhieuPhat7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMatSachLayout = new javax.swing.GroupLayout(pnlMatSach);
        pnlMatSach.setLayout(pnlMatSachLayout);
        pnlMatSachLayout.setHorizontalGroup(
            pnlMatSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatSachLayout.createSequentialGroup()
                .addGroup(pnlMatSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMatSachLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(lblTitle7)
                        .addGap(0, 426, Short.MAX_VALUE))
                    .addGroup(pnlMatSachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlThongTinSachMat6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlDanhSachPhieuPhat7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMatSachLayout.setVerticalGroup(
            pnlMatSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMatSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinSachMat6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhSachPhieuPhat7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mất Sách", pnlMatSach);

        lblTitle8.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle8.setText("Thay Đổi Mật Khẩu");

        lblMatKhauCu8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMatKhauCu8.setText("Mật khẩu cũ:");

        lblMatKhauMoi8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMatKhauMoi8.setText("Mật khẩu mới:");

        btnThayMatKhau8.setBackground(new java.awt.Color(204, 255, 204));
        btnThayMatKhau8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThayMatKhau8.setText("Thay Đổi Mật Khẩu");
        btnThayMatKhau8.setToolTipText("");

        javax.swing.GroupLayout pnlThayMatKhauLayout = new javax.swing.GroupLayout(pnlThayMatKhau);
        pnlThayMatKhau.setLayout(pnlThayMatKhauLayout);
        pnlThayMatKhauLayout.setHorizontalGroup(
            pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThayMatKhauLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThayMatKhau8, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addGroup(pnlThayMatKhauLayout.createSequentialGroup()
                        .addGroup(pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMatKhauCu8)
                            .addComponent(lblMatKhauMoi8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(psfMatKhauMoi8)
                            .addComponent(psfMatKhauCu8))))
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThayMatKhauLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle8)
                .addGap(236, 236, 236))
        );
        pnlThayMatKhauLayout.setVerticalGroup(
            pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThayMatKhauLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTitle8)
                .addGap(84, 84, 84)
                .addGroup(pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatKhauCu8)
                    .addComponent(psfMatKhauCu8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThayMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(psfMatKhauMoi8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatKhauMoi8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThayMatKhau8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thay mật khẩu", pnlThayMatKhau);

        lbThuanNhanVienThanhLy.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lbThuanNhanVienThanhLy.setText("Thanh Lý Sách");

        pnlDanhSachSach10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlDanhSachSach10.setToolTipText("");
        pnlDanhSachSach10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tblDanhSachSach10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tblDanhSachSach10);

        lblLyDoThanhLy10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLyDoThanhLy10.setText("Lý do thanh lý:");

        cboLyDoThanhLy10.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboLyDoThanhLy10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mất", "Hư hỏng", "Người dùng làm mất" }));

        btnThuanThanhLySach.setBackground(new java.awt.Color(204, 255, 204));
        btnThuanThanhLySach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThuanThanhLySach.setText("Thanh lý sách");
        btnThuanThanhLySach.setToolTipText("");
        btnThuanThanhLySach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuanThanhLySachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhSachSach10Layout = new javax.swing.GroupLayout(pnlDanhSachSach10);
        pnlDanhSachSach10.setLayout(pnlDanhSachSach10Layout);
        pnlDanhSachSach10Layout.setHorizontalGroup(
            pnlDanhSachSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSach10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThuanThanhLySach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(pnlDanhSachSach10Layout.createSequentialGroup()
                        .addComponent(lblLyDoThanhLy10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboLyDoThanhLy10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDanhSachSach10Layout.setVerticalGroup(
            pnlDanhSachSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSach10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlDanhSachSach10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLyDoThanhLy10)
                    .addComponent(cboLyDoThanhLy10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThuanThanhLySach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDanhSachSachThanhLy10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sách Thanh Lý", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        pnlDanhSachSachThanhLy10.setToolTipText("");
        pnlDanhSachSachThanhLy10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tblDanhSachSachThanhLy10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tblDanhSachSachThanhLy10);

        javax.swing.GroupLayout pnlDanhSachSachThanhLy10Layout = new javax.swing.GroupLayout(pnlDanhSachSachThanhLy10);
        pnlDanhSachSachThanhLy10.setLayout(pnlDanhSachSachThanhLy10Layout);
        pnlDanhSachSachThanhLy10Layout.setHorizontalGroup(
            pnlDanhSachSachThanhLy10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSachThanhLy10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDanhSachSachThanhLy10Layout.setVerticalGroup(
            pnlDanhSachSachThanhLy10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachSachThanhLy10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(89, 89, 89))
        );

        lblTitle11.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        lblTitle11.setText("Thanh Lý Sách");

        javax.swing.GroupLayout pnlThanhLySachLayout = new javax.swing.GroupLayout(pnlThanhLySach);
        pnlThanhLySach.setLayout(pnlThanhLySachLayout);
        pnlThanhLySachLayout.setHorizontalGroup(
            pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhLySachLayout.createSequentialGroup()
                .addGroup(pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhLySachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDanhSachSach10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThanhLySachLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbThuanNhanVienThanhLy)))
                .addGap(18, 18, 18)
                .addGroup(pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhLySachLayout.createSequentialGroup()
                        .addComponent(lblTitle11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlDanhSachSachThanhLy10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlThanhLySachLayout.setVerticalGroup(
            pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhLySachLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbThuanNhanVienThanhLy)
                    .addComponent(lblTitle11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThanhLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDanhSachSach10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhSachSachThanhLy10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thanh lý sách", pnlThanhLySach);

        lblTitle9.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N

        jLabel5.setText("Họ tên độc giả");

        cbbPhieuTraSachHoTen.setModel(cbModelPhieuTraSachHoTen);

        jLabel6.setText("Ngày Trả");

        jLabel7.setText("Tiền phạt kỳ này");

        txtTienPhatKyNay.setText("0");
        txtTienPhatKyNay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienPhatKyNayActionPerformed(evt);
            }
        });

        jLabel8.setText("Tiền nợ");

        txtTienNo.setText("0");

        jLabel9.setText("Tổng nợ");

        tbPhieuTraSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"
            }
        ));
        jScrollPane11.setViewportView(tbPhieuTraSach);

        jLabel10.setText("Phiếu mượn");

        cbbPhieuTraPhieuMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTraSach.setText("Trả sách");
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });

        txtTraSachLoi.setText("Lỗi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbPhieuTraSachHoTen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbbPhieuTraPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnTraSach)
                    .addComponent(txtTraSachLoi))
                .addGap(116, 116, 116)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtpPhieuTraSachNgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienPhatKyNay)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongNo, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(txtTienNo))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbPhieuTraSachHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(dtpPhieuTraSachNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienPhatKyNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbPhieuTraPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTienNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTraSachLoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtTongNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(btnTraSach)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNhanTraSachLayout = new javax.swing.GroupLayout(pnlNhanTraSach);
        pnlNhanTraSach.setLayout(pnlNhanTraSachLayout);
        pnlNhanTraSachLayout.setHorizontalGroup(
            pnlNhanTraSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanTraSachLayout.createSequentialGroup()
                .addContainerGap(658, Short.MAX_VALUE)
                .addComponent(lblTitle9)
                .addGap(266, 266, 266))
            .addGroup(pnlNhanTraSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNhanTraSachLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlNhanTraSachLayout.setVerticalGroup(
            pnlNhanTraSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanTraSachLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitle9)
                .addContainerGap(503, Short.MAX_VALUE))
            .addGroup(pnlNhanTraSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNhanTraSachLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Nhận trả sách", pnlNhanTraSach);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dtpNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpNgayMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dtpNgayMuonActionPerformed

    private Boolean kiemTra4Ngay() {
        String[] Sach4Ngay = tblphieumuonsachDAO.layDSKiemTraNgayMuon(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], new Date());

        if (Sach4Ngay.length > 0) {
            txtLoi.setVisible(true);
            txtLoi.setForeground(Color.RED);
            txtLoi.setText("Tài khoản mượn sách quá 4 ngày");
            return true;
        } else {
            return false;
        }
    }

    private Boolean kiemTraHan() {
        Date now = new Date();
        Date NgayLapThe = TheDocGiaDAO.layNgayLapBangMaThe(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()]);
        //        System.out.println("Ngay Lap The: " + NgayLapThe);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(NgayLapThe.getYear(), NgayLapThe.getMonth(), NgayLapThe.getDate());
        Calendar cal2 = Calendar.getInstance();
        cal2.set(now.getYear(), now.getMonth(), now.getDate());
        boolean valid = TinhThang.isSixMonthsAgo(cal2, cal1);

        // System.out.println("Kiem tra 6 thang: " + valid);
        if (valid) {
            txtLoi.setVisible(true);
            txtLoi.setForeground(Color.RED);
            txtLoi.setText("Thẻ quá hạn.");
        } else {
            txtLoi.setForeground(Color.GREEN);
            txtLoi.setVisible(false);
        }
        return valid;
    }

    private void btnLuuPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPhieuActionPerformed

        if (kiemTraHan()) {
            return;
        }
        if (kiemTra4Ngay()) {
            return;
        }
        Date now = new Date();

        String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], now);
        if (DSPhieuMuonHienTai.length > 4) {
            txtLoi.setVisible(true);
            txtLoi.setForeground(Color.RED);
            txtLoi.setText("Chỉ được mượn tối đa 5 quyển");
            return;
        }
        System.out.println("DanhSachPhieuDangMuon: " + DSPhieuMuonHienTai.length);
        if (tbMuon.getModel().getRowCount() == 0) {
            txtLoi.setVisible(true);
            txtLoi.setForeground(Color.RED);
            txtLoi.setText("Chưa chọn sách");
            return;
        }

        tblphieumuonsach t = new tblphieumuonsach();
        t.setMaTheDocGia(Integer.parseInt(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()]));
        Date NgayMuon = dtpNgayMuon.getDate();
        t.setNgayMuon(NgayMuon);
        t.setTrangThaiXoa(false);
        int newid = tblphieumuonsachDAO.themPhieuMuon(t);

        for (int i = 0; i < tbMuon.getRowCount(); i++) {
            tblchitietphieumuon ct = new tblchitietphieumuon();
            ct.setMaPhieuMuon(newid);

            int MaSach = Integer.parseInt(tbMuon.getModel().getValueAt(i, 1).toString());
            ct.setMaSach(MaSach);
            tblchitietphieumuonDAO.ThemChiTieuPhieuMuon(ct);
        }
        DefaultTableModel dmXoa = (DefaultTableModel) tbMuon.getModel();
        int rowCount = dmXoa.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmXoa.removeRow(i);
        }

        txtLoi.setVisible(true);
        txtLoi.setForeground(Color.GREEN);
        txtLoi.setText("Thêm phiếu mượn thành công!");

        //---phieu tra sach
        String[] dsTheMuonBangMaTheDocGia = null;

        Boolean coTraCoPhieuMuonSach = false;
        //Bat su kien ben tra
        TheDocGiaComboModel combomodeldsTheMuonBangMaTheDocGia;
        dsTheMuonBangMaTheDocGia = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGiaDistinct(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]);
        if (dsTheMuonBangMaTheDocGia.length == 0) {
            txtTraSachLoi.setVisible(true);
            txtTraSachLoi.setForeground(Color.red);
            txtTraSachLoi.setText("Đọc giả không có phiếu mượn");
            coTraCoPhieuMuonSach = false;
//            System.out.println("Tien no: " + TheDocGiaDAO.layTienNoBangMaThe(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]));
            txtTienNo.setText(String.valueOf(TheDocGiaDAO.layTienNoBangMaThe(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()])));
            combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
            cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);
            btnTraSach.setVisible(false);
        } else {
            btnTraSach.setVisible(true);
            coTraCoPhieuMuonSach = true;
            txtTraSachLoi.setVisible(false);
            txtTraSachLoi.setForeground(Color.green);
            txtTraSachLoi.setText("");

            combomodeldsTheMuonBangMaTheDocGia = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGia);
            cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGia);
            int TienPhatKyNay = 0;
            DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
            int i = 1;
            List<Object[]> o = tblphieumuonsachDAO.layMaSachNgayMuonByMaPhieuMuon(cbbPhieuTraPhieuMuon.getSelectedItem().toString());
            for (Object[] countResult : o) {
                System.out.println("NgayMuon:" + countResult[0] + "MaSach:" + countResult[1] + " TieNo: " + countResult[2]);
                //TableModel n = new UserTableModel.SachTableModel("select * from tblsach");
                txtTienNo.setText(countResult[1].toString());

                Date NgayMuonNew = (Date) countResult[0];
                long diff = new Date().getTime() - NgayMuonNew.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                long TienPhat = 0;
                if (diffDays > 4) {
                    TienPhat = (diffDays - 4) * 1000;
                }
                TienPhatKyNay += TienPhat;
                modelTraSach.addRow(new Object[]{i, countResult[1], countResult[0], String.valueOf(diffDays), String.valueOf(TienPhat)});
                i++;
            }
            txtTienPhatKyNay.setText(String.valueOf(TienPhatKyNay));
            int TongNo = Integer.parseInt(txtTienPhatKyNay.getText()) + Integer.parseInt(txtTienNo.getText());
            txtTongNo.setText(String.valueOf(TongNo));
            tbPhieuTraSach.setModel(modelTraSach);
        }
    }//GEN-LAST:event_btnLuuPhieuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (kiemTraHan()) {
            return;
        }
        if (kiemTra4Ngay()) {
            return;
        }
        String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], new Date());
        if (DSPhieuMuonHienTai.length + dm.getRowCount() > 4) {
            return;
        }

        ArrayList<String> danhSachDangChon = new ArrayList<>();

        for (int i = 0; i < dm.getRowCount(); i++) {
            String MaSach = dm.getValueAt(i, 1).toString();
            danhSachDangChon.add(MaSach);

        }
        System.out.println("Danh sach: " + danhSachDangChon.size());

        //DefaultTableModel dt = new DefaultTableModel(data, columnNames);
        JTable tbSach2 = new JTable();

        if (danhSachDangChon.size() > 0) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Tên Sách", "Thể loại", "Tác giả", "Tình trạng"}, 0);
            //TableModel n = new UserTableModel.SachTableModel("select * from tblsach");
            TableModel n = createTableModelSachChuaMuon();
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
            System.out.println("n.getRowCount(): " + n.getRowCount());
            for (int i = 0; i < n.getRowCount(); i++) {
                String cot1 = n.getValueAt(i, 1).toString();
                String cot2 = n.getValueAt(i, 2).toString();
                String cot3 = n.getValueAt(i, 3).toString();
                String cot4 = n.getValueAt(i, 4).toString();
                String cot5 = n.getValueAt(i, 5).toString();
                if (!danhSachDangChon.contains(cot1)) {
                    model.addRow(new Object[]{i, cot1, cot2, cot3, cot4, cot5});
                }
            }
            tbSach2.setModel(model);
        } else {
            tbSach2.setModel(createTableModelSachChuaMuon());
        }

        JScrollPane jScrollPane1 = new JScrollPane();

        jScrollPane1.setViewportView(tbSach2);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(jScrollPane1);
        int result = JOptionPane.showConfirmDialog(null, panel, "Thêm Sách",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (tbSach2.getRowCount() > 0) {
                int column = 1;
                int row = tbSach2.getSelectedRow();

                String MaSach = tbSach2.getModel().getValueAt(row, column).toString();
                String TenSach = tbSach2.getModel().getValueAt(row, column + 1).toString();
                String TheLoai = tbSach2.getModel().getValueAt(row, column + 2).toString();
                String TacGia = tbSach2.getModel().getValueAt(row, column + 3).toString();
                Object[] newRowData = {tbMuon.getRowCount() + 1, MaSach, TenSach, TheLoai, TacGia};

                dm.addRow(newRowData);
                SoSachDaMuon += 1;
                // txtSoSachConLai.setText(String.valueOf(SoSachDaMuon));
                txtSoSachConLai.setText(String.valueOf(DSPhieuMuonHienTai.length + dm.getRowCount()));
                txtSoSachDangChon.setText(String.valueOf(tbMuon.getRowCount()));
            }
        } else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        if (kiemTraHan()) {
            return;
        }
        if (kiemTra4Ngay()) {
            return;
        }
        dm.removeRow(tbMuon.getSelectedRow());
        String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()], new Date());
        txtSoSachConLai.setText(String.valueOf(DSPhieuMuonHienTai.length + dm.getRowCount()));
        txtSoSachDangChon.setText(String.valueOf(tbMuon.getRowCount()));
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTienPhatKyNayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienPhatKyNayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienPhatKyNayActionPerformed

    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
        // TODO add your handling code here:
        tblphieutrasach t = new tblphieutrasach();
        t.setMaTheDocGia(Integer.parseInt(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]));
        t.setNgayTra(dtpPhieuTraSachNgayTra.getDate());
        t.setTienPhat(Integer.parseInt(txtTienPhatKyNay.getText()));
        int idnew = tblphieutrasachDAO.themPhieuTra(t);
        if (idnew != 0) {

            for (int i = 0; i < tbPhieuTraSach.getRowCount(); i++) {
                String MaSach = tbPhieuTraSach.getValueAt(i, 1).toString();
                System.out.println("MaSach: " + MaSach);
                tblchitietphieutra tct = new tblchitietphieutra();
                tct.setMaPhieuTra(idnew);
                tct.setMaSach(Integer.parseInt(MaSach));
                tblchitietphieutrasachDAO.themPhieuTra(tct);
            }
            TheDocGiaDAO.updateTienNo(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()], txtTongNo.getText());
            tblphieumuonsachDAO.updateXoaMuon(cbbPhieuTraPhieuMuon.getSelectedItem().toString());
            txtTraSachLoi.setVisible(true);
            txtTraSachLoi.setText("Thêm phiếu trả thành công!");
            txtTraSachLoi.setForeground(new Color(4, 142, 8));
            String[] dsTheMuonBangMaTheDocGianew = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGiaDistinct(dsMaTheDocGia[cbbPhieuTraSachHoTen.getSelectedIndex()]);;
            TheDocGiaComboModel combomodeldsTheMuonBangMaTheDocGianew = new TheDocGiaComboModel(dsTheMuonBangMaTheDocGianew);
            cbbPhieuTraPhieuMuon.setModel(combomodeldsTheMuonBangMaTheDocGianew);
            DefaultTableModel modelTraSach = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Ngày Mượn", "Số Ngày Mượn", "Tiền Phạt"}, 0);
            tbPhieuTraSach.setModel(modelTraSach);

            //Reset lai phieu muon
            if (kiemTraHan()) {
                txtSoSachConLai.setText("0");
                txtSoSachDangChon.setText("0");
                btnThem.setVisible(false);
                btnXoa.setVisible(false);
                btnLuuPhieu.setVisible(false);
                return;
            }
            if (kiemTra4Ngay()) {
                btnThem.setVisible(false);
                btnXoa.setVisible(false);
                btnLuuPhieu.setVisible(false);
                // return;
            } else {
                btnThem.setVisible(true);
                btnXoa.setVisible(true);
                btnLuuPhieu.setVisible(true);
                txtSoSachDangChon.setText(String.valueOf(tbMuon.getRowCount()));

                String[] DSPhieuMuonHienTai = tblphieumuonsachDAO.layDSPhieuMuonSachBangMaTheDocGia(dsMaTheDocGia[cbbMaTheDocGia.getSelectedIndex()]);
                SoSachDaMuon = DSPhieuMuonHienTai.length;
                txtSoSachConLai.setText(String.valueOf(DSPhieuMuonHienTai.length + dm.getRowCount()));
            }
        }
    }//GEN-LAST:event_btnTraSachActionPerformed

    private void btnThem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem4ActionPerformed
        // TODO add your handling code here:
        if (txtHoTen4.getText().equals("") || cboBangCap4.getSelectedItem().equals("") || cboBoPhan4.getSelectedItem().equals("") || cboChucVu4.getSelectedItem().equals("")
                || txtSDT4.getText().equals("") || txtDiaChi4.getText().equals("")) {
            lblGhiChu.setText("Vui lòng nhập đầy đủ thông tin");
            lblGhiChu.setForeground(Color.RED);
            return;
        }

        ThuanNhanVien t = new ThuanNhanVien();
        t.setMatKhau("123456");
        t.setHoTen(txtHoTen4.getText());
        t.setBoPhan(cboBoPhan4.getSelectedItem().toString());
        t.setChucVu(cboChucVu4.getSelectedItem().toString());
        t.setLoaiBangCap(cboBangCap4.getSelectedItem().toString());
        t.setDiaChi(txtDiaChi4.getText());
        t.setNgaySinh(dtpNgaySinh4.getDate());
        t.setTrangThaiXoa(false);
        t.setSoDienThoai(txtSDT4.getText());
        ThuanNhanVienDAO.ThemNhanVien(t);
        lblGhiChu.setText("Thêm nhân viên thành công!");
        lblGhiChu.setForeground(Color.GREEN);
        System.out.println("frmGUI.btnThem4ActionPerformed(): them thanh cong");
        KhoiTaoBangTiepNhanNhanVien(tblNhanVien4);
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnThuanThanhLySachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuanThanhLySachActionPerformed
        // TODO add your handling code here:
        if(tblDanhSachSach10.getRowCount() == 0){
            return;
        }
        ThuanThanhLySach t = new ThuanThanhLySach();
        t.setMaNhanVienLap(MaNhanVienDangNhap);
        t.setNgayThanhLy(new Date());
        t.setTrangThaiXoa(false);
        int newid = ThuanThanhLySachDAO.themPhieuThanhLy(t);

        ThuanChiTietThanhLy ct = new ThuanChiTietThanhLy();
        ct.setMaPhieuThanhLy(newid);

        int columnHienTai = 1;
        int rowHienTai = tblDanhSachSach10.getSelectedRow();
        int MaSachHienTai = Integer.parseInt(tblDanhSachSach10.getModel().getValueAt(rowHienTai, columnHienTai).toString());
        ct.setMaSach(MaSachHienTai);
        ct.setLyDoThanhLy(cboLyDoThanhLy10.getSelectedItem().toString());
        ThuanChiTietThanhLyDAO.themChiTietPhieuThanhLy(ct);
        //  System.out.println("frmGUI.btnThuanThanhLySachActionPerformed() MaSachHienTai: " + MaSachHienTai);
        ResetThanhLy(tblDanhSachSach10, tblDanhSachSachThanhLy10);
    }//GEN-LAST:event_btnThuanThanhLySachActionPerformed
    //Lam tiep nhan nhanvien
    public static void KhoiTaoBangTiepNhanNhanVien(JTable tbNhanVien4) {
        tbNhanVien4.setModel(new ThuanTableModelNhanVien("select* from tblnhanvien"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGUI(1).setVisible(true);
            }
        });
    }

    private static TableModel createTableModel() {
        TableModel md = new UserTableModel.SachTableModel("select * from tblSach");
        return md;
    }

    private static TableModel createTableModelSachChuaMuon() {
        String sql = "select * from tblsach ts where ts.MaSach not in( select tc.MaSach from tblchitietphieumuon tc inner join tblphieumuonsach tm on tc.MaPhieuMuon = tm.MaPhieuMuon and tm.TrangThaiXoa = 0 ) and ts.MaSach not in(select tl.MaSach from tblchitietthanhly tl where tl.MaSach = ts.MaSach)";
        TableModel md = new UserTableModel.SachTableModel(sql);
        return md;
    }
    //phieu tra sach
    //

    private static void ResetThanhLy(JTable tblDanhSachSach10, JTable tblDanhSachSachThanhLy10) {
        String sqlDanhSachThanhLy = "select * from tblsach ts where ts.MaSach not in(select tl.MaSach from tblchitietthanhly tl where tl.MaSach = ts.MaSach)";
        TableModel mdDanhSachThanhLy = new UserTableModel.SachTableModel(sqlDanhSachThanhLy);
        tblDanhSachSach10.setModel(mdDanhSachThanhLy);

        String sqlDanhSachThanhLy10 = "select ts.MaSach, ts.TenSach, tc.LyDoThanhLy from tblsach ts inner join  tblchitietthanhly tc on ts.MaSach = tc.MaSach";
        DefaultTableModel mdDanhSachThanhLy10 = new DefaultTableModel(new String[]{"STT", "Mã Sách", "Tên Sách", "Lý Do Thanh Lý"}, 0);
        int i = 1;
        List<Object[]> dsThanhLy = ThuanThanhLySachDAO.layDanhSachDaThanhLy(sqlDanhSachThanhLy10);
        for (Object[] countResult : dsThanhLy) {
            mdDanhSachThanhLy10.addRow(new Object[]{i, countResult[0], countResult[1], countResult[2]});
            i++;
        }
        tblDanhSachSachThanhLy10.setModel(mdDanhSachThanhLy10);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuPhieu;
    private javax.swing.JButton btnThayMatKhau8;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem3;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnThem6;
    private javax.swing.JButton btnThem7;
    private javax.swing.JButton btnThuanThanhLySach;
    private javax.swing.JButton btnTraSach;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbMaTheDocGia;
    private javax.swing.JComboBox<String> cbbPhieuTraPhieuMuon;
    private javax.swing.JComboBox<String> cbbPhieuTraSachHoTen;
    private javax.swing.JComboBox<String> cboBangCap4;
    private javax.swing.JComboBox<String> cboBoPhan4;
    private javax.swing.JComboBox<String> cboChucVu4;
    private javax.swing.JComboBox<String> cboHoTenDocGia6;
    private javax.swing.JComboBox<String> cboHoTenDocGia7;
    private javax.swing.JComboBox<String> cboLoaiDocGia3;
    private javax.swing.JComboBox<String> cboLyDoThanhLy10;
    private javax.swing.JComboBox<String> cboNguoiGhiNhan7;
    private javax.swing.JComboBox<String> cboNguoiThu6;
    private javax.swing.JComboBox<String> cboNguoiTiepNhan5;
    private javax.swing.JComboBox<String> cboTheLoai5;
    private org.jdesktop.swingx.JXDatePicker dtpNgayMuon;
    private org.jdesktop.swingx.JXDatePicker dtpNgayNhap5;
    private org.jdesktop.swingx.JXDatePicker dtpNgayNhap6;
    private org.jdesktop.swingx.JXDatePicker dtpNgaySinh3;
    private org.jdesktop.swingx.JXDatePicker dtpNgaySinh4;
    private org.jdesktop.swingx.JXDatePicker dtpPhieuTraSachNgayTra;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbThuanNhanVienThanhLy;
    private javax.swing.JLabel lblBangCap4;
    private javax.swing.JLabel lblBoPhan4;
    private javax.swing.JLabel lblChucVu4;
    private javax.swing.JLabel lblConNo6;
    private javax.swing.JLabel lblDiaChi3;
    private javax.swing.JLabel lblDiaChi4;
    private javax.swing.JLabel lblEmail3;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblHoTen3;
    private javax.swing.JLabel lblHoTen4;
    private javax.swing.JLabel lblHoTen6;
    private javax.swing.JLabel lblHoTen7;
    private javax.swing.JLabel lblLoaiDocGia3;
    private javax.swing.JLabel lblLyDoThanhLy10;
    private javax.swing.JLabel lblMatKhauCu8;
    private javax.swing.JLabel lblMatKhauMoi8;
    private javax.swing.JLabel lblNgayGhiNhan7;
    private javax.swing.JLabel lblNgayNhap5;
    private javax.swing.JLabel lblNgaySinh3;
    private javax.swing.JLabel lblNgaySinh4;
    private javax.swing.JLabel lblNgaySinh5;
    private javax.swing.JLabel lblNguoiGhiNhan7;
    private javax.swing.JLabel lblNguoiThu6;
    private javax.swing.JLabel lblNhaXuatBan5;
    private javax.swing.JLabel lblSDT4;
    private javax.swing.JLabel lblSoTienConNo6;
    private javax.swing.JLabel lblSoTienNo6;
    private javax.swing.JLabel lblSoTienThu6;
    private javax.swing.JLabel lblTenSach5;
    private javax.swing.JLabel lblTenSach7;
    private javax.swing.JLabel lblTheLoai5;
    private javax.swing.JLabel lblTienNo6;
    private javax.swing.JLabel lblTienPhat7;
    private javax.swing.JLabel lblTitle11;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle5;
    private javax.swing.JLabel lblTitle6;
    private javax.swing.JLabel lblTitle7;
    private javax.swing.JLabel lblTitle8;
    private javax.swing.JLabel lblTitle9;
    private javax.swing.JLabel lblTriGia5;
    private javax.swing.JLabel lblTriGia6;
    private javax.swing.JPanel pblDanhSachNhanVien4;
    private javax.swing.JPanel pblDanhSachSach5;
    private javax.swing.JPanel pnlDanhSachPhieuPhat6;
    private javax.swing.JPanel pnlDanhSachPhieuPhat7;
    private javax.swing.JPanel pnlDanhSachSach10;
    private javax.swing.JPanel pnlDanhSachSachThanhLy10;
    private javax.swing.JPanel pnlDanhSachTheDocGia3;
    private javax.swing.JPanel pnlLapPhieuPhat;
    private javax.swing.JPanel pnlLapTheDocGia;
    private javax.swing.JPanel pnlMatSach;
    private javax.swing.JPanel pnlMuonSach;
    private javax.swing.JPanel pnlNhanTraSach;
    private javax.swing.JPanel pnlThanhLySach;
    private javax.swing.JPanel pnlThayMatKhau;
    private javax.swing.JPanel pnlThongTinNhanVien4;
    private javax.swing.JPanel pnlThongTinPhieuPhat6;
    private javax.swing.JPanel pnlThongTinSach5;
    private javax.swing.JPanel pnlThongTinSachMat6;
    private javax.swing.JPanel pnlThongTinThe3;
    private javax.swing.JPanel pnlTiepNhanNhanVien;
    private javax.swing.JPanel pnlTiepNhanSach;
    private javax.swing.JPasswordField psfMatKhauCu8;
    private javax.swing.JPasswordField psfMatKhauMoi8;
    private javax.swing.JTable tbMuon;
    private javax.swing.JTable tbPhieuTraSach;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tblDanhSachSach10;
    private javax.swing.JTable tblDanhSachSachThanhLy10;
    private javax.swing.JTable tblNhanVien4;
    private javax.swing.JTable tblPhieuPhat6;
    private javax.swing.JTable tblPhieuPhat7;
    private javax.swing.JTable tblSach5;
    private javax.swing.JTable tblTheDocGia3;
    private javax.swing.JLabel txtConLai;
    private javax.swing.JLabel txtConLai1;
    private javax.swing.JTextField txtDiaChi3;
    private javax.swing.JTextField txtDiaChi4;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtHoTen3;
    private javax.swing.JTextField txtHoTen4;
    private javax.swing.JLabel txtLoi;
    private javax.swing.JTextField txtNamXuatBan6;
    private javax.swing.JTextField txtNhaXuatBan5;
    private javax.swing.JTextField txtSDT4;
    private javax.swing.JLabel txtSoSachConLai;
    private javax.swing.JLabel txtSoSachDangChon;
    private javax.swing.JTextField txtSoTienThu6;
    private javax.swing.JTextField txtTenSach5;
    private javax.swing.JTextField txtTenSach7;
    private javax.swing.JTextField txtTienNo;
    private javax.swing.JTextField txtTienPhat7;
    private javax.swing.JTextField txtTienPhatKyNay;
    private javax.swing.JTextField txtTongNo;
    private javax.swing.JLabel txtTraSachLoi;
    private javax.swing.JTextField txtTriGia5;
    // End of variables declaration//GEN-END:variables
}
