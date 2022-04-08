package org.unibl.etf.virtualbank.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.unibl.etf.virtualbank.models.dto.Account;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;
import org.unibl.etf.virtualbank.utils.ConnectionPool;
import org.unibl.etf.virtualbank.utils.DAOUtil;

public class AccountDAO {

	private static final ConnectionPool pool=ConnectionPool.getInstance();
	private static final String GET_BY_CARD_NUMBER_AND_PIN="SELECT * FROM account WHERE credit_card_number=? and pin=?";
	private static final String UPDATE_CAN_PAY="UPDATE account SET can_pay=? WHERE account_id=?";
	
	public static Account getByCardNumberAndPin(String creditCardNumber, String pin) {
		Connection conn=null;
		Account result=null;
		Object[] values= {creditCardNumber, pin};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, GET_BY_CARD_NUMBER_AND_PIN, false, values);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				result=new Account(rs.getInt("account_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("credit_card_number"), CreditCardType.values()[rs.getInt("credit_card_type")], rs.getDouble("amount") , rs.getString("pin"), rs.getDate("expiration_date"), rs.getBoolean("can_pay"));
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
	
	public static boolean updateCanPay(Account a) {
		Connection conn = null;
		boolean result=false;	
		
		Object[] values= {a.getCanPay(), a.getAccountId()};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, UPDATE_CAN_PAY, false, values);
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

}
