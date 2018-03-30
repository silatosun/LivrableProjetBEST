package best.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;


@Entity
@Table(name="batiment")
public class Batiment 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="b_id")
	private Long id;
	
	@Column(name="nom")
	private String name;
	
	@Column(name="alerte")
	private int alerte;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "batiment_zone",
	joinColumns = { @JoinColumn(name = "Batiment_b_id") },
	inverseJoinColumns = { @JoinColumn(name = "listZone_z_id") })
	private  List<Zone> listZone=new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private  List<Sortie> listSortie=new ArrayList<>();;

	public Batiment(String name) 
	{
		this.name = name;
	}
	
	public Batiment(Long id,String name) 
	{	
		this.id=id;
		this.name = name;
		this.alerte=0;
		
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

	public void setAlerte(int a) {
		this.alerte=a;
	}
	public int getAlerte() {
		return this.alerte;
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