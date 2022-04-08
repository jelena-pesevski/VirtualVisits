<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.unibl.etf.virtualbank.models.dto.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="accountBean" type="org.unibl.etf.virtualbank.models.beans.AccountBean" scope="session"/>
<jsp:useBean id="transactionBean" type="org.unibl.etf.virtualbank.models.beans.TransactionBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Bank</title>
<link rel="stylesheet" href="styles/style.css"/>
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.blue-yellow.min.css" /> 
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<script src="scripts/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body onload="init(<%=accountBean.getCurrAccount().getCanPay()%>)">
 <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
       <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">Virtual Bank</span>
          <div class="mdl-layout-spacer"></div>
          <nav class="mdl-navigation">
              <a class="mdl-navigation__link" href="?action=logout">Logout</a>
            </nav>
      </div>
   </header>
   
     <div class="mdl-tabs mdl-js-tabs mdl-js-ripple-effect">
            <div class="mdl-tabs__tab-bar">
                <a href="#transactions" class="mdl-tabs__tab is-active">Transactions</a> 
                <a href="#account" class="mdl-tabs__tab">Account details</a>
            </div>
            
            
            
            <div class="mdl-tabs__panel is-active" id="transactions">
             	<table id="tabela">
			         <thead>
			          <tr>
			              <th>Date and time</th>
			              <th>Cash amount </th>
			          </tr>
			          </thead>
			          <tbody id="tbody">
			          <% 
			          	SimpleDateFormat sdf=new SimpleDateFormat("MMM d, yyyy h:mm:ss aa");
			      		ArrayList<Transaction> transactions=transactionBean.getAllByAccountId(accountBean.getCurrAccount().getAccountId());
			      		for(Transaction t: transactions){ %>
			          		<tr>
			          			<td><%=sdf.format(t.getDateTime())%></td>
			          			<td><%=t.getCashAmount()%></td>
							</tr>
						<%} %>
					</tbody>
			     </table>
            </div>
            
            
             <div class="mdl-tabs__panel" id="account">
             	<div class="content">
             		<div class="card-container mdl-card mdl-shadow--2dp">
					      <div class="mdl-card__title mdl-card--expand">
					          <h2 class="mdl-card__title-text">Account details</h2>
					      </div>
					      <div class="mdl-card__supporting-text">
					        <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="can-pay-switch">
			  				<input type="checkbox" id="can-pay-switch" class="mdl-switch__input" onchange="change()">
			 	 			<span class="mdl-switch__label">Online paying</span>
			 				</label>
			 				<br>
			 				<label id="can-pay-lbl">Online paying is <%=accountBean.getCurrAccount().getCanPay()?"enabled":"disabled"%></label>
			 				<br>
			 				<label>Current cash amount:<%=accountBean.getCurrAccount().getAmount()%></label>
						</div>
					  </div>
             	</div>
             </div>
      </div>
 </div>
</body>
</html>