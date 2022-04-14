package org.unibl.etf.virtualbank.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.unibl.etf.virtualbank.models.beans.AccountBean;
import org.unibl.etf.virtualbank.models.beans.TransactionBean;
import org.unibl.etf.virtualbank.models.dao.TransactionDAO;
import org.unibl.etf.virtualbank.models.dto.Transaction;

import com.google.gson.Gson;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/Controller")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String action=request.getParameter("action");
		String address = "/WEB-INF/pages/login.jsp";
		session.setAttribute("notification", "");
		
		AccountBean currAccount=(AccountBean)session.getAttribute("accountBean");
		
		if(action==null || "".equals(action)) {
			address="/WEB-INF/pages/login.jsp";
		}else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		}else if (action.equals("login")) {
			String creditCardNumber = request.getParameter("creditCardNumber");
			String pin = request.getParameter("pin");
			AccountBean accountBean=new AccountBean();
			if (accountBean.login(creditCardNumber, pin)) {
				session.setAttribute("accountBean", accountBean);
				TransactionBean transactionBean=new TransactionBean();
				session.setAttribute("transactionBean", transactionBean);
				address = "/WEB-INF/pages/main-page.jsp";
			} else {
				session.setAttribute("notification", "Incorrect credit card number or pin");
			}
		}else if(action.equals("change") && currAccount!=null && currAccount.isLoggedIn()) {
			String jsonBody = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(
		            Collectors.joining("\n"));
			if(jsonBody!=null && jsonBody.trim().length()!=0) {
				JSONObject obj=new JSONObject(jsonBody);
				
				if(obj.has("canPay")){
					boolean canPay=obj.getBoolean("canPay");
					AccountBean accountBean=(AccountBean)session.getAttribute("accountBean");
					boolean result=accountBean.updateCanPay(canPay);
					if(result) {
						response.setStatus(200);
						return;
					}
				}
				
			}
			response.setStatus(404);
			return;
		}else if(action.equals("transactions")) {
			if(currAccount==null || !currAccount.isLoggedIn()) {
				response.setStatus(404);
				return;
			}
			
			ArrayList<Transaction> transactions=TransactionDAO.getAllByAccountId(currAccount.getCurrAccount().getAccountId());
			Gson gson=new Gson();
			String json=gson.toJson(transactions);
			
			response.setContentType("application/json");
			PrintWriter out=response.getWriter();
			out.print(json);
			out.flush();
			out.close();
			
			return;
		}
		else {
			address = "/WEB-INF/pages/error.jsp";
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
