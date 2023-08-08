<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ page import="beans.UserBean" %>
<%
    UserBean userbean = (UserBean) session.getAttribute("userBean");
    if (userbean.getLogin() == null || !userbean.getLogin().equals("mounir")) {
    	response.sendRedirect(request.getContextPath());
    }
%>
			
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Add New Admin form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/login.css" />

</head>
<body>

	<button class="go-back-btn" type="button" onclick="goBack()">Home Page</button>

	<f:view>
	<div class="wrapper">
	        <div class="inner-warpper text-center">
	            <h2 class="title">Add New Admin</h2>
	            
	            <h:form id="AddUserForm" styleClass="formvalidate">
	                <div class="input-group">
	                    <label class="palceholder" for="loginInput">Login</label>
	                    <h:inputText styleClass="form-control" value="#{userBean.login}" id="loginInput"  />
	                    <h:message styleClass="validate-input" for="loginInput" id="loginInputError" />
	                </div>
	
	                <div class="input-group">
	                    <label class="palceholder" for="passInput">Password</label>
	                    <h:inputSecret styleClass="form-control" value="#{userBean.pass}" id="passInput"  />
	                    <h:message styleClass="validate-input" for="passInput" id="passInputError" />
	                </div>
	                
	                <div class="role-input">
	                    <label for="role">Role</label>
	                    <input type="text" id="role" value="Admin" disabled="disabled" />
	                </div>
	
	                <h:commandButton action="#{userBean.addUser}" type="submit" id="NewAdmin" value="New Admin" styleClass="login-btn" />
	            </h:form>
	        </div>
	    </div>
	</f:view>
	
	<script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/stock-system/js/login.js"></script>
		
	<script>

		function goBack() {
			window.location.href = "${pageContext.request.contextPath}/faces/stock-system/home.jsp";
		}
		
	</script>
</body>
</html>