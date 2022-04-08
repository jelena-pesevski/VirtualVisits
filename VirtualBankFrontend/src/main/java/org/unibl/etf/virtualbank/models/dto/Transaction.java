package org.unibl.etf.virtualbank.models.dto;

import java.sql.Timestamp;

public class Transaction {
	
	private Integer transactionId;
	private Timestamp dateTime;
	private Double cashAmount;
	private Integer accountId;
	
	public Transaction(Integer transactionId, Timestamp dateTime, Double cashAmount, Integer accountId) {
		super();
		this.transactionId = transactionId;
		this.dateTime = dateTime;
		this.cashAmount = cashAmount;
		this.accountId = accountId;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	

}
