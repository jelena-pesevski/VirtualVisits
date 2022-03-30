package org.unibl.etf.virtual_bank.models.beans;

import java.io.Serializable;

import org.unibl.etf.virtual_bank.models.dao.AccountDAO;
import org.unibl.etf.virtual_bank.models.dto.Account;

public class AccountBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Account currAccount=new Account();
	private boolean isLoggedIn=false;
	
	public boolean login(String creditCardNumber, String pin) {
		currAccount=AccountDAO.getByCardNumberAndPin(creditCardNumber, pin);
		if(currAccount!=null) {
			isLoggedIn=true;
			return true;
		}
		return false;
	}
	
	public boolean updateCanPay(boolean canPay) {
		currAccount.setCanPay(canPay);
		return AccountDAO.updateCanPay(currAccount);
	}
	

	public Account getCurrAccount() {
		return currAccount;
	}

	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
