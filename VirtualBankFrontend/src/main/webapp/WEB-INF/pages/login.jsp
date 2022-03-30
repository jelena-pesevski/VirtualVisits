<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/style.css"/>
<script src="scripts/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.blue-yellow.min.css" /> 
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<meta charset="ISO-8859-1">
<title>Virtual Bank</title>
</head>
<body>
 <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
       <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">Virtual Bank</span>
      </div>
   </header>
 </div>
 <div class="content">
 <div class="card-container mdl-card mdl-shadow--2dp">
      <div class="mdl-card__title mdl-card--expand">
          <h2 class="mdl-card__title-text">Log in</h2>
      </div>
      <div class="mdl-card__supporting-text">
          <form method="post" action="?action=login">
              <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="creditCardNumber" id="creditCardNumber" required="required">
                  <label class="mdl-textfield__label" for="creditCardNumber">Credit card number:</label>
              </div>
              <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="password" name="pin" id="pin" required="required" autocomplete="off">
                  <label class="mdl-textfield__label" for="pin">Pin:</label>
              </div>

              <div class="mdl-card__actions">
                  <button class="mdl-button mdl-js-button mdl-button--primary" type="submit">
                      Log in
                    </button>
              </div>
          </form>
           <div class="mdl-card--border">
                 <p><%=session.getAttribute("notification")==null?"":session.getAttribute("notification")%></p>
           </div>
      </div>
  </div>
 </div>
</body>
</html>