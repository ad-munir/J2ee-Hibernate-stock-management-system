<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ page import="beans.UserBean" %>
<%
  // UserBean userbean = (UserBean) session.getAttribute("userBean");
   //if (userbean != null) {
   // 	response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
   //}
%>	

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Login form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/login.css" />

</head>
<body>

	<div class="logo text-center">
	  <img class="logo-img" src="${pageContext.request.contextPath}/stock-system/img/rayban.png" alt="logo">
	</div>
	
	
	
	<f:view>
	    <div class="wrapper">
	        <div class="inner-warpper text-center">
	            <h2 class="title">Login to your account</h2>
	            
	            <h:form id="LoginForm" styleClass="formvalidate">
	                <div class="input-group">
	                    <label class="palceholder" for="userName">User Name</label>
	                    <h:inputText styleClass="form-control" value="#{userBean.login}" id="userName"  />
	                    <h:message styleClass="validate-input" for="userName" id="userNameError" />
	                </div>
	
	                <div class="input-group">
	                    <label class="palceholder" for="userPassword">Password</label>
	                    <h:inputSecret styleClass="form-control" value="#{userBean.pass}" id="userPassword"  />
	                    <h:message styleClass="validate-input" for="userPassword" id="userPasswordError" />
	                </div>
	
	                <h:commandButton action="#{userBean.loginUser}" type="submit" id="login" value="Login" styleClass="login-btn" />
	            </h:form>
	        </div>
	    </div>
	</f:view>
	    
	<script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/stock-system/js/login.js"></script>
	

</body>
</html>