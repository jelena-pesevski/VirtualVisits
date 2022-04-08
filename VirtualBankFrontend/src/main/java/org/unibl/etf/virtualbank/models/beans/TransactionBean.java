package org.unibl.etf.virtualbank.models.beans;

import java.io.Serializable;
import java.util.ArrayList;

import org.unibl.etf.virtualbank.models.dao.TransactionDAO;
import org.unibl.etf.virtualbank.models.dto.Transaction;

public class TransactionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Transaction> getAllByAccountId(Integer accountId){
		return TransactionDAO.getAllByAccountId(accountId);
	}
}
