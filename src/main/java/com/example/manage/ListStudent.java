package com.example.manage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.helper.RegexConst;
import com.example.helper.Valid;
import com.example.iservice.ICrud;
import com.example.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.example.database.ConnectDB;
import com.example.database.ConnectionDB;

public class ListStudent implements ICrud {
	private List<Student> listStd;
	
		//=====	constructor=======
	public ListStudent() {
		 listStd = new ArrayList<>();
	}
	
		//====getter setter======
	public List<Student> getListStd() {
		return listStd;
	}

	public void setListStd(List<Student> listStd) {
		this.listStd = listStd;
	}

	//=========thêm sinh viên================	
	@Override
	public void addList() {
		var std = new Student();
		std.insertStudent();
		listStd.add(std);
		
	}

	//========xóa sinh viên===============
	@Override
	public void deleteList() {
		int id = Integer.parseInt(Valid.input(RegexConst.ID,
				" Nhập id để xóa"));
		System.out.println(listStd.removeIf(s -> 
			s.getStdId() == id ) 
					? "xóa thành công " 
					: "không tồn tại id");
	} 
	
	

	// ================= tìm kiếm ================= 
//	=================tìm kiếm theo ID=========
	@Override
	public void seekStudentbyId() {
		int id = Integer.parseInt( Valid.input(RegexConst.ID,
				"Nhập id để tìm kiếm sinh viên : "));
		
		listStd.stream()
		.filter(s -> s.getStdId() == id)
		.findFirst()
		.ifPresentOrElse(
				s -> System.out.println(s) ,
				() -> System.out.println("Không tìm thấy sinh viên với ID: " + id)
		);
		}
	
//=================================================================================================
//	public void seekStudentbyId() {
//		int id = Integer.parseInt( Valid.input(RegexConst.ID,
//				"Nhập id để tìm kiếm : "));
//		
//		listStd.stream()
//		.filter(s -> s.getStdId() == id)
//		.forEach(System.out::println);
//	}
//	==============
//	@Override
//	public void seekStudentByName() {
//	String name =  Valid.input(RegexConst.NAME,
//			"Nhập tên để tìm kiếm sinh viên : ");
//	
//	System.out.println(listStd.stream()
//	.filter(s -> s.getStdName().equals(name)));
//	}
	//=================================================================================================	
	
	
//	===========  cập nhật lại sinh viên=========

	@Override
	public void updateList() {
System.out.println("===Vui lòng cập nhật lại thông tin sinh viên=== ");

		int id = Integer.parseInt(Valid.input(RegexConst.ID, 
				" Nhập mã sinh viên: "));
		listStd.stream().filter(s ->
							s.getStdId() == id )
		.forEach(s -> {
			s.setStdName(Valid.input(RegexConst.NAME,
						" Nhập tên sinh viên: "));
			
			s.setGender(Valid.input(RegexConst.GENDER,
					" Nhập giới tính " + "(1 - male, 0 - female)")
			.equals("1") ? true : false);
			
			s.setBirthDay(LocalDate.parse(
					Valid.input(RegexConst.DATE, 
							" Nhập ngày sinh (dd-MM-yyyy) (dd/MM/yyyy)"
						),
						DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]")
					));
			
			s.setAddress(Valid.input(RegexConst.ADDRESS, "Nhập địa chỉ: "));
		});
	}
	//=============sắp xếp================
	@Override
	public void sortList() {
		listStd.stream()
		.sorted(Comparator.comparing(Student::getStdId).reversed())
		.forEach(System.out::println);
		
	}
	
	//============= hiển thị================
	
	@Override
	public void showList() {
		System.out.println("hiển thị toàn bộ thông tin tất cả sinh viên");
		listStd.stream()
		.forEach(System.out::println);
	}
    
	//============= tính tuổi  ============= 
	@Override
	public void calAge() {
		int id = Integer.parseInt(Valid.input(RegexConst.ID, 
				"Nhập id để tính tuổi sinh viên: "));
		listStd.stream().filter(s -> s.getStdId() == id)
		.forEach(s ->{
			int age = s.calAge();
			System.out.println("Sinh viên có ID: " + id + ", Tuổi: " + age);
		});;
		}

//=============	thống kệ theo tháng=============
	public void thongKeTheoThang() {
		int[] thongKe = new int[12];
		this.listStd.forEach(s -> 
		thongKe[s.getBirthDay().getMonthValue() - 1]++);
		for(int i = 0 ; i < thongKe.length; i++) {
			System.out.printf("Tháng %d có %d sinh viên%n", i + 1, thongKe[i]);
		}
	}
    
   
//	========= đọc và lưu file json========
	// Phương thức lưu danh sách sinh viên vào file JSON
	
	public void saveToJson(String filePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.writeValue(new File(filePath), listStd);
		
    }
