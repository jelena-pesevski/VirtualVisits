package org.unibl.etf.virtualvisits.models.dto;

import java.sql.Timestamp;
import java.time.Instant;

public class Log {

	private Integer logId;
	private String info;
	private String action;
	private Instant dateTime;
	private String username;
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(Integer logId, String info, String action, Instant dateTime, String username) {
		super();
		this.logId = logId;
		this.info = info;
		this.action = action;
		this.dateTime = dateTime;
		this.username=username;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Instant getDateTime() {
		return dateTime;
	}
	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
