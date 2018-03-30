package best.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="zone")
public class Zone 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="z_id")
	private Long id;
	
	@Column(name="nom")
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "zone_point",
	joinColumns = { @JoinColumn(name = "Zone_z_id") },
	inverseJoinColumns = { @JoinColumn(name = "listPoint_p_id") })
	private List<Point> listPoint=new ArrayList<>();
	
	@ManyToMany(mappedBy="listZone")
	private  List<Batiment> listBatiment=new ArrayList<>();

	public Zone(String name)
	{
		this.name = name;
	}
	
	public Zone(Long id,String name)
	{	
		this.id=id;
		this.name = name;
	}
	
	public Zone()
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

	public List<Point> getListPoint()
	{
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint)
	{
		this.listPoint = listPoint;
	}
	
	

	public List<Batiment> getListBatiment() {
		return listBatiment;
	}

	public void setListBatiment(Batiment batiment) {
		this.listBatiment.add(batiment);
		batiment.getListZone().add(this);
	}

	@Override
	public String toString() 
	{
		return "Zone [id=" + id + ", name=" + name + ", listPoint=" + listPoint + "]";
	}
}
