package core.hibernate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
  private static final Logger logger = Logger.getLogger("org.hibernate");

  static {
    logger.setLevel(Level.WARNING);
  }
  
  public static void main(String[] args) {
	  HibernateUtil.getSessionWithTransaction().getTransaction().commit();
	  
	  try {
	  }
	  catch (Exception e) {
		  e.printStackTrace();
	  }
	  finally {
		  HibernateUtil.closeSessionFactory();
	  }
  }

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration = new Configuration().configure();

      // apply configuration property settings to StandardServiceRegistryBuilder
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties()).build();

      return configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      logger.severe("Initial SessionFactory creation failed: " + ex);
      throw ex;
    }
  }
  
  public static void closeSessionFactory() {
    sessionFactory.close();
  }
  

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static Session getSessionWithTransaction() {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session;
  }

}
