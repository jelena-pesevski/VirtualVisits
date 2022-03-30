package org.unibl.etf.virtual_bank.models.dto;

import java.sql.Date;

import org.unibl.etf.virtual_bank.models.enums.CreditCardType;

public class Account {

	private Integer accountId;
	private String firstname;
	private String lastname;
	private String creditCardNumber;
	private CreditCardType creditCardType;
	private Double amount;
	private String pin;
	private Date expirationDate;
	private Boolean canPay;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Account(Integer accountId, String firstname, String lastname, String creditCardNumber,
			CreditCardType creditCardType, Double amount, String pin, Date expirationDate, Boolean canPay) {
		super();
		this.accountId = accountId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.creditCardNumber = creditCardNumber;
		this.creditCardType = creditCardType;
		this.amount = amount;
		this.pin = pin;
		this.expirationDate = expirationDate;
		this.canPay = canPay;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public CreditCardType getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Boolean getCanPay() {
		return canPay;
	}
	public void setCanPay(Boolean canPay) {
		this.canPay = canPay;
	}
	
	
}
