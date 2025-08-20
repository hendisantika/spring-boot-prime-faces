package com.example.view;


import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component("employeeView")
@Scope("request")
@Controller
public class EmployeeView{
		
	@Autowired
    private EmployeeService employeeService;


    public List<Employee> getEmployeesList() {
        return employeeService.getAllEmployees();
    }

}