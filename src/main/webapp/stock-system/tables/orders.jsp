<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
  
<%@ page import="DAO.OrdersDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.Orders" %>

<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/orders.css" />
<title>Orders</title>
</head>
<body>

	<%
	   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	   OrdersDAO ordersDAO = (OrdersDAO) context.getBean("OrdersDAO") ;
	   List<Orders> orders = ordersDAO.getAll(); 
	%>
	
	<div class="header">
		<h1>All Orders <span>(<%=orders.size() %>)</span></h1>
	</div>

	<table>
		<thead>
			<tr>
				<th>Order ID</th>
				<th>Product</th>
				<th>Client</th>
				<th>Quantity</th>
				<th>Total Amount</th>
				<th>Date - Time</th>
 
			</tr>
		</thead>
		<tbody>
			<%
			for (Orders order : orders) {
			%>
			<tr>
				<td><%= order.getId_cmd()%></td>
				<td><%= order.getArticle().getName()%></td>
				<td><%= order.getUser().getLogin()%></td>
				<td><%= order.getQuantity()%></td>
				<td><%= order.getTotal()%> MAD</td>
				<td><%= order.getDate()%></td>
				
			</tr>
			<% 
			   } 
			%>
		</tbody>
	</table>

<script type="text/javascript">
	function completeOrder(orderId) {
		window.location.href = "${pageContext.request.contextPath}/completeOrder?orderId=" + orderId;
	}
</script>
</body>
</html>