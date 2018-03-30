package best.Test.Hibernate;

import best.repository.*;
import best.entity.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import best.entity.Point;
import best.entity.Signal;
import best.entity.User;

public class Test {

	public static void main(String[] args) {
		/*SortieRepository srepo=new SortieRepository ();
		ZoneRepository zrepo=new ZoneRepository();
		System.out.println(srepo.getAllsortierest(Long.valueOf(1)));*/
		
		/*UserRepository repoUser=new UserRepository();	
		User user=new User();
		
		user.setName("fatima");
		user.setNumber("0664177500");
		repoUser.add(user);*/
		
		//User u=repoUser.find("0664177500");
		/*BatimentRepository repoB=new BatimentRepository();	
		repoB.add("HomeTest");*/
		
		/*SessionFactory factory = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(User.class)
		.buildSessionFactory();
		Session session=factory.getCurrentSession();*/
		/*session.beginTransaction();
		
		Signal s=new Signal();
		Signal s1=new Signal();
		s.setMac("00-14-22-01-23-45");
		s.setRssi("-96");
		s.setSsid("alaha");
		
		s1.setMac("00-14-22-01-23-45");
		s1.setRssi("-96");
		s1.setSsid("alaha");
		
		Point p=new Point();
		p.setX(12.0);
		p.setY(13.0);	
		
		p.getListSignal().add(s);
		p.getListSignal().add(s1);
		
		Point p1=new Point();
		p1.setX(12.0);
		p1.setY(13.0);	
		
		p1.getListSignal().add(s);
		p1.getListSignal().add(s1);
	
		
		
		
		Zone b=session.get(Zone.class,Long.valueOf("3"));
		b.getListPoint().add(p);
		b.getListPoint().add(p1);
		session.save(b);
		session.getTransaction().commit();
		/*BatimentRepository bat=new BatimentRepository();
		System.out.println(bat.getAllbatiment());*/
		
		/*
		Zone z1=new Zone();
		z1.setName("Home");
		z1.getListPoint().add(p);
		z1.getListPoint().add(p1);
		Batiment b=new Batiment();
		b.setName("Allo");
		b.getListZone().add(z1);
		Sortie so=new Sortie();
		so.setName("sortie");
		so.setCoordonne(p);*/
		/*try {
			
			session.beginTransaction();
			
					
			//session.save(b);
			
			
			session.getTransaction().commit();
		
		
		}finally {
		factory.close();
		}*/


	}

}
