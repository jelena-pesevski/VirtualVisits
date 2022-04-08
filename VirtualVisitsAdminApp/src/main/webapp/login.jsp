<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="userBean" class="org.unibl.etf.virtualvisits.models.beans.UserBean" scope="session"/>
<jsp:useBean id="logBean" class="org.unibl.etf.virtualvisits.models.beans.LogBean" scope="session"/>
<%
	if (request.getParameter("submit") != null) {
		if (userBean.loginUsernamePassword(request.getParameter("username"), request.getParameter("password"))) {
			session.setAttribute("notification", "");
			response.sendRedirect("users.jsp");
		} else {
			session.setAttribute("notification", "Incorrect username or password");
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.light_green-yellow.min.css" />
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
 <link rel="stylesheet" href="style/style.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Admin App</title>
</head>
<body>
 <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
       <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">Virtual visits user management</span>
      </div>
   </header>
 </div>
 
 <div class="content">
 <div class="card-container mdl-card mdl-shadow--2dp">
      <div class="mdl-card__title mdl-card--expand">
          <h2 class="mdl-card__title-text">Log in</h2>
      </div>
      <div class="mdl-card__supporting-text">
          <form method="post" action="login.jsp">
              <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="username" id="username" required="required">
                  <label class="mdl-textfield__label" for="username">Username</label>
              </div>
              <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="password" name="password" id="password" required="required" autocomplete="off">
                  <label class="mdl-textfield__label" for="password">Password</label>
              </div>

              <div class="mdl-card__actions">
                  <input class="mdl-button mdl-js-button mdl-button--primary" type="submit" name="submit" value="Log in" />
              </div>
          </form>
           <div class="mdl-card--border">
                 <p><%=session.getAttribute("notification")%></p>
           </div>
      </div>
  </div>
 </div>
</body>
</html>