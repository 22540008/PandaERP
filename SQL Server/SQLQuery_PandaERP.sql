-- XOÁ DATABASE
USE master;
GO
ALTER DATABASE PandaERP SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE PandaERP;
GO

-- TẠO DATABASE
CREATE DATABASE PandaERP;
GO
USE PandaERP;
SET DATEFORMAT dmy;

------------------------------------------------
-- Bảng Users
DROP TABLE Users
DROP TABLE NhanVien

CREATE TABLE Users (
    tenTK VARCHAR(255) PRIMARY KEY,
    matKhau NVARCHAR(255),
    systemRoles NVARCHAR(MAX),
    trangThai INT
);

CREATE TABLE NhanVien (
    maNV INT PRIMARY KEY,
    tenTK VARCHAR(255),
    ho NVARCHAR(255),
    ten NVARCHAR(255),
    chucVu NVARCHAR(255),
    phongBan NVARCHAR(255),
    diaChi NVARCHAR(255),
    soDT NVARCHAR(20),
    --CONSTRAINT FK_tenTK FOREIGN KEY (tenTK) REFERENCES Users(tenTK)
);

-- Bảng Users
INSERT INTO Users (tenTK, matKhau, systemRoles, trangThai) VALUES
('admin', '123456', 'admin;requester;buyer', 0),
('tqhung', '123456', 'PR;PO;GR', 0),
('ptnam', '123456', 'requester', 0);

-- Bảng NhanVien
INSERT INTO NhanVien (maNV, tenTK, ho, ten, chucVu, phongBan, diaChi, soDT) VALUES
(100001, 'admin', NULL, 'admin', N'Trưởng phòng IT', 'MIS', N'số 01, đường Hàn Thuyên, Khu phố 6, phường Linh Trung, Thành phố Thủ Đức, Tp. HCM', '(028) 372 52002'),
(100002, 'tqhung', N'Trần Quang', N'Hùng', N'NV mua hàng', N'Chuỗi cung ứng', N'Thủ Đức-HCM', '+84 775822269'),
(100003, 'ptnam', N'Phạm Tiến', N'Nam', N'NV kinh doanh', 'Kinh doanh', N'Biên Hoà-Đồng Nai', '1800 9090');

SELECT * FROM Users
SELECT * FROM NhanVien
SELECT * FROM Users JOIN NhanVien ON Users.tenTK = NhanVien.tenTK

SELECT maNV, Users.tenTK, matKhau, systemRoles, trangThai, ho, ten, chucVu, phongBan, diaChi, soDT
FROM Users JOIN NhanVien ON Users.tenTK = NhanVien.tenTK
WHERE Users.TenTK = 'tqhung' AND matKhau = '123456'


-- Bảng Item
DROP TABLE Item;
CREATE TABLE Item (
    maHang INT PRIMARY KEY,
    tenHang NVARCHAR(255),
    dvt NVARCHAR(50),
	donGia MONEY,
    trangThai INT
);

-- Thêm dữ liệu vào bảng Item
INSERT INTO Item (maHang, tenHang, dvt, donGia, trangThai) VALUES
(100001, 'Item 1', 'pcs', 10000000, 0),
(100002, 'Item 2', 'box', 15000000, 0),
(100003, 'Item 3', 'ea', 1050000, 0),
(100004, 'Item 4', 'pc', 200000, 0),
(100005, 'Item 5', 'ea', 120000, 0),
(100006, 'Item 6', 'box', 500000, 0),
(100007, 'Item 7', 'ctn', 100007, 0);


SELECT * FROM  Item
SELECT * FROM Item WHERE maHang = 100001
UPDATE Item SET tenHang = N'Trial1', dvt = 'pc', donGia = 1000 WHERE maHang = 100006
UPDATE Item Set trangThai = 1 WHERE maHang = 100007


DROP TABLE Vendor
-- Bảng Vendor
CREATE TABLE Vendor (
    maNCC INT PRIMARY KEY,
    tenNCC NVARCHAR(50),
    diaChi NVARCHAR(100),
    mST NVARCHAR(20),
	vat NUMERIC(4, 2),
    trangThai INT
);

