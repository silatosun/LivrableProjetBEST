package best.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import best.connexion.bdd.ConnexionBdd;
import best.entity.User;;

public class UserRepository
{	
	public User add(User user) 
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
		try {
			
			//start transaction 
			session.beginTransaction();
			
			//save user			
			session.save(user);
			
			//commit transaction
			session.getTransaction().commit();
			
			}catch(Exception e) {
				System.out.println(e);
				if(session.getTransaction()!=null)
					session.getTransaction().rollback();		
		
			}finally {
				session.close();
			}
		
		return user;
	}
	
	public User find(String number) 
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
		User user=null;
		
		try {
			//start transaction 
			session.beginTransaction();
			user=(User) session
					 .createQuery("FROM User WHERE number="+number)
					 .getSingleResult();

			//commit transaction
			session.getTransaction().commit(); 
			}catch(Exception e) {
				System.out.println(e);
				if(session.getTransaction()!=null)
					session.getTransaction().rollback();
				
			}
			finally {
				session.close();
			}
		
		return user;
	}

}
