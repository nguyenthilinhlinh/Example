package com.example.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.example.helper.RegexConst;
import com.example.helper.Valid;

public class Student {
	//	====fields===== ;
	private int stdId;
	private String stdName;
	private boolean gender;
	private LocalDate birthDay;
	private String address;

//	====constructor ========//
	public Student() {
	}

	public Student(int stdId, String stdName, boolean gender, LocalDate birthDay, String address) {
//		if (stdName.length() > 50) {
//			throw new IllegalArgumentException("Tên sinh viên không được quá 50 ký tự");
//		}
		this.stdId = stdId;
		this.stdName = stdName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.address = address;
		System.out.println("constructor");
	}
	public Student(int stdId, String stdName) {
		this.stdId = stdId;
		this.stdName = stdName;
	}
	public Student(boolean gender) {
		this.gender = gender;
	}
	
	//=====methods===========/

	public int getStdId() {
		return stdId;
	}

	public void setStdId(int stdId) {
//		if (stdName.length() > 50) {
//			throw new IllegalArgumentException("Tên sinh viên không được quá 50 ký tự");
//		}
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		System.out.println("setstdname");
		if (stdName.length() > 50) {
			throw new IllegalArgumentException("Tên sinh viên không được quá 50 ký tự");
		}
		this.stdName = stdName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
//	==========insertStudent===================
	public void insertStudent() {
		System.out.println("====VUI LÒNG NHẬP THÔNG TIN SINH VIÊN====");
	
		stdId = Integer.parseInt(Valid.input(RegexConst.ID, 
				"Nhập mã sinh viên:"));
		
		stdName = 
				Valid.input(RegexConst.NAME,
						"Nhập tên sinh viên:");
		gender =
				Valid.input(RegexConst.GENDER,
						"Nhập giới tính sinh viên - " + "(1 - male, 0 - female)")
				.equals("1") ? true : false;
		
		birthDay = LocalDate.parse(
				Valid.input(RegexConst.DATE, 
						"Nhập ngày sinh (dd-MM-yyyy) (dd/MM/yyyy)"
					),
					DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]")
				); 
		address = Valid.input(RegexConst.ADDRESS, "Nhập địa chỉ: ");
	}
		
//	================== tính tuổi ==================
	public int calAge() {
		return Period.between(this.birthDay, LocalDate.now()).getYears();
		
	}

	@Override
	public String toString() {
		var genderStr = this.gender ? "nam" : "nữ";
		return "Student [stdId=" + stdId + ", stdName=" + stdName + ", gender=" + genderStr + ", birthDay=" + birthDay
				+ ", address=" + address + ", Age=" + calAge() + "]";
	}
	
	

 

}
