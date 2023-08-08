import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.UsersDAO;
import beans.UserBean;

public class Test {

	public static void main(String[] args) {

		// Initialize the application context
     	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
     	
     	// Retrieve the ArticlesDAO bean & the Articles_stock bean
     	UsersDAO dao = (UsersDAO) context.getBean("UsersDAO") ;
     	UserBean user1 = (UserBean) context.getBean("User");

		user1.setLogin("abdeljebar");
		user1.setPass("azerty");
		user1.setRole(true);
		
		dao.save(user1);
	}
}
