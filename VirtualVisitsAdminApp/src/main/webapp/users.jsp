<%@page import="java.time.Instant"%>
<%@page import="org.unibl.etf.virtualvisits.models.dto.Log"%>
<%@page import="org.unibl.etf.virtualvisits.models.dto.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="org.unibl.etf.virtualvisits.models.beans.UserBean" scope="session"/>
<jsp:useBean id="logBean" class="org.unibl.etf.virtualvisits.models.beans.LogBean" scope="session"/>
<jsp:useBean id="usersManagerBean" class="org.unibl.etf.virtualvisits.models.beans.UsersManagerBean" scope="session" />

<%
	session.setAttribute("deleteResult", "");
	session.setAttribute("resetPasswdResult", "");
	if(!userBean.isLoggedIn()){
		response.sendRedirect("error.jsp");
	}

	//delete
	if(request.getParameter("submit")!=null){
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		if(userId!=null && !userBean.getCurrUser().getUserId().equals(userId)){
			boolean result=usersManagerBean.deleteUser(userId);
			if(!result){
				session.setAttribute("deleteResult", "User can't be deleted");
			}else{
				logBean.insertLog(new Log(0, userBean.getCurrUser().getUsername()+" deleted user with id:"+ userId, "USER-DELETE", Instant.now()));
				session.setAttribute("deleteResult", "User is deleted");
			}
		}else{
			session.setAttribute("deleteResult", "User can't be deleted");
		}
	}else if(request.getParameter("resetPassword")!=null){
		Integer userId=Integer.parseInt(request.getParameter("userIdForReset"));
		if(userId!=null){
			boolean result=usersManagerBean.resetPassword(userId);
			if(!result){
				logBean.insertLog(new Log(0, userBean.getCurrUser().getUsername()+" password reset for user with id:"+ userId, "PASSWORD-RESET", Instant.now()));
				session.setAttribute("resetPasswdResult", "Password couldn't be reset");
			}else{
				session.setAttribute("resetPasswdResult", "New password is set");
			}
		}else{
			session.setAttribute("resetPasswdResult", "Password couldn't be reset");
		}
	}

	ArrayList<User> users=usersManagerBean.getUsers();
	
%>

<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>Admin app</title>
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.light_green-yellow.min.css" />
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="style/style.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
	  <header class="mdl-layout__header">
       <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">Virtual visits user management</span>
          <div class="mdl-layout-spacer"></div>
          <nav class="mdl-navigation">
             <a class="mdl-navigation__link" href="add_user.jsp">Add new user</a>
             <a class="mdl-navigation__link" href="logout.jsp">Logout</a>
           </nav>
      </div>
   </header>
   
   <% if(session.getAttribute("deleteResult")!=null){ %>
   		<div class="notification"><%=session.getAttribute("deleteResult") %></div>
   <%} %>
    <% if(session.getAttribute("resetPasswdResult")!=null){ %>
   		<div class="notification"> <%=session.getAttribute("resetPasswdResult") %></div>
   <%} %>
   <!-- tabela -->
   <table id="tabela">
        <thead>
         <tr>
             <th>Id</th>
             <th>Firstname </th>
             <th>Lastname</th>
             <th>Username </th>
             <th>Mail</th>
             <th>Role</th>
             <th>Status</th>
             <th>Edit</th>
             <th>Delete</th>
             <th>Reset password</th>
         </tr>
         </thead>
         <tbody id="tbody">
        	<% for(User u: users){ %>
        	<tr>
	        	<td><%=u.getUserId()%></td>
	        	<td><%=u.getFirstname()%></td>
	        	<td><%=u.getLastname()%></td>
	        	<td><%=u.getUsername()%></td>
	        	<td><%=u.getMail()%></td>
	        	<td><%=u.getRole() %></td>
	        	<td><%=u.getStatus() %></td>
	        	<td>
	        		<form method="post" action="edit_user.jsp">
	        			<input type="hidden" name="editUserId" id="editUserId" value="<%=u.getUserId()%>" />
	        			
	        			<button type="submit" name="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
	        			 	<i class="material-icons">edit</i>
	        			</button>
	        		</form>
	        	</td>
	        	<td>
	        		<form method="post" action="users.jsp">
	        			<input type="hidden" name="userId" id="userId" value="<%=u.getUserId()%>" />
	        			
	        			<button type="submit" name="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
	        			 	<i class="material-icons">delete</i>
	        			</button>
	        		</form>
	        	</td>
	        		<td>
	        		<form method="post" action="users.jsp">
	        			<input type="hidden" name="userIdForReset" id="userIdForReset" value="<%=u.getUserId()%>" />
	        			
	        			<button type="submit" name="resetPassword" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
	        			 	Reset password
	        			</button>
	        		</form>
	        	</td>
        	</tr>
        <%}	%>
		</tbody>
    </table>
</div>
</body>
</html>