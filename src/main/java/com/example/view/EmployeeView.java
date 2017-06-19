package com.example.view;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@ManagedBean(name="employeeView")
@RequestScoped
@Controller
public class EmployeeView{
		
	@Autowired
    private EmployeeService employeeService;


    public List<Employee> getEmployeesList() {
        return employeeService.getAllEmployees();
    }

}