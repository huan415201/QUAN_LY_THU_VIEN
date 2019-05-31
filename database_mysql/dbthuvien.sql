-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 27, 2019 at 06:06 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbthuvien`
--

-- --------------------------------------------------------

--
-- Table structure for table `abc`
--

DROP TABLE IF EXISTS `abc`;
CREATE TABLE IF NOT EXISTS `abc` (
  `id` int(10) NOT NULL,
  `ma` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `abc`
--

INSERT INTO `abc` (`id`, `ma`) VALUES
(1, 'abc'),
(2, 'abc');

-- --------------------------------------------------------

--
-- Table structure for table `tblchitietphieumuon`
--

DROP TABLE IF EXISTS `tblchitietphieumuon`;
CREATE TABLE IF NOT EXISTS `tblchitietphieumuon` (
  `MaPhieuMuon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  PRIMARY KEY (`MaPhieuMuon`,`MaSach`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblchitietphieumuon`
--

INSERT INTO `tblchitietphieumuon` (`MaPhieuMuon`, `MaSach`) VALUES
(1, 1),
(1, 2),
(1, 4),
(1, 5),
(1, 6),
(2, 4),
(3, 3),
(3, 5),
(4, 1),
(4, 6),
(5, 1),
(5, 3),
(5, 5),
(6, 3),
(6, 5),
(6, 6),
(7, 3),
(7, 4),
(7, 5),
(8, 2),
(8, 3),
(8, 6),
(9, 2),
(9, 3),
(9, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tblchitietphieutra`
--

DROP TABLE IF EXISTS `tblchitietphieutra`;
CREATE TABLE IF NOT EXISTS `tblchitietphieutra` (
  `MaPhieuTra` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  PRIMARY KEY (`MaPhieuTra`,`MaSach`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblchitietphieutra`
--

INSERT INTO `tblchitietphieutra` (`MaPhieuTra`, `MaSach`) VALUES
(1, 1),
(1, 2),
(1, 4),
(1, 5),
(1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tblchitietthanhly`
--

DROP TABLE IF EXISTS `tblchitietthanhly`;
CREATE TABLE IF NOT EXISTS `tblchitietthanhly` (
  `MaPhieuThanhLy` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `LyDoThanhLy` varchar(50) NOT NULL,
  PRIMARY KEY (`MaPhieuThanhLy`,`MaSach`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblchitietthanhly`
--

INSERT INTO `tblchitietthanhly` (`MaPhieuThanhLy`, `MaSach`, `LyDoThanhLy`) VALUES
(19, 7, 'Mất'),
(18, 5, 'Mất'),
(17, 1, 'Hư hỏng'),
(16, 6, 'Hư hỏng');

-- --------------------------------------------------------

--
-- Table structure for table `tblmatsach`
--

DROP TABLE IF EXISTS `tblmatsach`;
CREATE TABLE IF NOT EXISTS `tblmatsach` (
  `MaTheDocGia` int(11) NOT NULL,
  `MaSachMat` int(11) NOT NULL,
  `MaNhanVienLap` int(11) NOT NULL,
  `NgayGhiNhan` datetime NOT NULL,
  `TienPhat` int(11) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaTheDocGia`,`MaSachMat`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tblnhanvien`
--

DROP TABLE IF EXISTS `tblnhanvien`;
CREATE TABLE IF NOT EXISTS `tblnhanvien` (
  `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT,
  `MatKhau` varchar(50) DEFAULT NULL,
  `BoPhan` varchar(50) NOT NULL,
  `ChucVu` varchar(50) NOT NULL,
  `LoaiBangCap` varchar(50) NOT NULL,
  `HoTen` varchar(50) NOT NULL,
  `DiaChi` varchar(50) NOT NULL,
  `NgaySinh` date NOT NULL,
  `SoDienThoai` varchar(10) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblnhanvien`
--

INSERT INTO `tblnhanvien` (`MaNhanVien`, `MatKhau`, `BoPhan`, `ChucVu`, `LoaiBangCap`, `HoTen`, `DiaChi`, `NgaySinh`, `SoDienThoai`, `TrangThaiXoa`) VALUES
(1, '123456', 'Thủ Kho', 'Nhân Viên', 'Tú Tài', 'Origi', 'Belgium', '2019-05-16', '123', 0),
(2, '123456', 'Thủ Thư', 'Nhân Viên', 'Trung Cấp', 'Salah', 'Ai cập', '1994-01-01', '123', 0),
(3, '123456', 'Thủ Thư', 'Nhân Viên', 'Tú Tài', 'Alisson Becker', 'Brazil', '1969-01-01', '131', 0),
(4, '123456', 'Thủ Thư', 'Nhân Viên', 'Tú Tài', 'Arnold', 'Anh', '1995-01-01', '1', 0),
(5, '123456', 'Thủ Thư', 'Nhân Viên', 'Tú Tài', 'coutinho', 'brazil', '1995-01-01', '123', 0),
(6, '123456', 'Thủ Thư', 'Nhân Viên', 'Tú Tài', 'adad', 'adad', '1995-01-01', '123', 0),
(7, '123456', 'Thủ Quỹ', 'Phó Phòng', 'Đại Học', 'Pique', 'TBN', '1997-01-01', '5', 0),
(8, '123456', 'Thủ Thư', 'Nhân Viên', 'Tú Tài', 'rakitic', 'croatia', '1995-01-01', '123', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblphieumuonsach`
--

DROP TABLE IF EXISTS `tblphieumuonsach`;
CREATE TABLE IF NOT EXISTS `tblphieumuonsach` (
  `MaPhieuMuon` int(11) NOT NULL AUTO_INCREMENT,
  `MaTheDocGia` int(11) NOT NULL,
  `NgayMuon` datetime NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaPhieuMuon`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblphieumuonsach`
--

INSERT INTO `tblphieumuonsach` (`MaPhieuMuon`, `MaTheDocGia`, `NgayMuon`, `TrangThaiXoa`) VALUES
(1, 1, '2019-05-17 00:00:00', 1),
(2, 1, '2019-05-17 00:00:00', 1),
(3, 1, '2019-05-17 00:00:00', 1),
(4, 2, '2019-05-17 00:00:00', 1),
(5, 1, '2019-05-17 00:00:00', 1),
(6, 1, '2019-05-17 00:00:00', 1),
(7, 1, '2019-05-17 00:00:00', 1),
(8, 1, '2019-05-18 00:00:00', 1),
(9, 1, '2019-05-18 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblphieuthutienphat`
--

DROP TABLE IF EXISTS `tblphieuthutienphat`;
CREATE TABLE IF NOT EXISTS `tblphieuthutienphat` (
  `MaPhieuThu` int(11) NOT NULL AUTO_INCREMENT,
  `MaTheDocGia` int(11) NOT NULL,
  `MaNhanVienThu` int(11) NOT NULL,
  `SoTienThu` int(11) NOT NULL,
  `NgayThu` datetime NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaPhieuThu`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tblphieutrasach`
--

DROP TABLE IF EXISTS `tblphieutrasach`;
CREATE TABLE IF NOT EXISTS `tblphieutrasach` (
  `MaPhieuTra` int(11) NOT NULL AUTO_INCREMENT,
  `MaTheDocGia` int(11) NOT NULL,
  `NgayTra` datetime NOT NULL,
  `TienPhat` int(11) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaPhieuTra`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblphieutrasach`
--

INSERT INTO `tblphieutrasach` (`MaPhieuTra`, `MaTheDocGia`, `NgayTra`, `TienPhat`, `TrangThaiXoa`) VALUES
(1, 1, '2019-05-17 00:00:00', 0, 0),
(2, 1, '2019-05-17 00:00:00', 0, 0),
(3, 1, '2019-05-17 00:00:00', 0, 0),
(4, 2, '2019-05-17 00:00:00', 0, 0),
(5, 1, '2019-05-17 00:00:00', 0, 0),
(6, 1, '2019-05-17 00:00:00', 0, 0),
(7, 1, '2019-05-17 00:00:00', 0, 0),
(8, 1, '2019-05-18 00:00:00', 0, 0),
(9, 1, '2019-05-18 00:00:00', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblsach`
--

DROP TABLE IF EXISTS `tblsach`;
CREATE TABLE IF NOT EXISTS `tblsach` (
  `MaSach` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVienTiepNhan` int(11) NOT NULL,
  `TenSach` varchar(50) NOT NULL,
  `TheLoai` varchar(50) NOT NULL,
  `TacGia` varchar(50) NOT NULL,
  `NamXuatBan` int(11) NOT NULL,
  `NhaXuatBan` varchar(50) NOT NULL,
  `NgayNhap` datetime NOT NULL,
  `TriGia` int(11) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaSach`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblsach`
--

INSERT INTO `tblsach` (`MaSach`, `MaNhanVienTiepNhan`, `TenSach`, `TheLoai`, `TacGia`, `NamXuatBan`, `NhaXuatBan`, `NgayNhap`, `TriGia`, `TrangThaiXoa`) VALUES
(1, 2, 'Henderson', '', 'Henderson', 2019, 'Henderson', '2019-05-23 00:00:00', 1, 0),
(2, 3, 'Shaquiri', '', 'Shaquiri', 2019, 'Shaquiri', '2019-05-23 00:00:00', 1, 0),
(3, 4, 'Van Dijk', '', 'Van Dijk', 2019, 'Van Dijk', '2019-05-23 00:00:00', 1, 0),
(4, 5, 'Arnold', '', 'Arnold', 2019, 'Arnold', '2019-05-23 00:00:00', 1, 0),
(5, 6, 'Son Huenmin', '', 'Son Huenmin', 2019, 'Son Huenmin', '2019-05-23 00:00:00', 1, 0),
(6, 7, 'Lucas Moura', '', 'Lucas Moura', 2019, 'Lucas Moura', '2019-05-23 00:00:00', 1, 0),
(7, 2, 'Lloris', 'Lloris', 'Lloris', 2018, 'Lloris', '2019-05-09 00:00:00', 200000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblthanhlysach`
--

DROP TABLE IF EXISTS `tblthanhlysach`;
CREATE TABLE IF NOT EXISTS `tblthanhlysach` (
  `MaPhieuThanhLy` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVienLap` int(11) NOT NULL,
  `NgayThanhLy` datetime NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaPhieuThanhLy`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblthanhlysach`
--

INSERT INTO `tblthanhlysach` (`MaPhieuThanhLy`, `MaNhanVienLap`, `NgayThanhLy`, `TrangThaiXoa`) VALUES
(1, 1, '2019-05-18 00:00:00', 0),
(2, 1, '2019-05-18 00:00:00', 0),
(3, 1, '2019-05-18 00:00:00', 0),
(4, 1, '2019-05-18 00:00:00', 0),
(5, 1, '2019-05-18 00:00:00', 0),
(6, 1, '2019-05-18 00:00:00', 0),
(7, 1, '2019-05-18 00:00:00', 0),
(8, 1, '2019-05-18 00:00:00', 0),
(9, 1, '2019-05-18 00:00:00', 0),
(10, 1, '2019-05-18 00:00:00', 0),
(11, 1, '2019-05-18 00:00:00', 0),
(12, 1, '2019-05-18 00:00:00', 0),
(13, 1, '2019-05-18 00:00:00', 0),
(14, 1, '2019-05-18 00:00:00', 0),
(15, 1, '2019-05-18 00:00:00', 0),
(16, 1, '2019-05-18 00:00:00', 0),
(17, 1, '2019-05-18 00:00:00', 0),
(18, 1, '2019-05-18 00:00:00', 0),
(19, 1, '2019-05-19 00:00:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblthedocgia`
--

DROP TABLE IF EXISTS `tblthedocgia`;
CREATE TABLE IF NOT EXISTS `tblthedocgia` (
  `MaTheDocGia` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVienLap` int(11) NOT NULL,
  `LoaiDocGia` varchar(200) NOT NULL,
  `HoTen` varchar(200) NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `NgaySinh` date NOT NULL,
  `NgayLapThe` date NOT NULL,
  `TienNo` int(11) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaTheDocGia`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblthedocgia`
--

INSERT INTO `tblthedocgia` (`MaTheDocGia`, `MaNhanVienLap`, `LoaiDocGia`, `HoTen`, `DiaChi`, `Email`, `NgaySinh`, `NgayLapThe`, `TienNo`, `TrangThaiXoa`) VALUES
(1, 1, 'A', 'Văn A', '1 Nguyễn Kim P.1 Q.1', 'vana@gmail.com', '2019-04-01', '2019-04-02', 6, 0),
(2, 1, 'A', 'Kinh Hãi', '2 Kim Lăng P.2 Q.2', 'hai@gmail.com', '2019-04-01', '2019-04-03', 0, 0),
(3, 1, 'A', 'Thất Lạc', '5 đường số 5 P.5 Q.5', 'lac@gmail.com', '1989-12-31', '1989-12-31', 0, 0),
(9, 1, 'A', 'Thất Lạc', '5 đường số 5 P.5 Q.5', 'lac@gmail.com', '1989-12-31', '1989-12-31', 0, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
