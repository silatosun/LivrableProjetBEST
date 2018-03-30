package fr.best.client.entity;


public class Signal 
{
	private Long id;

	private int rssi;

	private String ssid;

	private String mac;

	public Signal(int rssi, String ssid, String mac)
	{
		this.rssi = rssi;
		this.ssid = ssid;
		this.mac = mac;
	}
	
	public Signal() 
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

	public int getRssi()
	{
		return rssi;
	}

	public void setRssi(int rssi)
	{
		this.rssi = rssi;
	}

	public String getSsid() 
	{
		return ssid;
	}

	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}

	public String getMac() 
	{
		return mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac;
	}

	@Override
	public String toString() 
	{
		return "Signal [id=" + id + ", rssi=" + rssi + ", ssid=" + ssid + ", mac=" + mac + "]";
	}		
}