-- Thêm dữ liệu vào bảng Vendor
INSERT INTO Vendor (maNCC, tenNCC, diaChi, mST, vat, trangThai) VALUES
(1001, 'Vendor 1', '123 First Ave', '123456', 10, 0),
(1002, 'Vendor 2', '456 Second St', '789012', 10, 0),
(1003, 'Vendor 3', '789 Third Rd', '345678', 10, 0),
(1004, 'Vinamilk', 'VN', '084', 8, 0),
(1005, 'Umbrella', 'America', '000-001', 5, 0);

SELECT * FROM Vendor
SELECT * FROM Vendor WHERE maNCC = 1002
INSERT INTO Vendor (maNCC, tenNCC, diaChi, mST, trangThai) VALUES (1004, N'Đấu phá thương khung', N'Trung Quốc', '123456', 0);



-- Tạo bảng PurchaseRequest
DROP TABLE PurchaseRequest;

CREATE TABLE PurchaseRequest (
	soCT_line VARCHAR(13) PRIMARY KEY,
    soCT INT,
    nguoiTao VARCHAR(255),
    ngayTao DATE,
    ngaySua DATE,
	trangThai INT,
	ItemLine INT,
    maHang INT,
	giaEst MONEY,
	soLuong INT,
	giaTong MONEY
);

-- Thêm dữ liệu mẫu vào bảng PurchaseRequest
INSERT INTO PurchaseRequest (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, ItemLine, maHang, giaEst, soLuong, giaTong) VALUES
('1190001_1', 1190001, 'Nguyen Van A', '01/01/2022', '01/02/2022', 3, 1, 100001, 0, 5, 0),
('1190002_1', 1190002, 'Nguyen Van B', '01-01-2022', '01-02-2022', 3, 1, 100002, 0, 8, 0),
('1190003_1', 1190003, 'Nguyen Van C', '01/01/2022', '01/02/2022', 3, 1, 100003, 0, 10, 0);


SELECT * FROM PurchaseRequest;
SELECT PurchaseRequest.*, Item.*, PurchaseRequest.maHang AS PR_maHang, Item.maHang AS Item_maHang
FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang;
SELECT *
FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang;
UPDATE PurchaseRequest SET trangThai = 0 WHERE soCT =  1240001 AND itemLine = 1;
UPDATE PurchaseRequest SET trangThai = 0, soLuong = 33 WHERE SoCT = 1240009 AND itemLine = 1;

-- Bảng PurchaseOrders
DROP TABLE PurchaseOrder;

CREATE TABLE PurchaseOrder (
    soCT_line VARCHAR(13) PRIMARY KEY,
    soCT INT,
    nguoiTao VARCHAR(255),
    ngayTao DATE,
    ngaySua DATE,
	trangThai INT,
	itemLine INT,
	maNCC INT,
	gia MONEY,
	soLuong INT,
	vat NUMERIC(4,2),
	giaTong MONEY,
	slChoNhan INT,
);

-- Thêm dữ liệu mẫu vào bảng PurchaseOrder
INSERT INTO PurchaseOrder (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine, maNCC, gia, soLuong, vat, giaTong, slChoNhan) VALUES
('2190001_1', 2190001, N'tqhung', '01/01/2022', '01/02/2022', 0, 1, 1001, 1000, 15, 10, 0, 14),
('2190002_1', 2190002, N'ptnam', '01-01-2022', '01-02-2022', 0, 1, 1002, 2000, 18, 10, 0, 17),
('2190003_1', 2190003, N'admin', '01/01/2022', '01/02/2022', 0, 1, 1003, 3000, 12, 10, 0, 11);


-- Bảng PO_PR
DROP TABLE PO_PR

CREATE TABLE PO_PR (
    soPO_line VARCHAR(13),
    soPR_line VARCHAR(13),
    --FOREIGN KEY (soPO_line) REFERENCES PurchaseOrder(soCT),
    --FOREIGN KEY (soPR_line) REFERENCES PurchaseRequest(soCT),
    PRIMARY KEY (soPO_line, soPR_line)
);

-- Thêm dữ liệu mẫu vào bảng PO-PR
INSERT INTO PO_PR (soPO_line, soPR_line) VALUES
('2190001_1', '1190001_1'),
('2190002_1', '1190002_1'),
('2190003_1', '1190003_1');

