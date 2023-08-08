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


@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the value of the product id 
        int id = Integer.parseInt(request.getParameter("productId"));
        
        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the ArticlesDAO bean
     	ArticlesDAO dao = (ArticlesDAO) context.getBean("ArticlesDAO");
        Articles_stock article = dao.getById(id);
        
        if (article != null) {
            article.setName(request.getParameter("name"));
	        article.setDescription(request.getParameter("description"));
	        article.setPrice(Double.parseDouble(request.getParameter("price")));
	        article.setQuantity(Integer.parseInt(request.getParameter("quantity")));
	        dao.update(article);
        }
        
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
		
		System.out.println(request.getParameter("productId"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("quantity"));
		
	}
	
}
