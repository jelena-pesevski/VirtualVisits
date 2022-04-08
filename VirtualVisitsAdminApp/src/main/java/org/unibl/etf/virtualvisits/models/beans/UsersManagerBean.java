package org.unibl.etf.virtualvisits.models.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.unibl.etf.virtualvisits.models.dao.LogDAO;
import org.unibl.etf.virtualvisits.models.dao.UserDAO;
import org.unibl.etf.virtualvisits.models.dto.Log;
import org.unibl.etf.virtualvisits.models.dto.User;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;

public class UsersManagerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String usernameRegex="^[^@#/]+$";
	private final String passwordRegex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{15,}$";
	private final String mailRegex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public ArrayList<User> getUsers(){
		return UserDAO.getAll();
	}
	
	public User getById(Integer id) {
		return UserDAO.getById(id);
	}
	
	public boolean insertUser(User user) {
		//check restrictions
		if(!checkRestrictions(user, true)) {
			return false;
		}
				
		return UserDAO.insert(user);
	}
	
	public boolean updateUser(User user, boolean changePass) {
		//check restrictions
		if(!checkRestrictions(user, changePass)) {
			return false;
		}
	
		return UserDAO.update(user, changePass);
	}
	
	public boolean deleteUser(Integer userId) {
		return UserDAO.delete(userId);
	}
		 
	public boolean resetPassword(Integer userId) {
		String password=generateRandomPassword();
	
		if(password.matches(passwordRegex)) {
			return UserDAO.updatePassword(userId, password);
			//send on mail
		}
		return false;
	}
	
	
	public String generateRandomPassword() {
		Random rand=new Random();
		StringBuilder newPassword=new StringBuilder();
		
		for(int i=0; i<5; i++) {
			newPassword.append(String.valueOf((char) (rand.nextInt(27) + 'A')));
		}
		
		for(int i=0; i<7; i++) {
			newPassword.append(String.valueOf((char) (rand.nextInt(27) + 'a')));
		}
		
		for(int i=0; i<3; i++) {
			newPassword.append(String.valueOf((char) (rand.nextInt(10) + '0')));
		}
		
		System.out.println("Generated password:"+newPassword.toString());
		return newPassword.toString();
	}
	
	public boolean checkRestrictions(User user, boolean checkPass) {
		if(user.getFirstname()==null || user.getLastname()==null || user.getUsername()==null || user.getMail()==null
				|| user.getPassword()==null || user.getRole()==null || user.getStatus()==null) {
			return false;
		}
		if(!user.getUsername().matches(usernameRegex) || user.getUsername().length()<12 || !user.getMail().matches(mailRegex)) {
			return false;
		}
		if(checkPass && !user.getPassword().matches(passwordRegex)) {
			return false;
		}
		
		return true;
	}
}
