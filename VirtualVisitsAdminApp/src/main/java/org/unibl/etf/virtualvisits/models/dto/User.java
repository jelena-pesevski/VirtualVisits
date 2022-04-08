package org.unibl.etf.virtualvisits.models.dto;

import java.sql.Timestamp;

import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;

public class User {

	private Integer userId;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String mail;
	private Role role;
	private UserStatus status;
	private String otpToken;
	private Boolean isLoggedIn;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer userId, String firstname, String lastname, String username, String password, String mail, Role role,
			UserStatus status, String otpToken, Boolean isLoggedIn) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.role = role;
		this.status = status;
		this.otpToken = otpToken;
		this.isLoggedIn = isLoggedIn;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public String getOtpToken() {
		return otpToken;
	}
	public void setOtpToken(String otpToken) {
		this.otpToken = otpToken;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	
}
