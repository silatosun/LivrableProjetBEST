package best.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import best.connexion.bdd.ConnexionBdd;
import best.entity.Batiment;

public class BatimentRepository
{	
	public Batiment find(Long id) 
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();	
		Batiment batiment=null;
		
		try {
				//start transaction 
				session.beginTransaction();	
				
				batiment =(Batiment) session
						 .createQuery("FROM Batiment WHERE b_id="+id)
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
		
		return batiment;
	}
	
	public void setAlerte(Long id, int valeur) {
		
		Batiment bAlerte = this.find(id);

		bAlerte.setAlerte(valeur);
		
		// AJOUT BAT AVEC ALERTE = Valeur
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
		
		try {	
				//start transaction 
				session.beginTransaction();	

				session.update(bAlerte);
				
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
	
	public void add(String name)
	{	
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
		Batiment batiment=new Batiment();
		
		try {	
				batiment.setName(name);
				//start transaction 
				session.beginTransaction();	
				
				session.save(batiment);
				
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
	public List<Batiment> getAllbatiment()
	{
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();	
		List<Batiment> listbatiment=new ArrayList<>();
		
		try {	
				
				//start transaction 
				session.beginTransaction();	
		     
		        listbatiment = session
		        				.createQuery("From Batiment")
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
		
		return listbatiment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllbatimentrest()
	{
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();		
		List<Object[]> list=new ArrayList<>();
		
		try {	
				
				//start transaction 
				session.beginTransaction();				    
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
		         CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		         Root<Batiment> root = query.from(Batiment.class);
		         query.multiselect(root.get("id"),root.get("name"));
		       
		         list=session.createQuery(query).getResultList();
		        				
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
		
		return list;
	}
	
	public void deleteBatiment(Long id)
	{
		SessionFactory factory=ConnexionBdd.getSessionfactory();
		Session session=factory.getCurrentSession();
		
		try {
				//start transaction 
				session.beginTransaction();					
				
				Batiment b=session.get(Batiment.class,id);
				session.delete(b);
				
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
}