<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="DAO.*" %>
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.YearMonth" %>
<%@ page import="java.time.ZoneId" %>

<%
    UserBean userbean = (UserBean) session.getAttribute("userBean");
    if (userbean.getLogin() == null) {
    	response.sendRedirect(request.getContextPath());
    }
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/stock-system/styles/home.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>Home</title>
</head>
<body>

	<div class="sidebar">
        <div class="brand">
            <p><img src="${pageContext.request.contextPath}/stock-system/img/rayban1.png" width="60px" alt=""><span><img src="${pageContext.request.contextPath}/stock-system/img/rayban2.png" width="60px" alt=""></span></p>
        </div>
        <ul class="nav-list">
            <li class="nav-item" id="home">
                <a href="#">
                    <span class="nav-item__icon">
                        <i class="fa fa-home" aria-hidden="true"></i>
                    </span>
                    <span class="nav-item__text">
                        Home
                    </span>
                </a>
            </li>

            <li class="nav-item" id="products">
                <a href="#">
                    <span class=""><img src="${pageContext.request.contextPath}/stock-system/img/glasses.png" width="35px" alt=""></span>
                    <span class="nav-item__text">
                        Products
                    </span>
                </a>
            </li>

            <li class="nav-item" id="orders">
                <a href="#">
                    <span class="nav-item__icon">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                    </span>
                    <span class="nav-item__text">
                        Orders
                    </span>
                </a>
            </li>
            
            
            <li class="nav-item" id="supply-chain">
                <a href="#">
                    <span class=""><img src="${pageContext.request.contextPath}/stock-system/img/transit.gif" width="35px" alt=""></span>
                    <span class="nav-item__text">
                        Supply Chain
                    </span>
                </a>
            </li>
            
            <li class="nav-item" id="users">
                <a href="#">
                    <span class="nav-item__icon">
                        <i class="fa fa-user-circle" aria-hidden="true"></i>
                    </span>
                    <span class="nav-item__text">
                        Users
                    </span>
                </a>
            </li>
            
        </ul>
        
        <f:view>
	        <ul class="nav-list">
	            <li class="nav-item" id="admin">
	                <a href="#">
	                    <span class="">
	                    	<img src="${pageContext.request.contextPath}/stock-system/img/admin.png" width="35px" alt="">
	                    </span>
	                   	<span class="nav-item__text admin-name">
		                    	<h:outputText value="#{userBean.login}"/>
		                </span>
	                </a>
	            </li>
	            
	            <li class="nav-item" id="logout">
	                
		            <span class="nav-item__icon logout">
		                <i class="fa fa-sign-out" aria-hidden="true"></i>
		            </span>
		            
		            <span class="nav-item__text">
							<h:form>
								<h:commandButton action="#{userBean.logout}" type="submit" value="Log out" styleClass="logout-btn"/>
							</h:form>
		            </span>
	                
	            </li>
	            
	        </ul>
        </f:view>
    </div>
    
	<div class="content">
			
		<% 
			// Initialize the application context
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			// Retrieve the ordersDAO bean and fetch the list of orders
			OrdersDAO ordersDAO = (OrdersDAO) context.getBean("OrdersDAO") ;
			List<Orders> orders = ordersDAO.getAll(); 
			
			
			// Retrieve the ArticlesDAO bean and fetch the list of articles
			ArticlesDAO articlesDAO = (ArticlesDAO) context.getBean("ArticlesDAO") ;
			List<Articles_stock> articles = articlesDAO.getAll(); 
			
			// Retrieve the ArticlesDAO bean and fetch the list of articles
			UsersDAO UsersDAO = (UsersDAO) context.getBean("UsersDAO") ;
			List<UserBean> users = UsersDAO.getAll(); 
			
			// Retrieve the ArticlesDAO bean and fetch the list of articles
			ApproDAO approDAO = (ApproDAO) context.getBean("ApproDAO") ;
			List<Appro> appros = approDAO.getAll(); 
			
			// Get the current date and month
			LocalDate currentDate = LocalDate.now();
			YearMonth currentMonth = YearMonth.from(currentDate);
			
			
			double totalSum = 0.0;
			// Calculate the total sum of all orders
			for (Orders order : orders) {
			    totalSum += order.getTotal();
			}
			 
			   
			int commingProducts = 0;
			int sumQty = 0;
			
			int commingToday = 0;
			int sumQtyToday = 0;
			
			
			// Iterate over the collection of Appro objects
			for (Appro appro : appros) {
			 
				if( !appro.isStatus()){
					commingProducts ++;
					sumQty += appro.getQteCommande();
				} 
			 
			 
				LocalDate today = LocalDate.now();  // Get the current date
				
				// Convert the Date object to LocalDate
				Date dateFromDB = appro.getDatePrevueLivraison();
				LocalDate localDateFromDB = dateFromDB.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				// Compare the date from the database with the current date
				if (localDateFromDB.equals(today) && !appro.isStatus()) {
				commingToday++;  // Increment the counter if the dates match
				sumQtyToday += appro.getQteCommande();
				}
			}
			
			
			int newOrders = 0;
			// Iterate over the collection of Appro objects
			for (Orders order : orders) {
			 
				if( !order.isStatus()){
					newOrders ++;
				} 
			}
		%>  
	
		<div class="grid-container">
			
			<div class="stat-card">
		        <div class="left">
		            <span class="number"><%=totalSum %> MAD</span>
		            <h3 class="title">All Earnings</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <span class="material-symbols-outlined">signal_cellular_alt</span>
		            </span>
		        </div>
		        <div class="footer"></div>
		    </div>
	    
	    
		    <div class="stat-card">
		        <div class="left">
		            <span class="number" id="nb-orders"><%=newOrders %></span>
		            <h3 class="title">New Orders</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <i class="fa fa-file-text-o" aria-hidden="true"></i>
		            </span>
		        </div>
		        <div class="footer" id="second"></div>
		    </div>
		    
		    

		    <div class="stat-card">
		        <div class="left">
		            <span class="number" id="nb-appros"><%=sumQty %>/<%=commingProducts %></span>
		            <h3 class="title">Coming Products</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <span class="material-symbols-outlined" style="color: #197657;">local_shipping</span>
		            </span>
		        </div>
		        <div class="footer" id="third"></div>
		    </div>
		    
		    <div class="stat-card">
		    	<div class="left">
		            <span class="number" id="nb-coming-today"><%=sumQtyToday %>/<%=commingToday %></span>
		            <h3 class="title">Coming Today</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <span class="material-symbols-outlined" style="color: #fbd80f">today</span>
		            </span>
		        </div>
		        <div class="footer" id="fourth"></div>
		    </div>
			
			<div class="stat-card">
		        <div class="left">
		            <span class="number" id="nb-users"><%=users.size() %></span>
		            <h3 class="title">Users</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <span class="material-symbols-outlined">person</span>
		            </span>
		        </div>
		        <div class="footer" id="fifth"></div>
		    </div>
			
		    <div class="stat-card">
		        <div class="left">
		            <span class="number" id="nb-products"><%=articles.size() %></span>
		            <h3 class="title">Products</h3>
		        </div>
		        <div class="right">
		            <span class="icon">
		                <span class="material-symbols-outlined" style="color: #ff6600">eyeglasses</span>
		            </span>
		        </div>
		        <div class="footer" id="sixth"></div>
		    </div>
			
		</div>
	</div>																
		   

    <script src="${pageContext.request.contextPath}/stock-system/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/stock-system/js/home.js"></script>
    
    
</body>
</html>