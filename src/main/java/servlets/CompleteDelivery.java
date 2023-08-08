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

import DAO.ApproDAO;
import DAO.ArticlesDAO;
import beans.Appro;
import beans.Articles_stock;


@WebServlet("/completeDelivery")
public class CompleteDelivery extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the values of approvisionnement id & articles_stock id & Quantity
        int approId = Integer.parseInt(request.getParameter("approId"));
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int qty = Integer.parseInt(request.getParameter("quantity"));
        
        
        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the ApproDAO bean
     	ApproDAO dao = (ApproDAO) context.getBean("ApproDAO");
        Appro appro = dao.getById(approId);
        
        
        // Retrieve the ArticlesDAO bean
     	ArticlesDAO articlesDAO = (ArticlesDAO) context.getBean("ArticlesDAO");
     	Articles_stock article = articlesDAO.getById(articleId);
     	
        if (appro != null) {
        	
        	// Update the Status of approvisionnement
	        appro.setStatus(true);
	        dao.update(appro);
	        
	        // Update the new Quantity of article
	        article.setQuantity(article.getQuantity() + qty);
	        articlesDAO.update(article);
        }
        
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
	}
	
}
