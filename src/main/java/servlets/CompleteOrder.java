package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.ApproDAO;
import DAO.ArticlesDAO;
import DAO.OrdersDAO;
import beans.Appro;
import beans.Articles_stock;
import beans.Orders;


@WebServlet("/completeOrder")
public class CompleteOrder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the value of order id
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the OrdersDAO bean
     	OrdersDAO dao = (OrdersDAO) context.getBean("OrdersDAO");
        Orders order = dao.getById(orderId);
        
        
        if (order != null) {
        	
        	// Update the Status of the order
        	order.setStatus(true);
	        dao.update(order);
        }
        
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
        
	}
	
}
