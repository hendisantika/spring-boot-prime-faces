package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String index(){
		return "Waktu Sekarang : " + new Date();
	}

	@RequestMapping(path="/employee", method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable("id") long id){
		return employeeService.getEmployeeById(id);
	}

    @RequestMapping(value = "/error-test", method = RequestMethod.GET)
	public Employee error() throws Exception{
		throw new Exception();
	}
}
