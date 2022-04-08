<%@page import="org.unibl.etf.virtualvisits.models.dto.Log"%>
<%@page import="java.time.Instant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="org.unibl.etf.virtualvisits.models.beans.UserBean" scope="session"/>
<jsp:useBean id="logBean" class="org.unibl.etf.virtualvisits.models.beans.LogBean" scope="session"/>

<%
	String token=request.getParameter("otp");
	
	if(token!=null && userBean.loginWithToken(token)){
		response.sendRedirect("users.jsp");
	}else if(token!=null){
		logBean.insertLog(new Log(0, "Bad login with token:"+ token, "LOGIN ADMIN APP FAIL", Instant.now()));
		response.sendRedirect("error.jsp");
	}else{
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin app</title>
</head>
<body>

</body>
</html>