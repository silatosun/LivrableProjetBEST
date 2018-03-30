package best.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import best.entity.UserAdmin;
import best.repository.UseradminRepository;

public class ConnexionServlet extends HttpServlet
{
	//private String url="/WEB-INF/index.jsp";
	private String url="/WEB-INF/View/connexion.jsp";
	private String key="SSIO_2018_M2";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(url).forward( req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{	
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		if(email!=null && password!=null) {
			UseradminRepository admin=new UseradminRepository();
			UserAdmin user=new UserAdmin();
			user.setEmail(email);
			user.setPassword(password);
			UserAdmin userR=admin.find(user);
			HttpSession session = req.getSession();
			if(userR!=null) {
				
				 session.setAttribute("USER",userR);			     
			    			      
				 resp.sendRedirect("/BestServeur/batiment");
			}else {
				this.getServletContext().getRequestDispatcher(url).forward( req,resp);
				 session.setAttribute("USER",null);
			}
			
			System.out.println("Connexion: "+userR);
			
		}
				
		
	}

}
