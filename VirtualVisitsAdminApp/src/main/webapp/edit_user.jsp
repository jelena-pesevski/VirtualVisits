<%@page import="java.time.Instant"%>
<%@page import="org.unibl.etf.virtualvisits.models.dto.Log"%>
<%@page import="org.unibl.etf.virtualvisits.models.enums.UserStatus"%>
<%@page import="org.unibl.etf.virtualvisits.models.enums.Role"%>
<%@page import="org.unibl.etf.virtualvisits.models.dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="org.unibl.etf.virtualvisits.models.beans.UserBean" scope="session"/>
<jsp:useBean id="logBean" class="org.unibl.etf.virtualvisits.models.beans.LogBean" scope="session"/>
<jsp:useBean id="usersManagerBean" class="org.unibl.etf.virtualvisits.models.beans.UsersManagerBean" scope="session" />

<%
	session.setAttribute("updateResult", "");
	Integer userId=null;
	User user=null;
	if(!userBean.isLoggedIn()){
		response.sendRedirect("error.jsp");
	}
	if(request.getParameter("submit")!=null){
		//check if it is redirection from users.jsp	
		if(request.getParameter("editUserId")!=null){
			userId=Integer.parseInt(request.getParameter("editUserId"));
			user=usersManagerBean.getById(userId);
		}else if(request.getParameter("userId")!=null){
			userId=Integer.parseInt(request.getParameter("userId"));
			user=usersManagerBean.getById(userId);
			
			if(user!=null){
				//get update fields
				String firstname=request.getParameter("firstname");
				String lastname=request.getParameter("lastname");
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				String mail=request.getParameter("mail");
				Role role=null;
				UserStatus status=null;
				if(request.getParameter("role")!= null){
					int roleIndex=Integer.parseInt(request.getParameter("role"));
					if(roleIndex< Role.values().length && roleIndex>=0){
						role=Role.values()[roleIndex];
					}
				}
				if(request.getParameter("status")!= null){
					int statusIndex=Integer.parseInt(request.getParameter("status"));
					if(statusIndex< UserStatus.values().length && statusIndex>=0){
						status=UserStatus.values()[statusIndex];
					}
				}
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setUsername(username);
				user.setMail(mail);
				user.setRole(role);
				user.setStatus(status);
				
				boolean passChange=false;
				if(password!=null && password.length()>=15){
					passChange=true;
					user.setPassword(password);
				}
				
				boolean result=usersManagerBean.updateUser(user, passChange);
				if(!result){
					session.setAttribute("updateResult", "User can't be updated");
				}else{
					logBean.insertLog(new Log(0, userBean.getCurrUser().getUsername()+" updated user with username:"+ user.getUsername(), "USER-UPDATE", Instant.now(),userBean.getCurrUser().getUsername()));
					response.sendRedirect("users.jsp");
				}
			}
		}
	}

%>
<!DOCTYPE html>
<html>
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
             <a class="mdl-navigation__link" href="users.jsp">Users</a>
             <a class="mdl-navigation__link" href="logout.jsp">Logout</a>
           </nav>
      </div>
   </header>
<div class="content">
 <div class="card-container mdl-card mdl-shadow--2dp">
      <div class="mdl-card__title mdl-card--expand">
          <h2 class="mdl-card__title-text">Edit user</h2>
      </div>
      <div class="mdl-card__supporting-text">
          <%if(user!=null){%>
			<form method="post" action="edit_user.jsp">
			     <input type="hidden" name="userId" id="userId" value="<%=user.getUserId()%>" />
		         <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	                 <input class="mdl-textfield__input" type="text" name="firstname" id="firstname" required value=<%=user.getFirstname() %>>
	                 <label class="mdl-textfield__label" for="firstname">Firstname</label>
		         </div>  			
			     <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	                 <input class="mdl-textfield__input" type="text" name="lastname" id="lastname" required value=<%=user.getLastname() %>>
	                 <label class="mdl-textfield__label" for="lastname">Lastname</label>
		         </div>
		         <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	                 <input class="mdl-textfield__input" type="text" name="username" id="username" required value=<%=user.getUsername() %>  minlength="12">
	                 <label class="mdl-textfield__label" for="username">Username</label>
		         </div>
		         <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	                 <input class="mdl-textfield__input" type="text" name="password" id="password"  minlength="15">
	                 <label class="mdl-textfield__label" for="password">Password</label>
		         </div>
		         <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	                 <input class="mdl-textfield__input" type="text" name="mail" id="mail" required value=<%=user.getMail() %>>
	                 <label class="mdl-textfield__label" for="mail">Mail</label>
		         </div>    
              	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <select class="mdl-textfield__input" id="role" name="role" required autocomplete="off">
                        <%for(Role r: Role.values()){%>
                        	<option value=<%=r.ordinal() %>  <%if(user.getRole().ordinal()==r.ordinal()){%> selected <%}%>><%=r.toString() %></option>
                       <%} %>
                    </select>
                    <label class="mdl-textfield__label" for="role">Role</label>
                </div>  	
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <select class="mdl-textfield__input" id="status" name="status" required autocomplete="off">
                          <%for(UserStatus s: UserStatus.values()){%>
                          	<option value=<%=s.ordinal() %>  <%if(user.getStatus().ordinal()==s.ordinal()){%> selected <%}%>> <%=s.toString() %></option>
                         <%} %>
                      </select>
                      <label class="mdl-textfield__label" for="status">Status</label>
                </div> 
		         
		        	<button type="submit" name="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
        			 	Save
        			</button>
        		   <% if(session.getAttribute("updateResult")!=null){ %>
				   		<div><%=session.getAttribute("updateResult") %></div>
				   <%} %>
		 	</form>
		 	
	
		<%} %>
      </div>
  </div>
 </div>
</div>
</body>
</html>