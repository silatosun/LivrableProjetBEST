package fr.best.client.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Zone
{
	private String type;
	private String directionMarche;
	private String sensDirection;

	private Map<String,Point> limitesZone;
	private String zoneSuivante;
	private Long id;

	private String name;

	private List<Point> listPoint=new ArrayList<>();

	public Zone(String name)
	{
		this.name = name;
	}
	
	public Zone()
	{		
	}

	public Zone(String type, String d, String sens){
		this.type=type;
		this.directionMarche=d;
		this.sensDirection=sens;
		this.zoneSuivante="Zone";
		limitesZone=new HashMap<>();
	}

	public Zone(String type, String d){
		this.type=type;
		this.directionMarche=d;
		this.sensDirection="nonDefini";
		this.zoneSuivante="Zone";
		limitesZone=new HashMap<>();
	}

	public void setZoneSuivante(String fin){
		zoneSuivante=fin;
	}
	public String getZoneSuivante(){
		return zoneSuivante;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public List<Point> getListPoint()
	{
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint)
	{
		this.listPoint = listPoint;
	}

	// Debut Gauche ----------- Debut Droite
	// |                        |
	// |                        |
	// |                        |
	// Fin Gauche ------------- Fin Droite

	// DELIMITER LES ZONES avec les points de la bdd
	public void delimiterZone(Point debutGauche, Point finGauche,Point debutDroite, Point finDroite) {

		System.out.println( "       DELIMITE ZONE : "+this.getType());

		limitesZone.put("debutGauche",debutGauche);
		System.out.println("        Debut Gauche : "+debutGauche.getX()+" "+debutGauche.getY());
		limitesZone.put("finGauche",finGauche);
		System.out.println("        Fin Gauche : "+finGauche.getX()+" "+finGauche.getY());
		limitesZone.put("debutDroite",debutDroite);
		System.out.println("        Debut Doite : "+debutDroite.getX()+" "+debutDroite.getY());
		limitesZone.put("finDroite",finDroite);
		System.out.println("        Fin Droite : "+finDroite.getX()+" "+finDroite.getY());

	}

	public boolean estDanslaZone(Point p){

		if(p.getX()>=limitesZone.get("debutGauche").getX() &&
				p.getX() < limitesZone.get("debutDroite").getX() &&
				p.getY() >= limitesZone.get("debutGauche").getY() &&
				p.getY() < limitesZone.get("finGauche").getY() ){
			return true;
		}
		else return false;
	}


	public String getDirectionMarche() {
		return directionMarche;
	}

	public String getSensDirection(){
		return sensDirection;
	}


	public String getType() {
		return type;
	}

	@Override
	public String toString() 
	{
		return "Zone [id=" + id + ", name=" + name + ", listPoint=" + listPoint + "]";
	}
}
