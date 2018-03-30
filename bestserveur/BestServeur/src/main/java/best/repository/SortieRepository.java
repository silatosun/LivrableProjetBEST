package best.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import best.connexion.bdd.ConnexionBdd;
import best.entity.Batiment;
import best.entity.Point;
import best.entity.Sortie;
import best.entity.Zone;

public class SortieRepository
{	
	public void add(String name,Long idBatiment)
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();		
		Sortie sortie=new Sortie();
		
		try {	
				sortie.setName(name);
				//start transaction 
				session.beginTransaction();	
				Batiment batiment=session.get(Batiment.class,idBatiment);
				batiment.getListSortie().add(sortie);
				session.update(batiment);				
				//commit transaction
				session.getTransaction().commit(); 
			}catch(Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
	}
	
	public void update(Sortie sortie)
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
				
		try {	
				
				//start transaction 
				session.beginTransaction();	
				Sortie s=session.get(Sortie.class,sortie.getId());
				Point p=sortie.getCoordonne();
				s.setCoordonne(p);
				session.update(s);				
				//commit transaction
				session.getTransaction().commit(); 
			}catch(Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Sortie> getAllsortie()
	{
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();	
		List<Sortie> listsortie=new ArrayList<>();
		
		try {	
				
				//start transaction 
				session.beginTransaction();	
				
				listsortie= session
							  .createQuery("From Sortie")
							  .getResultList();					      
				
				//commit transaction
				session.getTransaction().commit(); 
			}catch(Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
		
		return listsortie;
	}
	
	public List<Sortie> getAllsortierest(Long idbatiment)
	{  
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();		
		List<Sortie> list=new ArrayList<>();
		
		try {	
				session.beginTransaction();	 
				Batiment batiment=session.find(Batiment.class,idbatiment);
		        list=batiment.getListSortie(); 
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
