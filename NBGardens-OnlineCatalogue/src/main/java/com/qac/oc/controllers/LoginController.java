package com.qac.oc.controllers;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.qac.oc.services.LoginService;

@RequestScoped
@Named(value="login")
public class LoginController {
	@Inject
	private CurrentUser currentUser;
	@Inject
	private LoginService loginService;
	private String email;
	private String password;
	
	public String login() {
		//DoThings
		if(!email.isEmpty() && !password.isEmpty())
			if(loginService.validLogin())
				currentUser.setCustomer(loginService.loginUser(email, password));
		return "nbgardens";
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}