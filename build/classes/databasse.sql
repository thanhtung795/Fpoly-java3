create database DBSinhVien
go

DROP DATABASE DBSinhVien

use DBSinhVien
go

drop table sinhvien

create table SinhVien(
	MaNV varchar(12),
	TenNV nvarchar(100),
	Email nvarchar(100),
	SoDT varchar(12),
	DiaChi nvarchar(100),
	GioiTinh bit ,
	Hinh varchar(200),
	Nganh nvarchar(15)
	primary key (MaNV)
);
drop table diem
go
create table Diem(
	MaNV varchar(12),
	HoTen Nvarchar(50),
	DiemJava1 float,
	DiemJava2 float,
	DiemJava3 float,
	primary key (MaNV)
)
go
alter table Diem
add constraint FK_Diem
foreign key (MaNV) 
references SinhVien(MaNV);
go

drop table Users
go
create table Users(
	Username varchar(50),
	Password varchar(50),
	role varchar(50),
	primary key (Username)
)
go
drop table Remember
go
create table Remember(
	Username varchar(50),
	Password varchar(50),
	Remenber bit
	primary key (Username)
)
go
INSERT INTO SinhVien
VALUES
('PS001', N'Nguyễn Văn A', N'AnvPS28425@fpt.edu.vn', '0123456789', N'Lê Văn Quới', 1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P3.jpg',N'CNTT'),
('PS002', N'Trần Thi C', N'CTTPS34525@fpt.edu.vn', '0123456789', N'CMT8', 0,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P1.jpg','TKDH'),
('PS003', N'Lê Thi B', N'BLTPS29315@fpt.edu.vn', '0123456789', N'Trần Hưng Đạo', 0,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P2.jpg',N'LTG'),
('PS004', N'Võ Thanh E', N'EVTPS23425@fpt.edu.vn', '0123456789', N'Trần Binh Trọng', 1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P2.jpg',N'LTG'),
('PS005', N'Huỳnh Đăng D', N'DHDPSPS27455@fpt.edu.vn', '0123456789', N'Kinh Dương Vương',1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P3.jpg',N'CNTT'),
('PS006', N'Nguyễn Văn A', N'AnvPS28425@fpt.edu.vn', '0123456789', N'Lê Văn Quới', 1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P1.jpg',N'TKDH'),
('PS007', N'Trần Thi C', N'CTTPS34525@fpt.edu.vn', '0123456789', N'CMT8', 0,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P1.jpg',N'CNTT'),
('PS008', N'Lê Thi B', N'BLTPS29315@fpt.edu.vn', '0123456789', N'Trần Hưng Đạo', 0,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P2.jpg',N'LTG'),
('PS009', N'Võ Thanh E', N'EVTPS23425@fpt.edu.vn', '0123456789', N'Trần Binh Trọng', 1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P2.jpg',N'CNTT'),
('PS010', N'Huỳnh Đăng D', N'DHDPSPS27455@fpt.edu.vn', '0123456789', N'Kinh Dương Vương',1,'D:\FPT\java3\ASM-Java3\src\ASM\hinh\P1.jpg',N'TKDH'); 




INSERT INTO Diem
VALUES
('PS001', N'Nguyễn Văn A',9,10,9),
('PS002', N'NTrần Thi C',9,9,9),
('PS003', N'Lê Thi B',7,6,6),
('PS004', N'Võ Thanh E',5,8,7),
('PS005', N'Huỳnh Đăng D',10,10,9),
('PS006', N'Nguyễn Văn A',8,7,9),
('PS007', N'Trần Thi C',6,7,7),
('PS008', N'Lê Thi B',8,6,6),
('PS009', N'Võ Thanh E',8,7,6),
('PS010', N'Huỳnh Đăng D',10,9,9);
go

insert into Users values
('admin','123','dao tao'),
('trai','123','giang vien'),
('ly','123','giang vien'),
('tung','123','giangvien')

SELECT top 3 *
FROM Diem
order by (DiemJava1 + DiemJava2 + DiemJava3) / 3 desc

SELECT top 3 *
   FROM Diem,SinhVien
                    where diem.MaNV = SinhVien.MaNV
                    order by (DiemJava1 + DiemJava2 + DiemJava3) / 3 desc
 

 select * from SinhVien

 select * from Diem

 select * from Users

 select * from Remember

 select sv.manv,tennv,Email,SoDt,diachi,gioitinh
                   		,DiemJava1,DiemJava2,DiemJava3,Nganh
                    from sinhvien sv
                    inner join diem d on sv.MaNV = d.MaNV


SELECT sv.*,DiemJava1,DiemJava2,DiemJava3
from sinhvien sv
inner join Diem d on d.MaNV = sv.MaNV
where Nganh = N'TKDH'



 delete from Remember


where Username = 'admin'

 delete from users
 where Username =  'a'

update SinhVien
set Hinh = 'P1.jpg'
where MaNV = 'PS007';
go
delete from sinhvien
where tennv like '22332'

