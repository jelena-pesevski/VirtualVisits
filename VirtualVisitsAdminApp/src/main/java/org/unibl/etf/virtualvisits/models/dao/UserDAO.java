package org.unibl.etf.virtualvisits.models.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import org.unibl.etf.virtualvisits.models.dto.User;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;
import org.unibl.etf.virtualvisits.utils.ConnectionPool;
import org.unibl.etf.virtualvisits.utils.DAOUtil;

public class UserDAO {

	private static final ConnectionPool pool=ConnectionPool.getInstance();
	private static final String SELECT_ALL="SELECT * FROM user";
	private static final String INSERT="INSERT INTO user (firstname, lastname, username, password, mail, role, status) VALUES (?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_USER_BY_OTP_TOKEN="SELECT * FROM user WHERE otp_token=? and role=? and status=? ";
	private static final String GET_BY_ID="SELECT * FROM user WHERE user_id=? ";
	private static final String GET_BY_USERNAME_AND_PASSWORD="SELECT * FROM user WHERE username=? and password=? and role=? and status=? ";
	private static final String UPDATE="UPDATE user SET firstname=?, lastname=?, username=?, password=?, mail=?, role=?, status=?, otp_token=? WHERE user_id=?";
	private static final String DELETE="DELETE FROM user WHERE user_id=?";
	private static final String UPDATE_PASSWORD="UPDATE user SET password=? WHERE user_id=?";
	
	public static ArrayList<User> getAll(){
		Connection conn=null;
		ArrayList<User> result=new ArrayList<>();
		Object[] values= {};
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, SELECT_ALL, false, values);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				User u=new User(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("mail"), Role.values()[rs.getInt("role")], UserStatus.values()[rs.getInt("status")], rs.getString("otp_token"), rs.getBoolean("is_logged_in"));
				result.add(u);
			}
			rs.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return result;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	public static User getActiveAdminByOtpToken(String otp) {
		Connection conn=null;
		User result=null;
		Object[] values= {otp, Role.ADMIN.ordinal(), UserStatus.ACTIVE.ordinal()};
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, GET_USER_BY_OTP_TOKEN, false, values);
		
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				result=new User(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("mail"), Role.values()[rs.getInt("role")], UserStatus.values()[rs.getInt("status")], rs.getString("otp_token"), rs.getBoolean("is_logged_in"));
			}
			rs.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return result;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	

	public static User getActiveAdminByUsernameAndPassword(String username, String password) {
		Connection conn=null;
		User result=null;
		password=hashPassword(password);
		Object[] values= {username, password, Role.ADMIN.ordinal(), UserStatus.ACTIVE.ordinal()};
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, GET_BY_USERNAME_AND_PASSWORD, false, values);
		
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				result=new User(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("mail"), Role.values()[rs.getInt("role")], UserStatus.values()[rs.getInt("status")], rs.getString("otp_token"), rs.getBoolean("is_logged_in"));
			}
			rs.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return result;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	public static User getById(Integer id) {
		Connection conn=null;
		User result=null;

		Object[] values= {id};
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, GET_BY_ID, false, values);
		
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				result=new User(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("mail"), Role.values()[rs.getInt("role")], UserStatus.values()[rs.getInt("status")], rs.getString("otp_token"), rs.getBoolean("is_logged_in"));
			}
			rs.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return result;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	
	public static boolean insert(User u) {
		Connection conn = null;
		boolean result=false;
		
		u.setPassword(hashPassword(u.getPassword()));		
		
		Object[] values= {u.getFirstname(), u.getLastname(), u.getUsername(), u.getPassword(), u.getMail(), u.getRole().ordinal(), u.getStatus().ordinal()};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, INSERT, true, values);
			ps.executeUpdate();
			
			if(ps.getUpdateCount()>0) {
				result=true;
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                u.setUserId(generatedKeys.getInt(1));
		            }
		        }
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	public static boolean update(User u, boolean changePass) {
		Connection conn = null;
		boolean result=false;	
		
		if(changePass) {
			u.setPassword(hashPassword(u.getPassword()));
		}
		Object[] values= {u.getFirstname(), u.getLastname(), u.getUsername(), u.getPassword(), u.getMail(), u.getRole().ordinal(), u.getStatus().ordinal(),u.getOtpToken(), u.getUserId()};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, UPDATE, false, values);
			ps.executeUpdate();
		
			if(ps.getUpdateCount()>0) {
				result=true;
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	public static boolean updatePassword(Integer userId, String password) {
		Connection conn = null;
		boolean result=false;	
	
		password=hashPassword(password);
		Object[] values= { password, userId};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, UPDATE_PASSWORD, false, values);
			ps.executeUpdate();
		
			if(ps.getUpdateCount()>0) {
				result=true;
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
		
	public static boolean delete(Integer userId) {
		Connection conn = null;
		boolean result=false;		
		Object[] values= {userId};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, DELETE, false, values);
			ps.executeUpdate();
			
			if(ps.getUpdateCount()>0) {
				result=true;
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}
	
	
	private static String hashPassword(String password) {
		byte[] dataBytes = password.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(dataBytes);
			byte[] digest = md.digest();
		
			return byteArrayToHex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private static String byteArrayToHex(byte[] a) {
	   StringBuilder sb = new StringBuilder(a.length * 2);
	   for(byte b: a)
	      sb.append(String.format("%02x", b));
	   return sb.toString();
	}
}
