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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="point")
public class Point
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id")
	private Long id;
	
	@Column(name="x")
	private double x;
	
	@Column(name="y")
	private double y;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Signal> listSignal=new ArrayList<>();
	
	@ManyToMany(mappedBy="listPoint")
	private  List<Zone> listZone=new ArrayList<>();

	public Point(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public Point() 
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

	public double getX() 
	{
		return x;
	}

	public void setX(double x) 
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y) 
	{
		this.y = y;
	}

	public List<Signal> getListSignal() 
	{
		return listSignal;
	}

	public void setListSignal(List<Signal> listSignal) 
	{
		this.listSignal = listSignal;
	}
	

	public List<Zone> getListZone() {
		return this.listZone;
	}

	public void setListZone(Zone zone) {
		this.listZone.add(zone);
		zone.getListPoint().add(this);
	}

	@Override
	public String toString()
	{
		return "Point [id=" + id + ", x=" + x + ", y=" + y + ", listSignal=" + listSignal + "]";
	}	
}
