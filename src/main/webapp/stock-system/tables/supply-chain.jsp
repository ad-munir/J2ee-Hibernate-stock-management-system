<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="DAO.ApproDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.Appro" %>

<%@ page import="DAO.ArticlesDAO" %>
<%@ page import="beans.Articles_stock" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/supply-chain.css" />
	<title>Orders</title>
</head>
<body>


<% 
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ApproDAO approDAO = (ApproDAO) context.getBean("ApproDAO") ;
    List<Appro> appros = approDAO.getAll(); 
   
   
    ArticlesDAO articlesDAO = (ArticlesDAO) context.getBean("ArticlesDAO") ;
    List<Articles_stock> articles = articlesDAO.getAll(); 
%>

	<div class="header">
		
		<h1>Coming Products <span>(<%=appros.size()%>)</span></h1>

		<div class="header-actions">
			<button id="new-order-btn" type="button" onclick="openAddModal()">New Order</button>

			<!-- Add Product Modal -->
			<div id="modal" class="modal-overlay" style="display: none;">
				<div class="modal-content">
					<h1>Add Product</h1>

					
					<div class="select-dropdown">
						<select>
							<%
							for (Articles_stock article : articles) {
							%>
								<option value="<%=article.getCode_article()%>"><%=article.getName()%></option>
							<%
							}
							%>
						</select>
					</div>

					<div class="form-item">
						<label for="quantity">Quantity:</label> <input type="number" min="1"
							id="quantity" name="quantity" value="1">
					</div>

					<div class="form-item">
						<label for="PredictedDeliveryDate">Predicted Delivery
							Date:</label> <input type="date" id="PredictedDeliveryDate" 
							name="PredictedDeliveryDate">
					</div>


					<div class="modal-footer">
						<button type="submit" class="save" onclick="addProduct()">Save</button>
						<button type="button" id="cancel" onclick="closeAddModal()">Cancel</button>
					</div>

				</div>
			</div>
		</div>
	</div>


	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Predicted Delivery Date</th>
				<th>Status</th>
				<th>Change Status</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Appro appro : appros) {
				SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
				String strDate = formatter.format(appro.getDatePrevueLivraison());
			%>
			<tr>
				<td><%= appro.getId_appro() %></td>
				<td><%= appro.getArticle().getName() %> (<%= appro.getArticle().getCode_article() %>)</td>
				<td><%= appro.getQteCommande() %></td>
				<td><%= strDate %></td>
				<td><%= appro.isStatus()? "Delivered": "Pending" %></td>
				<td>
					<% if(!appro.isStatus()) { %>
					<button type="submit" class="save"
						onclick="completeDelivery('<%= appro.getId_appro() %>', '<%= appro.getArticle().getCode_article() %>','<%= appro.getQteCommande() %>')">Complete</button>
					<%
					}else {  %> <i class="fa fa-check" id="done" aria-hidden="true"></i>
					<%
					}
				%>
				</td>
			</tr>
			<% 
		   } 
		%>
		</tbody>
	</table>

	<script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/stock-system/js/supply-chain.js"></script>
	
	<script>
		function completeDelivery(approId, articleId, qty) {
			window.location.href = "${pageContext.request.contextPath}/completeDelivery?approId=" + approId + "&articleId=" + articleId + "&quantity=" + qty;
		}
		
		function addProduct() {
			var quantity = $("#quantity").val();
			var productId = $(".select-dropdown select").val();
			var predictedDeliveryDate = $("#PredictedDeliveryDate").val();
		
			var url = "${pageContext.request.contextPath}/newOrder?quantity=" + quantity + "&productId=" + productId + "&PredictedDeliveryDate=" + predictedDeliveryDate;
			window.location.href = url;
		}
	</script>

</body>
</html>