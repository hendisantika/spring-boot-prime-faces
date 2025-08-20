package com.example.view;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component("registrationView")
@Scope("request")
@Controller
public class RegistrationView {
	
	@Autowired
    private UserService userService;
	private User user = null;
	
	public RegistrationView(){
		super();
		user = new User();
	}


    public String userRegistration() {
        try {
            userService.saveUser(user);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User registered successfully"));
            return "index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Registration failed: " + e.getMessage()));
            return null;
        }
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
