<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
  
<%@ page import="DAO.ArticlesDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.Articles_stock" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>


<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/products.css" />
 	<title>Products</title>
</head>
<body>

<% 
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ArticlesDAO articlesDAO = (ArticlesDAO) context.getBean("ArticlesDAO") ;
	List<Articles_stock> articles = articlesDAO.getAll(); 
%>

	<div class="header">
	  
		<h1>All Products <span>(<%=articles.size() %>)</span></h1>
		 
		<div class="header-actions">
		
			<button id="pdf-btn" type="button" onclick="downloadSummary()">Stock Summary PDF</button>
			
			<button id="add-btn" type="button" onclick="openAddModal()">Add product</button>
	
			 <!-- Add Product Modal -->
			 <div id="modal" class="modal-overlay" style="display: none;">
			 	<div class="modal-content">
			     
			     	<h1>Add Product</h1>
			     
			     	<div class="form-item">
			     		<label for="name">Product Name:</label>
			       		<input type="text" id="name" name="name" >
			     	</div>
			       
			
					<div class="form-item">
			     		<label for="name">Product Description:</label>
			       		<input type="text" id="description" name="description">
			     	</div>
				
					<div class="form-item">
				        <label for="price">Price:</label>
				        <input type="number" id="price" name="price" >
					</div>
					
					<div class="form-item">
			     		<label for="quantity">Quantity:</label>
			     	  	<input type="number" id="quantity" min="1" max="" name="quantity" value="1">
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
	        <th>Name</th>
	        <th>Description</th>
	        <th>Price</th>
	        <th>Quantity</th>
	        <th>Actions</th>
	      </tr>
	    </thead>
	    
    	<tbody>
		    <% 
			   for (Articles_stock article : articles) { 
			%>
			   <tr>
			      <td><%= article.getCode_article() %></td>
			      <td><%= article.getName() %></td>
			      <td><%= article.getDescription() %></td>
			      <td><%= article.getPrice() %> MAD</td>
			      <td><%= article.getQuantity() %></td>
			      <td class="actions">
					  <button id="edit-btn" type="button" onclick="openEditModal('<%= article.getCode_article() %>','<%= article.getName() %>','<%= article.getDescription() %>','<%= article.getPrice() %>','<%= article.getQuantity() %>')">Edit</button>
					  <button id="delete-btn" type="button" onclick="deleteProduct('<%= article.getCode_article() %>')">Delete</button>
		     	  </td>
			   </tr>
			<% 
			   } 
			%>
		</tbody>
	</table>
  
    <!-- Edit Product Modal -->
	<div id="modalEdit" class="modal-overlay" style="display: none;">
	    <div class="modal-content">
	    
	    	<h1>Edit Product</h1>
	      
	      
	      	<input type="hidden" id="productId" >
	      	
	      	<div class="form-item">
	      		<label for="name">Product Name:</label>
	        	<input type="text" id="editname" name="name" >
	      	</div>
	        
	
			<div class="form-item">
	      		<label for="name">Product Description:</label>
	        	<input type="text" id="editdescription" name="description">
	      	</div>
			
			<div class="form-item">
		        <label for="price">Price:</label>
		        <input type="number" id="editprice" name="price" >
			</div>
			<div class="form-item">
	      		<label for="quantity">Quantity:</label>
	        	<input type="number" min="1" id="editquantity" name="quantity" value="1">
	      	</div>
	        
				
	        <div class="modal-footer">
	        	<button type="submit" class="save" onclick="EditProduct()">Save</button>
	        	<button type="button" id="cancel" onclick="closeEditModal()">Cancel</button>
	        </div>
	      
	    </div>
	</div>

	
	
	<script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/stock-system/js/products.js"></script>
	
	<script type="text/javascript">

		function EditProduct() {
			var productId = $("#productId").val();
			var name = $("#editname").val();
			var desc = $("#editdescription").val();
			var price = $("#editprice").val();
			var quantity = $("#editquantity").val();
			var url = "${pageContext.request.contextPath}/EditProduct?productId=" + productId + "&name=" + name
				+ "&description=" + desc + "&price=" + price + "&quantity="
				+ quantity;
			window.location.href = url;
		}
	
		function addProduct() {
			var name = $("#name").val();
			var desc = $("#description").val();
			var price = $("#price").val();
			var quantity = $("#quantity").val();
			var url = "${pageContext.request.contextPath}/AddProduct?name=" + name + "&description=" + desc
				+ "&price=" + price + "&quantity=" + quantity;
			window.location.href = url;
		}
	
		function deleteProduct(id) {
			var url = "${pageContext.request.contextPath}/DeleteProduct?productId=" + id;
			window.location.href = url;
		}
	
		function downloadSummary() {
			var url = "${pageContext.request.contextPath}/generatePDF";
			window.location.href = url;
		}
		
	</script>

</body>
</html>
    