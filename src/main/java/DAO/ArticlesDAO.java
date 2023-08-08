package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Articles_stock;

public class ArticlesDAO {

 public Articles_stock getById(int id) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  Articles_stock produit = session.get(Articles_stock.class, id);

  transaction.commit();
  session.close();
  return produit;
 }

 public void save(Articles_stock article) {

  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.save(article);

  transaction.commit();
  session.close();

 }

 public void delete(Articles_stock article) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.delete(article);

  transaction.commit();
  session.close();

 }

 public void update(Articles_stock article) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.update(article);
  transaction.commit();
  session.close();

 }

 @SuppressWarnings("unchecked")
  public List<Articles_stock> getAll(){
      Transaction transaction = null;
      List<Articles_stock> articles = null;
      try ( Session session = SessionFact.getSessionfactory().openSession()){

       transaction = session.beginTransaction();
       articles = session.createQuery("from Articles_stock").list();
       transaction.commit();

       
      }catch(Exception e) {
       System.out.println(e.getMessage());
        if (transaction != null) {
           transaction.rollback();}

}

      return articles; }

}