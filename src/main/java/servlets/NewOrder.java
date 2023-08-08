package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import freemarker.core.ParseException;


@WebServlet("/newOrder")
public class NewOrder extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the ApproDAO bean & Appro bean
     	ApproDAO dao = (ApproDAO) context.getBean("ApproDAO");
        Appro appro = (Appro) context.getBean("Appro");
        
        // Set date format
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        
		try {
			// Set the value of DatePrevueLivraison
			date = dateFormat.parse(request.getParameter("PredictedDeliveryDate"));
			appro.setDatePrevueLivraison(date);
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		// Retrieve the ArticlesDAO bean
     	ArticlesDAO articleDao = (ArticlesDAO) context.getBean("ArticlesDAO");        
		Articles_stock article = articleDao.getById(Integer.parseInt(request.getParameter("productId")));
        
        appro.setId_appro(Integer.parseInt(request.getParameter("productId")));
        appro.setQteCommande(Integer.parseInt(request.getParameter("quantity")));
        appro.setArticle(article);
        appro.setStatus(false);
        
        dao.save(appro);
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
		
		
	
	}
	
	
	
	
}
