package com.example.service;

import com.example.model.Employee;

import java.util.List;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	
}
