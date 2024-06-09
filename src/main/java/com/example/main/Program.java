package com.example.main;
import java.sql.SQLException;

import com.example.manage.ListStudent;
import com.example.menu.Menu;


//import java.text.ParseException;


public class Program {

	public static void main(String[] args) throws SQLException  {
		ListStudent listStudent = new ListStudent();
		listStudent.loadDB();
		
		Menu menu = new Menu(listStudent);
		menu.showMenu();
		
		// save database into db;
		listStudent.saveDB();
	}

}
