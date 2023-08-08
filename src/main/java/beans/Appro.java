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
@Entity(name = "Articles_approvisionnement")
public class Appro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_appro;

    @ManyToOne
    @JoinColumn(name = "code_article")
    private Articles_stock article;

    @Column
    private int qteCommande;

    @Column
    private Date datePrevueLivraison;

    @Column
    private boolean status;


    public int getId_appro() {
        return id_appro;
    }

    public void setId_appro(int id_appro) {
        this.id_appro = id_appro;
    }

    public Articles_stock getArticle() {
        return article;
    }

    public void setArticle(Articles_stock article) {
        this.article = article;
    }

    public int getQteCommande() {
        return qteCommande;
    }

    public void setQteCommande(int qteCommande) {
        this.qteCommande = qteCommande;
    }

    public Date getDatePrevueLivraison() {
        return datePrevueLivraison;
    }

    public void setDatePrevueLivraison(Date datePrevueLivraison) {
        this.datePrevueLivraison = datePrevueLivraison;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
