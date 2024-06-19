package com.example.menu;
import java.io.IOException;
import java.sql.SQLException;

import com.example.helper.RegexConst;
import com.example.helper.Valid;
import com.example.manage.ListStudent;
public class Menu {
	
	private ListStudent list;
	
	public Menu(ListStudent list) {
		this.list = list;
	}
	
	public void showMenu() {
//		Iterator<Student> students = STUDENT_MAPS.value().iterator();
		
	
		boolean _continue = true;
		while(_continue) {
			System.out.println("==========input selection===========");
			System.out.println("Chọn 1 để thêm 1 sinh viên ");
			System.out.println("Chọn 2 xóa sinh viên");
			System.out.println("Chọn 3 để tìm kiếm sinh viên");
			System.out.println("Chọn 4 để cập nhật sinh viên");
			System.out.println("Chọn 5 để sắp xếp danh sách sinh viên");
			System.out.println("Chọn 6 để hiển thị toàn bộ thông tin tất cả sinh viên");
			System.out.println("Chọn 7 tính tuổi sinh viên");
			System.out.println("Chọn 8 thống kê sinh viên theo tháng");
			System.out.println("Chọn 9 để lưu danh sách sinh viên xuống tệp JSON");
			System.out.println("Chọn 10 để đọc danh sách sinh viên từ tệp JSON");
			System.out.println("Chọn 11 để đọc danh sách sinh viên từ database");
			System.out.println("Chọn 12 để lưu danh sách vào cơ sở dữ liệu");
            System.out.println("Chọn bất kì để thoát");
			System.out.println("=================================");
			
			int choose = Integer.parseInt(
					Valid.input(RegexConst.ID, "==========vui lòng chọn :=========="));
			
			
			switch(choose) {
			case 1 -> list.addList();
			case 2 -> list.deleteList();
			case 3 -> list.seekStudentbyId();
			case 4 -> list.updateList();
			case 5 -> list.sortList();
			case 6 -> list.showList();
			case 7 -> list.calAge();
			case 8 -> list.thongKeTheoThang();
			case 9 -> {
				try {
					list.saveToJson("students.json");
					System.out.println("lưu danh sách sinh viên thành công");
				}catch(IOException e){
					System.err.println("Lỗi khi lưu danh sách sinh viên: " + e.getMessage());
				}
			}
			case 10 -> {
				try {
					list.loadFromJson("students.json");
					System.out.println("Đọc danh sách sinh viên thành công.");
					list.showList();
				}catch(IOException e) {
					System.err.println("Lỗi khi đọc danh sách sinh viên: " + e.getMessage());
				}
			}
			case 11 ->{
				try {
					list.loadDB();
					System.out.println("đọc danh sách sinh viên từ database");
			}
			catch (SQLException e) {
				e.printStackTrace();}}
			case 12 ->{
				try {
					list.saveDB();
					System.out.println("danh sách sinh viên đã được lưu lên database");
			}
			catch (SQLException e) {
				e.printStackTrace();}}
			default -> _continue = false;
		}
		}
		
	}
}
