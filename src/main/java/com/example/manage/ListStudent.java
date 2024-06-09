package com.example.manage;
import java.io.File;
import java.io.IOException;
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
//		var std = new Student();
//		std.insertStudent();
//		listStd.add(std);
		
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
	 

	
}
