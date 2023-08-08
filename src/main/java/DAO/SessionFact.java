package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import beans.Appro;
import beans.Articles_stock;
import beans.Orders;
import beans.UserBean;

public class SessionFact {

	private static final ServiceRegistry serviceRegistry;
	private static final SessionFactory sessionFactory;

	static {
	Configuration config = new Configuration();
	config.configure();

	config.addAnnotatedClass(Articles_stock.class);
	config.addAnnotatedClass(Orders.class);
	config.addAnnotatedClass(UserBean.class);
	config.addAnnotatedClass(Appro.class);


	serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	sessionFactory = config.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
