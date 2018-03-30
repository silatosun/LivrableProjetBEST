package best.server.BestServeur;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import best.entity.Batiment;
import best.entity.Zone;
import best.repository.BatimentRepository;
import best.repository.UserRepository;
import best.repository.ZoneRepository;

@Path("zonerest")
public class ZoneRest 
{
	@POST
	@Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Zone addZone(Zone zone) 
	{
		ZoneRepository repoZone=new ZoneRepository();		
		repoZone.update(zone);
		
		return zone;
    }

	
	@GET
	@Path("findzonebybatiment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Zone> findZone(@PathParam( "id" )Long id) 
	{
		ZoneRepository zrepo=new ZoneRepository();
		List<Zone> list=new ArrayList<>();
		list=zrepo.getAllzonerest(id);
		
		List<Zone> listr=new ArrayList<>();
		
		for(Zone z:list) {
			listr.add(new Zone(z.getId(),z.getName()));
		}
		
		return listr;
	}

}
