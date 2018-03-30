package best.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import best.entity.Batiment;
import best.entity.UserAdmin;
import best.entity.Zone;
import best.repository.BatimentRepository;
import best.repository.ZoneRepository;

public class ZoneServlet extends HttpServlet
{
	private String url="/WEB-INF/View/zone.jsp";
	private Map<String,String> message=new HashMap<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{	HttpSession session = req.getSession();
		UserAdmin  user=(UserAdmin)session.getAttribute("USER");
		if(user==null) {
			resp.sendRedirect("/BestServeur/connexion");
		}else {
			this.getListofZone(req);
			this.getListofBuiding(req); 
			this.getServletContext().getRequestDispatcher(url).forward( req,resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{	
		String nom=req.getParameter("nom");
		Long idBatiment=Long.valueOf(req.getParameter("batiment"));
		
		if(nom!=null  && idBatiment!=null) {
			ZoneRepository repoZone=new ZoneRepository();	
			repoZone.add(nom,idBatiment);
			message.put("add","Une nouvelle zone vient d'être ajoutée");
			message.remove("detete");
			req.setAttribute("message",message);
		}else {
			message.remove("detete");
			message.remove("add");
			req.setAttribute("message",message);
		}
		
		this.getListofZone(req);
		this.getListofBuiding(req); 
		this.getServletContext().getRequestDispatcher(url).forward( req,resp);
	}
	
	private void getListofBuiding(HttpServletRequest req) 
	{
		BatimentRepository bat=new BatimentRepository();
		List<Batiment> list=bat.getAllbatiment();
		if(!list.isEmpty()) {
			  req.setAttribute("Listbat",list);
		}
	}
	
	private void getListofZone(HttpServletRequest req) 
	{
		ZoneRepository zone=new ZoneRepository();
		List<Zone> list=zone.getAllzone();
		if(!list.isEmpty()) {
			  req.setAttribute("Listzone",list);
		}
	}

}
