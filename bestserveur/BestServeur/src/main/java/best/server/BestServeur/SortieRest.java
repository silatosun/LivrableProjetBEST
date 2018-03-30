package best.server.BestServeur;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import best.entity.Sortie;
import best.entity.Zone;
import best.repository.SortieRepository;
import best.repository.ZoneRepository;

@Path("sortierest")
public class SortieRest
{
	@GET
	@Path("findsortiebybatiment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sortie> findSortie(@PathParam( "id" )Long id) 
	{
		SortieRepository srepo=new SortieRepository ();	
		List<Sortie> list=new ArrayList<>();
		list=srepo.getAllsortierest(id);
		
		
		return list;
	}
	
	@POST
	@Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Sortie addCoordonne(Sortie sortie) 
	{
		SortieRepository srepo=new SortieRepository ();		
		srepo.update(sortie);
		
		return sortie;
    }

}
