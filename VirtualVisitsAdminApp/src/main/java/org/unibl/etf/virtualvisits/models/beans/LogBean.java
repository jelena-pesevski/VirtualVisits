package org.unibl.etf.virtualvisits.models.beans;

import org.unibl.etf.virtualvisits.models.dao.LogDAO;
import org.unibl.etf.virtualvisits.models.dto.Log;

public class LogBean {

	public void insertLog(Log log) {
		LogDAO.insert(log);
	}
}
