package best.server.BestServeur;

import best.entity.Batiment;
import best.repository.BatimentRepository;
import best.repository.ZoneRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("batimentrest")
public class BatimentRest {
	
	@GET
	@Path("find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Batiment findBatiment(@PathParam( "id" )Long id) 
	{
		BatimentRepository repoBat=new BatimentRepository();	
		Batiment b=repoBat.find(id);
		
		return b;
	}
	
	@GET
	@Path("findallbatiment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Batiment> findAll() 
	{	
		List<Batiment> list=new ArrayList<>();
		BatimentRepository repoBat=new BatimentRepository();	
		List<Object[]> blist=repoBat.getAllbatimentrest();
		
		   for (Object[] objects : blist) {
			   Long id=(Long) objects[0];
			   String s1=(String) objects[1];
	            list.add(new Batiment(id,s1));
	         }
		System.out.println(list);
		return list;
	}
	
	@POST
	@Path("add/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addBatiment(@PathParam( "nom" )String nom) 
	{
		BatimentRepository repoBat=new BatimentRepository();	
		repoBat.add(nom);				
    }

}
