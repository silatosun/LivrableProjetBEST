package best.connexion.bdd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import best.entity.User;

public class ConnexionBdd 
{
	private static final SessionFactory factory;

	//private static ServiceRegistry serviceReg;

	static {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
													  .configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			factory = metaData.getSessionFactoryBuilder().build();
		} catch (Throwable e) {
			System.err.println("Session Factory Exception" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionfactory()
	{
		return factory;
	}
}
