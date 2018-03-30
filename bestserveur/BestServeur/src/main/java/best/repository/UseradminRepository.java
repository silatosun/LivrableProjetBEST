package best.repository;



import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import best.connexion.bdd.ConnexionBdd;
import best.entity.Batiment;
import best.entity.UserAdmin;

public class UseradminRepository 
{	
	public UserAdmin add(UserAdmin user) 
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
	
	@SuppressWarnings("unchecked")
	public UserAdmin find(UserAdmin user) 
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
	   UserAdmin userad=null;
		try {
			
			//start transaction 
			session.beginTransaction();
			
			 Query query= session
					 .createQuery("FROM UserAdmin  WHERE email=:email")					
					 .setParameter("email",user.getEmail());					 
			 userad=(UserAdmin)query.getSingleResult();
			
			//commit transaction
			session.getTransaction().commit(); 
			
			}catch(Exception e) {
				System.out.println(e);
				if(session.getTransaction()!=null)
					session.getTransaction().rollback();
				
			}finally {
				session.close();
			}
		
		return userad;
	}

}
