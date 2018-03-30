package best.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="signale")
public class Signal 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="s_id")
	private Long id;
	
	@Column(name="rssi")
	private Integer rssi;
	
	@Column(name="ssid")
	private String ssid;
	
	@Column(name="mac")
	private String mac;

	public Signal(Integer rssi, String ssid, String mac) 
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

	public Integer getRssi() 
	{
		return rssi;
	}

	public void setRssi(Integer rssi) 
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
