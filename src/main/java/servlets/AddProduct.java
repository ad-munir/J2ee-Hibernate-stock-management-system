package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.ArticlesDAO;
import beans.Articles_stock;


@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
     // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
     	
     	// Retrieve the ArticlesDAO bean & the Articles_stock bean
		ArticlesDAO dao = (ArticlesDAO) context.getBean("ArticlesDAO") ;
        Articles_stock article = (Articles_stock) context.getBean("Articles_stock");
        
        article.setName(request.getParameter("name"));
        article.setDescription(request.getParameter("description"));
        article.setPrice(Double.parseDouble(request.getParameter("price")));
        article.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        dao.save(article);
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
		
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("quantity"));
		
	}
	
	
}
