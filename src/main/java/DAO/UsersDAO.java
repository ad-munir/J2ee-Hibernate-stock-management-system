package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.UserBean;

public class UsersDAO {

 public UserBean getById(int id) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  UserBean user = session.get(UserBean.class, id);

  transaction.commit();
  session.close();
  return user;
 }

 public void save(UserBean user) {

  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.save(user);

  transaction.commit();
  session.close();

 }

 public void delete(UserBean user) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.delete(user);

  transaction.commit();
  session.close();

 }

 public void update(UserBean user) {
  Session session = SessionFact.getSessionfactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  session.update(user);
  transaction.commit();
  session.close();

 }

 @SuppressWarnings("unchecked")
  public List<UserBean> getAll(){
      Transaction transaction = null;
      List<UserBean> users = null;
      try ( Session session = SessionFact.getSessionfactory().openSession()){

       transaction = session.beginTransaction();
       users = session.createQuery("from Users").list();
       transaction.commit();


      }catch(Exception e) {
       System.out.println(e.getMessage());
        if (transaction != null) {
           transaction.rollback();}

      }

      return users;
  }

}