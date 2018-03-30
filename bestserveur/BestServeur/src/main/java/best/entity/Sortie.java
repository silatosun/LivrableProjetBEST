package best.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sortie")
public class Sortie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="so_id")
	private Long id;
	
	@Column(name="nom")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Point coordonne;
	
	public Sortie(String name, Point coordonne)
	{
		this.name = name;
		this.coordonne = coordonne;
	}
	
	public Sortie(Long id,String name)
	{
		this.name = name;
		this.id = id;
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
		return "Sortie [id=" + id + ", name=" + name + ", coordonne=" + coordonne + "]";
	}
	
	
}
