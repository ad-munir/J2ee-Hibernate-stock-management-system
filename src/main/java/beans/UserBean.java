package beans;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.ArticlesDAO;
import DAO.UsersDAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Entity(name = "Users")
public class UserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String login;
	
	@Column
	private String pass;
	
	@Column
	private boolean role;
	
	@OneToMany(mappedBy = "user")
    private List<Orders> orders;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	// Method for login
	public String loginUser() { 
		System.out.println("aaaa");
		UsersDAO dao = new UsersDAO(); 
		List<UserBean> users = dao.getAll(); 
		
		// Variable for checking if password is correct or not
		int incorrectPassword = 0;
		
		// Case of empty inputs
		if( getLogin().equals("") || getPass().equals("") ) {
			System.out.println("jjjj");
			
			if( getLogin().equals("") ) {
				FacesContext.getCurrentInstance().addMessage("LoginForm:userName", new FacesMessage("Empty Username"));
			}
			
			if( getPass().equals("") ) {
		        FacesContext.getCurrentInstance().addMessage("LoginForm:userPassword", new FacesMessage("Empty Password"));
			}
			
			return "failure";
		}
		
		else {
			System.out.println("mmmm");
			for(UserBean user : users) {
				
				// Case of valid credentials
				if(user.getLogin().equals(getLogin()) && user.getPass().equals(getPass()) && user.isRole()) 
					return "success"; 
				
				// Check if password is incorrect
				if(user.getLogin().equals(getLogin()) && !user.getPass().equals(getPass()) ) {
					incorrectPassword = 1;
				}
			}
			
			// Handle error messages for incorrect password & if user is not found
			if( incorrectPassword == 1 ) {
				FacesContext.getCurrentInstance().addMessage("LoginForm:userPassword", new FacesMessage("Incorrect Password"));
			}else {
				FacesContext.getCurrentInstance().addMessage("LoginForm:userName", new FacesMessage("User Not Found"));
			}
			
			
	        return "failure";
		}
			
	}
	
	
	// Method for logout
	public String logout() {

     // Invalidate the session
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        
		//HttpSession session = ServletActionContext.getRequest().getSession(false);
        //session.invalidate();

        return "logout";
    }

	public String addUser() {
		System.out.println("jjj jshj");
		
		// Case of empty inputs
		if( getLogin().equals("") || getPass().equals("") ) {
			
			if( getLogin().equals("") ) {
				FacesContext.getCurrentInstance().addMessage("AddUserForm:loginInput", new FacesMessage("Empty Username"));
			}
			
			if( getPass().equals("") ) {
		        FacesContext.getCurrentInstance().addMessage("AddUserForm:passInput", new FacesMessage("Empty Password"));
			}
			
			setLogin("mounir");
			
			return "AddUserFailure";
		}
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UsersDAO dao = (UsersDAO) context.getBean("UsersDAO");
		UserBean user = (UserBean) context.getBean("User");
		
		user.setLogin( getLogin() );
		user.setPass( getPass() );
		user.setRole( true );
		dao.save(user);
		
		setLogin("mounir");
		
		return "AddUserSuccess";
	}
	
	public String deleteUser() {
		
		
			// Initialize the application context
		 	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		        
		    // Retrieve the UsersDAO bean
		 	UsersDAO dao = (UsersDAO) context.getBean("UsersDAO");
		    UserBean user = dao.getById(getId());
		    
		    if (user != null) {
		    	
		    	// Delete user
		        dao.delete(user);
		    }
						
			return "deleteUser";
	}
				
}
