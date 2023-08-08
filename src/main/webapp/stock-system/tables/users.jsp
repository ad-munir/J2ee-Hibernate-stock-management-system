<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ page import="DAO.UsersDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.UserBean" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%
    UserBean userbean = (UserBean) session.getAttribute("userBean");
    
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/users.css" />
	<title>Orders</title>
</head>
<body>

	<% 
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UsersDAO dao = (UsersDAO) context.getBean("UsersDAO") ;
	   	List<UserBean> users = dao.getAll(); 
	%>
<f:view>		
	<div class="header">
		<h1>All Users <span>(<%=users.size() %>)</span></h1>
		
		<div class="header-actions">
		
			<% 
			if( userbean.getLogin().equals("mounir") ){
			%>
				<button id="new-order-btn" type="button" onclick="openNewAdminPage()">New Admin ?</button>
			<% 
			} 
			%>

		</div>
		
	</div>
</f:view>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Login</th>
				<th>Role</th>
				<%
				if (userbean.getLogin().equals("mounir")) {
			    	%>
			    	<th>Change Role</th>
			    	<%
			    }
				%>
				
			</tr>
		</thead>
		<tbody>

			<%
			for (UserBean user : users) {
			%>
			<tr>
				<td><%= user.getId() %></td>
				<td><%= user.getLogin() %></td>
				<td><%= user.getLogin().equals("mounir") ? "<b style='color:red'>Owner</b>" : user.isRole() ? "Admin" : "User" %></td>
				
				<% 
					if( userbean.getLogin().equals("mounir") ){
						
						if( user.getLogin().equals("mounir") ) { %>
							<td><span></span></td>
						<%
						}else { 
							if( user.isRole() ) { %>
								<td>
									<button type="submit" id="delete-btn" class="change-role" onclick="changeRole('<%= user.getId() %>')">Delete Admin</button>
								</td>
							<%
							}else { 
							%> 
								<td>						
									<button type="submit" id="set-admin-btn" class="change-role" onclick="changeRole('<%= user.getId() %>')">Set As Admin</button>
								</td>
							<%
							}
						}
					}
				%>
			</tr>
			<% 
			   } 
			%>

		</tbody>
	</table>

	<script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
	
	<script>
		function changeRole( userId ) {
			window.location.href = "${pageContext.request.contextPath}/changeRole?userId=" + userId ;
		}
		
		function openNewAdminPage() {
			window.location.href = "${pageContext.request.contextPath}/faces/stock-system/add-admin.jsp";
		}
		
	</script>
</body>
</html>