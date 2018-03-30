package fr.best.client.entity;



import java.util.ArrayList;
import java.util.List;


public class Batiment 
{
	private Long id;

	private String name;

	private  List<Zone> listZone=new ArrayList<>();
	

	private  List<Sortie> listSortie=new ArrayList<>();;

	public Batiment(String name) 
	{
		this.name = name;
	}
	
	public Batiment(Long id,String name) 
	{	
		this.id=id;
		this.name = name;
	}

	public Batiment() 
	{		
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

	public List<Zone> getListZone()
	{
		return this.listZone;
	}
	
	public void setListZone(List<Zone> listZone) 
	{
		this.listZone = listZone;
	}
	
	
	public List<Sortie> getListSortie() {
		return listSortie;
	}

	public void setListSortie(List<Sortie> listSortie) {
		this.listSortie = listSortie;
	}

	@Override
	public String toString() {
		return "Batiment [id=" + id + ", name=" + name + ", bZone=" +this.listZone + "]";
	}	
	
	
}
