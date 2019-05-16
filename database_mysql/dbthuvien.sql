-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 16, 2019 at 06:19 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

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

-- --------------------------------------------------------

--
-- Table structure for table `tblchitietthanhly`
--

DROP TABLE IF EXISTS `tblchitietthanhly`;
CREATE TABLE IF NOT EXISTS `tblchitietthanhly` (
  `MaPhieuThanhLy` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `LyDoThanhLy` text NOT NULL,
  PRIMARY KEY (`MaPhieuThanhLy`,`MaSach`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  `BoPhan` text NOT NULL,
  `ChucVu` text NOT NULL,
  `LoaiBangCap` text NOT NULL,
  `HoTen` text NOT NULL,
  `DiaChi` text NOT NULL,
  `NgaySinh` date NOT NULL,
  `SoDienThoai` varchar(10) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tblsach`
--

DROP TABLE IF EXISTS `tblsach`;
CREATE TABLE IF NOT EXISTS `tblsach` (
  `MaSach` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVienTiepNhan` int(11) NOT NULL,
  `TenSach` text NOT NULL,
  `TheLoai` text NOT NULL,
  `TacGia` text NOT NULL,
  `NamXuatBan` int(11) NOT NULL,
  `NhaXuatBan` text NOT NULL,
  `NgayNhap` datetime NOT NULL,
  `TriGia` int(11) NOT NULL,
  `TrangThaiXoa` tinyint(1) NOT NULL,
  PRIMARY KEY (`MaSach`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblthedocgia`
--

INSERT INTO `tblthedocgia` (`MaTheDocGia`, `MaNhanVienLap`, `LoaiDocGia`, `HoTen`, `DiaChi`, `Email`, `NgaySinh`, `NgayLapThe`, `TienNo`, `TrangThaiXoa`) VALUES
(1, 1, 'A', 'Văn A', '1 Nguyễn Kim P.1 Q.1', 'vana@gmail.com', '2019-04-01', '2019-04-02', 0, 0),
(2, 1, 'A', 'Kinh Hãi', '2 Kim Lăng P.2 Q.2', 'hai@gmail.com', '2019-04-01', '2019-04-03', 0, 0),
(3, 1, 'A', 'Thất Lạc', '5 đường số 5 P.5 Q.5', 'lac@gmail.com', '1989-12-31', '1989-12-31', 0, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
