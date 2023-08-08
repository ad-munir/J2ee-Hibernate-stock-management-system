package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Appro;

public class ApproDAO {

 public Appro getById(int id) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  Appro produit = session.get(Appro.class, id);

  transaction.commit();
  session.close();
  return produit;
 }

 public void save(Appro appro) {

  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.save(appro);

  transaction.commit();
  session.close();

 }

 public void delete(Appro appro) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.delete(appro);

  transaction.commit();
  session.close();

 }

 public void update(Appro appro) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.update(appro);
  transaction.commit();
  session.close();

 }

 @SuppressWarnings("unchecked")
  public List<Appro> getAll(){
      Transaction transaction = null;
      List<Appro> appros = null;
      try ( Session session = SessionFact.getSessionfactory().openSession()){

       transaction = session.beginTransaction();
       appros = session.createQuery("from Articles_approvisionnement").list();
       transaction.commit();


      }catch(Exception e) {
       System.out.println(e.getMessage());
        if (transaction != null) {
           transaction.rollback();}

}

      return appros; }

}