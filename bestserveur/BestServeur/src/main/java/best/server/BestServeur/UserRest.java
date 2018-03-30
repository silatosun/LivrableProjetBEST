package best.server.BestServeur;

import best.entity.User;
import best.repository.UserRepository;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("userrest")
public class UserRest
{
	@POST
	@Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) 
	{
		UserRepository repoUser=new UserRepository();		
		repoUser.add(user);		
		
        return user;
    }
	
	@GET
	@Path("find/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public User findUser(@PathParam( "number" )String number) 
	{
		UserRepository repoUser=new UserRepository();	
		User user=repoUser.find(number);
		
		return user;
	}

}
