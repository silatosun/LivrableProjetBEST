package fr.best.client.entity;



public class User 
{
	private Long id;
	
	private String name;

	private String number;

	private Boolean type;

	public User(String name, String number) 
	{
		this.name = name;
		this.number = number;
		this.type=false;
	}
	
	public User()
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

	public String getNumber() 
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
	
	

}
