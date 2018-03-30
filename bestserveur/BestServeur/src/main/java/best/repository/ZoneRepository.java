package best.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import best.connexion.bdd.ConnexionBdd;
import best.entity.Batiment;
import best.entity.Point;
import best.entity.Zone;

public class ZoneRepository
{	
	public void add(String name,Long idBatiment)
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();	
		Zone zone=new Zone();
		
		try {	
				zone.setName(name);
				//start transaction 
				session.beginTransaction();	
				Batiment batiment=session.get(Batiment.class,idBatiment);
				batiment.getListZone().add(zone);
				session.update(batiment);				
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
	}
	
	public void update(Zone zone)
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();	
		
		try {	
				
				//start transaction 
				session.beginTransaction();
				Zone z=session.get(Zone.class,zone.getId());
				Point point=zone.getListPoint().get(0);
				z.getListPoint().add(point);
				session.update(z);
								
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
	}
	
	@SuppressWarnings("unchecked")
	public List<Zone> getAllzone()
	{
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();		
		List<Zone> listzone=new ArrayList<>();
		
		try {	
				
				//start transaction 
				session.beginTransaction();	
				
				listzone= session
							  .createQuery("From Zone")
							  .getResultList();					      
				
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
		
		return listzone;
	}
	
	public List<Zone> getAllzonerest(Long idbatiment)
	{  
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();		
		List<Zone> list=new ArrayList<>();
		
		try {	
				session.beginTransaction();	 
				Batiment batiment=session.find(Batiment.class,idbatiment);
		        list=batiment.getListZone(); 
		        session.getTransaction().commit(); 
			}catch(Exception e) {
				System.out.println(e);
				if(session.getTransaction()!=null)
					session.getTransaction().rollback();
			}
			finally {
				session.close();
			}
		
		return list;
	}

}
