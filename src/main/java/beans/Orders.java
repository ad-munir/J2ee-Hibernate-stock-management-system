package beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ManagedBean
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cmd;

	@Column
	private Date date;
	
	@Column
	private int quantity;
	
	@Column
	private double total;
	
	@Column
    private boolean status;

	@ManyToOne(optional = true)
    @JoinColumn(name = "id_user", nullable = true)
    private UserBean user;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "id_article", nullable = true)
    private Articles_stock article;
	
	
	

	public int getId_cmd() {
		return id_cmd;
	}

	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Articles_stock getArticle() {
		return article;
	}

	public void setArticle(Articles_stock article) {
		this.article = article;
	}

	
	
}	