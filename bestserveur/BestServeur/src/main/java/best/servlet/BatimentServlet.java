package best.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import best.entity.Batiment;
import best.entity.UserAdmin;
import best.repository.BatimentRepository;

public class BatimentServlet extends HttpServlet
{
	private String url="/WEB-INF/View/batiment.jsp";
	private Map<String,String> message=new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{	
		HttpSession session = req.getSession();
		UserAdmin  user=(UserAdmin)session.getAttribute("USER");
		if(user==null) {
			resp.sendRedirect("/BestServeur/connexion");
		}else {
				String ids=req.getParameter("id");
				String idBatiment=req.getParameter("idBatiment");
				String alerte = req.getParameter("alerte");
				try {
					if(ids!=null  && idBatiment == null && alerte==null) {
						Long id=Long.valueOf(ids);
						BatimentRepository br=new BatimentRepository();
						br.deleteBatiment(id);
						message.put("detete","le bâtiment numéro  "+id+ " vient d'être supprimé");
						message.remove("add");
						req.setAttribute("message",message);
						
					}
					else if(ids==null && idBatiment !=null && alerte !=null){
						if(alerte.compareTo("TRUE")==0) {
							Long id=Long.valueOf(idBatiment);
							BatimentRepository repoBat=new BatimentRepository();
							repoBat.setAlerte(id,1);
							
							message.put("alerte","Une alerte est envoyée.");
							message.remove("add");
							message.remove("detete");
							req.setAttribute("message", message);
						}
						else {
							Long id=Long.valueOf(idBatiment);
							BatimentRepository repoBat=new BatimentRepository();
							repoBat.setAlerte(id,0);
							
							message.put("alerte","L'alerte est enlevée.");
							message.remove("add");
							message.remove("detete");
							req.setAttribute("message", message);
						}
					}	
					else {
							message.remove("detete");
							message.remove("add");
							req.setAttribute("message",message);
							
						}			
				}catch(Exception e) {
					System.out.println(e);			
				}
				
				this.getListofBuiding(req); 
				this.getServletContext().getRequestDispatcher(url).forward( req,resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String nom=req.getParameter("nom");
		
		if(nom!=null) {
			BatimentRepository repoBat=new BatimentRepository();	
			repoBat.add(nom);
			message.put("add","Un nouveau bâtiment vient d'être ajouté");
			message.remove("detete");
			req.setAttribute("message",message);
			
		}
		else {
			message.remove("detete");
			message.remove("add");
			req.setAttribute("message",message);
		}
		
		
		
		this.getListofBuiding(req); 
		this.getServletContext().getRequestDispatcher(url).forward( req,resp);
	}
	
	private void getListofBuiding(HttpServletRequest req) 
	{
		BatimentRepository bat=new BatimentRepository();
		List<Batiment> list=bat.getAllbatiment();
		if(!list.isEmpty()) {
			System.out.println(list);
			  req.setAttribute("Listbat",list);
		}
	}
}