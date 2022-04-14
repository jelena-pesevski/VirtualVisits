package org.unibl.etf.virtualvisits.models.beans;

import java.time.Instant;

import org.unibl.etf.virtualvisits.models.dao.LogDAO;
import org.unibl.etf.virtualvisits.models.dao.UserDAO;
import org.unibl.etf.virtualvisits.models.dto.Log;
import org.unibl.etf.virtualvisits.models.dto.User;

public class UserBean {

	private User currUser;
	private boolean loggedIn=false;
	
	public boolean loginWithToken(String token) {
		if(token==null) {
			return false;
		}
		
		User user=UserDAO.getActiveAdminByOtpToken(token);
		if(user!=null) {
			currUser=user;
			user.setOtpToken(null);
			UserDAO.update(user, false);
			LogDAO.insert(new Log(null, "Admin "+ currUser.getUsername()+ " logged into admin JSP app." ,"LOGIN-ADMIN-APP", Instant.now(), currUser.getUsername()));
			loggedIn=true;
			
			return true;
		}else {
			return false;
		}
	}
	
	public boolean loginUsernamePassword(String username, String password) {
		if(username==null || password==null) {
			return false;
		}
		
		User user=UserDAO.getActiveAdminByUsernameAndPassword(username, password);
		if(user!=null) {
			currUser=user;
			UserDAO.update(user, false);
			LogDAO.insert(new Log(null, "Admin "+ currUser.getUsername()+ " logged into admin JSP app." ,"LOGIN-ADMIN-APP", Instant.now(), user.getUsername()));
			loggedIn=true;
			
			return true;
		}else {
			LogDAO.insert(new Log(null, "Login failed for username:"+username+ " and password:"+password ,"LOGIN-ADMIN-APP-FAIL", Instant.now(), null));
			return false;
		}
		
	}
	
	public void logout() {
		LogDAO.insert(new Log(null, "Admin "+ currUser.getUsername()+ " logged out from admin JSP app." ,"LOGOUT ADMIN APP", Instant.now(), currUser.getUsername()));
		//insert log entity
	}
	
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public User getCurrUser() {
		return currUser;
	}

	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}
	
	
}
