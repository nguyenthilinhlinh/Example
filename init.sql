
-- create students db
CREATE TABLE students (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    address NVARCHAR(255),
    gender TINYINT 
);

IF OBJECT_ID('GetStudentsPagedSorted', 'P') IS NOT NULL
    DROP PROCEDURE GetStudentsPagedSorted;
GO


create  proc  GetStudentsPagedSorted
  @SortField NVARCHAR(50),
  @Offset INT,
  @PageSize INT,
  @asc int
AS
BEGIN
  DECLARE @SQL NVARCHAR(MAX);
  DECLARE @OderBy NVARCHAR(MAX);
  DECLARE @OffsetParam NVARCHAR(MAX);
  DECLARE @FetchParam NVARCHAR(MAX);

  SET @OderBy = QUOTENAME(@SortField)  + CASE WHEN @asc = 1 THEN ' ASC ' ELSE ' DESC ' END;
  SET @OffsetParam = CONVERT(nvarchar,@Offset)
  SET @FetchParam = CONVERT(nvarchar,@PageSize)

  SET @SQL = N'
        SELECT msv, name, birthday, address, gender 
        FROM students
        ORDER BY ' + @OderBy   + 
        'OFFSET ' +  @OffsetParam + N' ROWS
        FETCH NEXT ' + @FetchParam + N' ROWS ONLY';
    EXEC sp_executesql @SQL;
  
END ;
GO
	INSERT INTO students(name,gender,birthday,address)
	VALUES (N'Linh Linh',0, '2003-11-28', N'Đồng Nai')

Exec INSERTSTUDENS
EXEC GetStudentsPagedSorted @SortField = 'birthday' , @Offset = 0, @PageSize = 5, @asc = 1

