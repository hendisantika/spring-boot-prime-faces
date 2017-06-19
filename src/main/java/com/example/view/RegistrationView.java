package com.example.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.model.User;
import com.example.service.UserService;

@ManagedBean(name="registrationView")
@RequestScoped
@Controller
public class RegistrationView {
	
	@Autowired
    private UserService userService;
	private User user = null;
	
	public RegistrationView(){
		super();
		user = new User();
	}
	
	
	public void userRegistration(){
		userService.saveUser(user);
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
