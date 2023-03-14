package com.contexio.maintenance.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/")
	public String indexPage() {
		return "maintenancePage.html";
	}
	
	
	@PostMapping("/addUserdetails")
	@ResponseBody
	public String UserDetails(@RequestParam(name = "name") String name, @RequestParam(name = "age") String age, @RequestParam(name= "email") String email, @RequestParam(name= "password") String password)
	{
		System.out.println("name is: "+name);
		System.out.println("age is: "+age);
		System.out.println("email is: "+email);
		System.out.println("password is: "+password);
		
		Connection con = null;
		java.sql.Statement stmt= null;
		ResultSet rs = null;
		
		try
		{
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","root");
			 PreparedStatement ps = con.prepareStatement("insert into userdetails values(?,?,?,?,?)");
			    ps.setInt(1,0);
			    ps.setString(2, name);
				ps.setString(3, age);
				ps.setString(4,email);
				ps.setString(5, password);
				ps.executeUpdate();
			 	System.out.println("Data inserted sucessfully");
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		
		return "My name is"+name;
	}
	
}
