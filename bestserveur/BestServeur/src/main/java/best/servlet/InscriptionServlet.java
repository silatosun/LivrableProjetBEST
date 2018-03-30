package best.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import best.entity.UserAdmin;
import best.repository.UseradminRepository;

public class InscriptionServlet extends HttpServlet
{
	private String url="/WEB-INF/View/inscription.jsp";
	private Map<String,String> message=new HashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(url).forward( req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String confirmpassword=req.getParameter("confirmpassword");
		
		
		if(email!=null && password!=null && confirmpassword!=null) {
			if(password.equals(confirmpassword)) {
				UserAdmin user=new UserAdmin();
				user.setEmail(email);
				user.setPassword(password);
				checkUniqueEmail(req,resp,user);
			}else {
				message.put("password","Votre mot de passe n'est pas conforme ");
				message.remove("email");
				message.remove("add");
				req.setAttribute("message",message);
				System.out.println("pass word not conform");
				this.getServletContext().getRequestDispatcher(url).forward( req,resp);
				
			}
			
			System.out.println("Inscription: "+email+" "+password);
			
		}
		
		
	}
	
	private void checkUniqueEmail(HttpServletRequest req,HttpServletResponse resp,UserAdmin user) throws IOException, ServletException
	{
		UseradminRepository admin=new UseradminRepository();
		UserAdmin userR=admin.find(user);
		if(userR!=null) {
			System.out.println("TEST EMAIL USE");
			message.put("email","Cette adresse email est déjà utilisée");
			message.remove("password");
			message.remove("add");
			req.setAttribute("message",message);
			this.getServletContext().getRequestDispatcher(url).forward( req,resp);
		}else {
			System.out.println("TEST INSERT"+user);
			admin.add(user);
			message.put("add","Votre inscription a été prise en compte! ");
			message.remove("password");
			message.remove("email");
			req.setAttribute("message",message);
			resp.sendRedirect("/BestServeur/connexion");
		}
	}
	

}
