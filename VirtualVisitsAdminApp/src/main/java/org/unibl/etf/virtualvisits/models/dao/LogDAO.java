package org.unibl.etf.virtualvisits.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.unibl.etf.virtualvisits.models.dto.Log;
import org.unibl.etf.virtualvisits.utils.ConnectionPool;
import org.unibl.etf.virtualvisits.utils.DAOUtil;

public class LogDAO {

	private static ConnectionPool pool=ConnectionPool.getInstance();
	private static final String INSERT="INSERT INTO log (info, action, date_time) VALUES (?, ?, ?)";
	
	
	public static boolean insert(Log log) {
		boolean result=false;
		Connection conn=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=formatter.format(Date.from(log.getDateTime()));
		
		 LocalDateTime ldt = LocalDateTime.ofInstant(log.getDateTime(),ZoneId.systemDefault());
		 Timestamp timestamp = Timestamp.valueOf(ldt);
		    
		
		Object[] values= {log.getInfo(), log.getAction(), timestamp};
		
		try {
			conn=pool.checkOut();
			PreparedStatement ps=DAOUtil.prepareStatement(conn, INSERT, true, values);
			ps.executeUpdate();
			
			if(ps.getUpdateCount()>0) {
				result=true;
				try(ResultSet generatedKeys=ps.getGeneratedKeys()){
					if(generatedKeys.next()) {
						log.setLogId(generatedKeys.getInt(1));
					}
				}
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.checkIn(conn);
		}
		return result;
	}

	
}
