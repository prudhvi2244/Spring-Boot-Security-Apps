package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping(value = "/home")
	public String homePage()
	{
		return "HomePage";
	}
	
	@GetMapping(value = "/welcome")
	public String welcomePage()
	{
		return "WelcomePage";
	}
	
	@GetMapping(value = "/admin")
	public String adminPage()
	{
		return "AdminPage";
	}
	
	@GetMapping(value = "/employee")
	public String employeePage()
	{
		return "EmployeePage";
	}
	
	@GetMapping(value = "/student")
	public String studentPage()
	{
		return "StudentPage";
	}
	
	@GetMapping(value = "/denied")
	public String accessDeniedPage()
	{
		return "AccessDenied";
	}
	
}
