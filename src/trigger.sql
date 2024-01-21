CREATE TRIGGER trg_AfterInsertSinhVien
ON SinhVien
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MaNV NVARCHAR(50);

    -- Sử dụng bảng Inserted để lấy thông tin MaNV từ dòng mới được thêm
    SELECT @MaNV = MaNV
    FROM Inserted;

    -- Lấy HoTen từ bảng Inserted (chứa dữ liệu mới được chèn)
    DECLARE @HoTen NVARCHAR(255);
    SELECT @HoTen = TenNV
    FROM Inserted;

    -- Thêm dòng mới vào bảng Diem
    INSERT INTO Diem (MaNV, HoTen)
    VALUES (@MaNV, @HoTen);
END;
go

CREATE OR ALTER TRIGGER trg_BeforeDeleteSinhVien
ON SinhVien
instead of DELETE
AS
BEGIN
	set nocount on;

	delete from Diem where MaNV in (select MaNV from deleted)
	delete from SinhVien where MaNV in (select MaNV from deleted)
END;
go

CREATE or alter TRIGGER UpdateDiem
ON SinhVien
AFTER UPDATE
AS
BEGIN
    UPDATE Diem
    SET Diem.HoTen = i.TenNV
    FROM Diem
    INNER JOIN inserted i ON Diem.MaNV = i.MaNV
    INNER JOIN deleted d ON Diem.MaNV = d.MaNV
    WHERE Diem.HoTen != i.TenNV
END
