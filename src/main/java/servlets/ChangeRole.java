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
import DAO.UsersDAO;
import beans.Appro;
import beans.Articles_stock;
import beans.UserBean;


@WebServlet("/changeRole")
public class ChangeRole extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the values of user id
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        
        // Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        // Retrieve the ApproDAO bean
     	UsersDAO dao = (UsersDAO) context.getBean("UsersDAO");
        UserBean user = dao.getById(userId);
        
     	
        if (user != null) {
        	
        	// Update the role of user
        	user.setRole( !user.isRole() );
	        dao.update(user);
        }
        
		
        response.sendRedirect(request.getContextPath()+"/faces/stock-system/home.jsp");
	}
	
}
