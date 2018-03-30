package fr.best.client.entity;

import java.util.ArrayList;
import java.util.List;


public class Point
{	

	private Long id;
	

	private float x;
	

	private float y;

	private List<Signal> listSignal=new ArrayList<>();

	public Point(float x, float y)
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

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
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

	@Override
	public String toString()
	{
		return "Point [id=" + id + ", x=" + x + ", y=" + y + ", listSignal=" + listSignal + "]";
	}	
}
