package fr.best.client.entity;




public class Sortie {

	private Long id;

	private String name;

	private Point coordonne;
	
	public Sortie(String name, Point coordonne)
	{
		this.name = name;
		this.coordonne = coordonne;
	}
	
	public Sortie()
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

	public Point getCoordonne()
	{
		return coordonne;
	}

	public void setCoordonne(Point coordonne)
	{
		this.coordonne = coordonne;
	}

	@Override
	public String toString() {
		return "Sortie{" +
				"id=" + id +
				", name='" + name + '\'' +
				", coordonne=" + coordonne +
				'}';
	}
}
