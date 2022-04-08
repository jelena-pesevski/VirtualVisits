package org.unibl.etf.virtualbank.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.unibl.etf.virtualbank.models.dto.Transaction;
import org.unibl.etf.virtualbank.utils.ConnectionPool;
import org.unibl.etf.virtualbank.utils.DAOUtil;

public class TransactionDAO {

	private static final ConnectionPool pool=ConnectionPool.getInstance();
	private static final String GET_ALL_BY_ACCOUNT_ID="SELECT * FROM transaction WHERE account_id=?";
	
	public static ArrayList<Transaction> getAllByAccountId(Integer accountId){
		Connection conn=null;
		ArrayList<Transaction> result=new ArrayList<>();
		Object[] values= {accountId};
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, GET_ALL_BY_ACCOUNT_ID, false, values);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Transaction t=new Transaction(rs.getInt("transaction_id"), rs.getTimestamp("date_time"), rs.getDouble("cash_amount"), rs.getInt("account_id"));
				result.add(t);
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
}