//=============	đọc lại toàn bộ tất cả sinh viên đã lưu từ file json lên=============
    public void loadFromJson(String filePath) throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
    	CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Student.class);
    	File file = new File(filePath);
    	if(file.exists()) {
    		listStd = mapper.readValue(file, listType);
//    		 Kiểm tra và xử lý giá trị null
    		for (Student student : listStd) {
                if (student.getStdName() == null) {
                    student.setStdName("Unknown"); // Hoặc giá trị mặc định khác
                }
            }
    	}else {
    		listStd = new ArrayList<>();
    	}
    		
    }
	 
    public void loadDB() throws SQLException {
    	Connection connection = null;
    	java.sql.Statement statement = null;
    	
    	try {
    		connection = ConnectionDB.getConnection();
			statement = connection.createStatement();
			
    		ResultSet rs = statement.executeQuery("select msv, name, birthday, address, gender from students");

    		listStd = new ArrayList<>();
    		while(rs.next()) {
				listStd.add(new Student(rs.getInt(1), rs.getString(2), rs.getBoolean(5), 
    					rs.getDate(3).toLocalDate(), rs.getString(4)));
    		}
    		System.out.println("Load students successful");
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		if (statement != null) {
    			statement.close();
    		}
    		ConnectionDB.close(connection);
    	}
    	
    }
    
    public void saveDB() throws SQLException {
    	if (listStd.isEmpty()) { 
    		// delete all
    		
		}
    	
    	String sqlInsert = "insert into students (name, birthday, address, gender) values(?, ?, ?, ?)";
    		
    	Connection connection = null;
    	java.sql.PreparedStatement statement = null;
    	
    	try {
    		connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(sqlInsert);
			
    		for (Student std : listStd) {
    			statement.setString(1, std.getStdName());
    			statement.setDate(2, java.sql.Date.valueOf(std.getBirthDay())); // Use the java.sql.Date instance
    			statement.setString(3, std.getAddress());
    			statement.setInt(4, std.isGender() ? 1 : 0); // Assuming gender is represented by an integer
//    			statement.addBatch();
    			int numberOfRowResult= statement.executeUpdate();
    		}
//    		statement.executeBatch();
    		
    		connection.commit();
    		
    		System.out.println("Save students successful!");
    	} catch(SQLException e) {
    		connection.rollback();
    	} finally {
    		if (statement != null) {
    			statement.close();
    		}
    		ConnectionDB.close(connection);
    	}
    }
    public static void updateStudent() {
		int id = Integer.parseInt(
				Valid.input(RegexConst.ID,
						"Vui lòng nhập ID sinh viên để cập nhật: "));
		String stdname = Valid.input(RegexConst.NAME, "Vui lòng nhập họ tên: ");

		
		boolean isGender = Valid.input(RegexConst.GENDER,
				"Vui lòng nhập giới tính (1 - nam, 0 - nữ): ")
				.equals("1");
		
		LocalDate birthday = LocalDate.parse(
				Valid.input(RegexConst.DATE,
						"Vui lòng nhập ngày sinh (dd-MM-yyyy) (dd/MM/yyyy): "),
				DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]"));

		String address = Valid.input(RegexConst.ADDRESS,
				"Vui lòng nhập địa chỉ: ");


		updateStdWithDB(id, stdname, isGender, java.sql.Date.valueOf(birthday), address);
	}
    public static void updateStdWithDB(int stdId, String stdname, boolean gender, java.sql.Date birthday, String address
			) {
		String sql = "UPDATE students " + "SET name = ?, " + " gender = ?, " + "birthday = ?,"
				+ "address = ? " + "WHERE msv = ?";

		try (
				Connection con = ConnectionDB.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, stdname);
			pstmt.setBoolean(2, gender);
			pstmt.setDate(3, birthday);
			pstmt.setString(4, address);
			pstmt.setInt(5, stdId);

			int rowsAffected = pstmt.executeUpdate();

			System.out.println("Cập nhật thành công cho sinh viên có ID: " + stdId + ". " + rowsAffected
					+ " bản ghi được cập nhật.");

		} catch (SQLException ex) {
			System.err.println("Lỗi khi cập nhật sinh viên: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void sortDescDB() {

		String sortField = "birthday"; // default sort field
		int offset = 1; // starting row offset
	    int pageSize = 5; // number of rows per page
	    int asc = 1;
    	try (
    			Connection connection = ConnectionDB.getConnection();
    			java.sql.CallableStatement statement = connection.prepareCall("{call GetStudentsPagedSorted(?, ?, ?,?)}");
    			

    			){
    		statement.setString(1, sortField);
    		statement.setInt(2, offset);
			statement.setInt(3, pageSize);
			statement.setInt(4, asc);
			ResultSet rs = statement.executeQuery();
    		
    		System.out.println("sap xếp danh sách sinh viên giảm dần: ");

    		while(rs.next()) {
				
				System.out.print(rs.getInt("msv"));
				System.out.print("\t" + rs.getString("name"));
				System.out.print("\t" + rs.getBoolean("gender"));
				System.out.print("\t" + rs.getDate("birthday"));
				System.out.print("\t" + rs.getString("address"));
				System.out.println();
    		}

    		listStd = new ArrayList<>();

        		while(rs.next()) {
        			Student student = new Student(
        					rs.getInt("msv"),
							rs.getString("name"),
							rs.getBoolean("gender"),
							rs.getDate("birthday").toLocalDate(),
							rs.getString("address")
							);	
        			listStd.add(student);
        			
    				System.out.print("\t" + rs.getInt("msv"));
    				System.out.print("\t" + rs.getString("name"));
    				System.out.print("\t" + rs.getBoolean("gender"));
    				System.out.print("\t" + rs.getDate("birthday"));
    				System.out.print("\t" + rs.getString("address"));
    				System.out.println();
    				System.out.println(student);
//    		)

    		} 
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} 
		
	
   
    
   
    
	}

	
}