SELECT * FROM PurchaseOrder;
SELECT * FROM PO_PR;
SELECT *
FROM PurchaseRequest JOIN Item ON PurchaseRequest.maHang = Item.maHang;
UPDATE PurchaseRequest SET trangThai = 0 WHERE soCT =  1240001 AND itemLine = 1;
UPDATE PurchaseRequest SET trangThai = 0, soLuong = 33 WHERE SoCT = 1240009 AND itemLine = 1;

SELECT *
FROM PurchaseOrder JOIN PO_PR ON PurchaseOrder.soCT_line = PO_PR.soPO_line
	JOIN PurchaseRequest ON PO_PR.soPR_line = PurchaseRequest.soCT_line
	JOIN Item ON PurchaseRequest.maHang = Item.maHang
	JOIN Vendor ON PurchaseOrder.maNCC = Vendor.maNCC
WHERE PurchaseOrder.trangThai NOT IN (1);


INSERT INTO PurchaseOrder (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine, maNCC, gia, soLuong, vat, giaTong, slChoNhan) VALUES
('2240001_1', 2240001, N'tqhung', '01/01/2022', '01/02/2022', 0, 0, 1001, 2000, 5, 0.1, 0, 5);
INSERT INTO PO_PR (soPO_line, soPR_line) VALUES ('2240001_1', '1190001_1');

UPDATE PurchaseOrder SET ngaySua = N'17-05-2024', maNCC = 10002, gia = 1000, soLuong = 10, vat = 10, giaTong = 010000 WHERE soCT_line = '2190001_1';



-- Bảng GoodsReceipt
DROP TABLE GoodsReceipt;

CREATE TABLE GoodsReceipt (
    soCT_line VARCHAR(13) PRIMARY KEY,
    soCT INT,
    nguoiTao VARCHAR(255),
    ngayTao DATE,
    ngaySua DATE,
	trangThai INT,
	itemLine INT,
	soPO_line VARCHAR(13),
	--soPR_line VARCHAR(13),
	--soLuong INT,
	slNhan INT,
	luuKho INT,
	lanCuoi BIT
);

-- Thêm dữ liệu mẫu vào bảng GoodsReceipt
INSERT INTO  GoodsReceipt (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine, soPO_line, slNhan, luuKho, lanCuoi) VALUES
('3190001_1', 3190001, N'tqhung', '01/01/2022', '01/02/2022', 3, 1, '2190001_1', 1, 0, 0),
('3190002_1', 3190002, N'ptnam', '01-01-2022', '01-02-2022', 3, 1, '2190002_1', 1, 1, 0),
('3190003_1', 3190003, N'admin', '01/01/2022', '01/02/2022', 3, 1, '2190003_1', 1, 1, 0);


SELECT * FROM  GoodsReceipt;

SELECT *
FROM GoodsReceipt JOIN PurchaseOrder ON GoodsReceipt.soPO_line = PurchaseOrder.soCT_line
	JOIN PO_PR ON PurchaseOrder.soCT_line = PO_PR.soPO_line
	JOIN PurchaseRequest ON PO_PR.soPR_line = PurchaseRequest.soCT_line
	JOIN Item ON PurchaseRequest.maHang = Item.maHang
	JOIN Vendor ON PurchaseOrder.maNCC = Vendor.maNCC


SELECT *
FROM PurchaseOrder JOIN PO_PR ON PurchaseOrder.soCT_line = PO_PR.soPO_line
    JOIN PurchaseRequest ON PO_PR.soPR_line = PurchaseRequest.soCT_line
    JOIN Item ON PurchaseRequest.maHang = Item.maHang
    JOIN Vendor ON PurchaseOrder.maNCC = Vendor.maNCC
WHERE PurchaseOrder.trangThai IN (0, 3);

INSERT INTO  GoodsReceipt (soCT_line, soCT, nguoiTao, ngayTao, ngaySua, trangThai, itemLine, soPO_line, slNhan, luuKho, lanCuoi) VALUES
('3240001_1', 3240001 , N'tqhung', '19/05/2024', '19/05/2024', 4, 1, '2240001_1', 1, 1, 0);





