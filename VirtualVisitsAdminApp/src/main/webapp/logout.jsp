<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="org.unibl.etf.virtualvisits.models.beans.UserBean" scope="session"/>
<jsp:useBean id="logBean" class="org.unibl.etf.virtualvisits.models.beans.LogBean" scope="session"/>

<%
	userBean.logout();
	session.invalidate();
	response.sendRedirect("login.jsp");
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