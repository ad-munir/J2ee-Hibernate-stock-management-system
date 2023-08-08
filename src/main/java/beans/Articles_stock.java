package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@ManagedBean
@Entity
public class Articles_stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code_article;
    
    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private double price;
    
    @Column
    private int quantity;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Appro> comingProducts;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Orders> orders;
    
    
    
    // Getters and setters
    // ...

    public int getCode_article() {
        return code_article;
    }

    public void setCode_article(int code_article) {
        this.code_article = code_article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Appro> getComingProducts() {
        return comingProducts;
    }

    public void setComingProducts(List<Appro> comingProducts) {
        this.comingProducts = comingProducts;
    }

	
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	
	
	
	public String addArticle() { 
        if(!getName().isEmpty()) {
        	System.out.println(getName());
        	return "success"; 
        }
            
        FacesContext facescontext = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("erreur") ;
        facescontext.addMessage("LoginForm", msg);
        
        return "failure";
	}

}
