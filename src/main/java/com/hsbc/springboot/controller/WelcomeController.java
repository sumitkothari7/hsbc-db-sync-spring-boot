package com.hsbc.springboot.controller;

import java.util.List;
import java.util.Map;

import com.hsbc.springboot.derby.EmployeeD;
import com.hsbc.springboot.derby.EmployeeDRepository;
import com.hsbc.springboot.mysql.Employee;
import com.hsbc.springboot.mysql.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {


	@Autowired
	private EmployeeRepository employeeRepository;

	// inject via application.properties
	private String message = "HSBC DB Sync";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("title", this.message);
		return "index";
	}



	/*@RequestMapping("/mysql")
	public String mysql(Map<String, Object> model) {
		model.put("title", this.message);
		return "index";
	}*/

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

}