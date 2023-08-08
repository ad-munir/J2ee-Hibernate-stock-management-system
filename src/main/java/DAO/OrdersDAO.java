package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Orders;

public class OrdersDAO {

 public Orders getById(int id) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  Orders produit = session.get(Orders.class, id);

  transaction.commit();
  session.close();
  return produit;
 }

 public void save(Orders order) {

  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.save(order);

  transaction.commit();
  session.close();

 }

 public void delete(Orders order) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.delete(order);

  transaction.commit();
  session.close();

 }

 public void update(Orders order) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.update(order);
  transaction.commit();
  session.close();

 }

 @SuppressWarnings("unchecked")
  public List<Orders> getAll(){
      Transaction transaction = null;
      List<Orders> orders = null;
      try ( Session session = SessionFact.getSessionfactory().openSession()){

       transaction = session.beginTransaction();
       orders = session.createQuery( "from Orders").list();
       transaction.commit();

      }catch(Exception e) {
       System.out.println(e.getMessage());
        if (transaction != null) {
           transaction.rollback();}

}

      return orders; }

}