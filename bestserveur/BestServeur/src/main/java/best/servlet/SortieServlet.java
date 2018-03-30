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
import best.entity.Sortie;
import best.entity.UserAdmin;
import best.repository.BatimentRepository;
import best.repository.SortieRepository;

public class SortieServlet  extends HttpServlet
{
	private String url="/WEB-INF/View/sortie.jsp";
	private Map<String,String> message=new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{	
		HttpSession session = req.getSession();
		UserAdmin  user=(UserAdmin)session.getAttribute("USER");
		if(user==null) {
			resp.sendRedirect("/BestServeur/connexion");
		}else {
		
			this.getListofBuiding(req);
			this.getListofSortie(req);
			this.getServletContext().getRequestDispatcher(url).forward( req,resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String nom=req.getParameter("nom");
		Long idBatiment=Long.valueOf(req.getParameter("batiment"));
		
		if(nom!=null  && idBatiment!=null) {
			SortieRepository repoSortie=new SortieRepository();	
			repoSortie.add(nom,idBatiment);
			message.put("add","Une nouvelle sortie vient d'être ajoutée");
			message.remove("detete");
			req.setAttribute("message",message);
		}else {
			message.remove("detete");
			message.remove("add");
			req.setAttribute("message",message);
		}
		
		this.getListofBuiding(req);
		this.getListofSortie(req);
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
	
	private void getListofSortie(HttpServletRequest req) 
	{
		SortieRepository sortie=new SortieRepository();
		List<Sortie> list=sortie.getAllsortie();
		if(!list.isEmpty()) {
			  req.setAttribute("Listsortie",list);
		}
	}

}
