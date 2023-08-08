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


@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the value articles_stock id
		int id = Integer.parseInt(request.getParameter("productId"));

        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the ArticlesDAO bean
     	ArticlesDAO dao = (ArticlesDAO) context.getBean("ArticlesDAO");
        Articles_stock product = dao.getById(id);
        
        if (product != null) {
        	
        	// Delete article
            dao.delete(product);
        }
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
		
	}

	
}
