package web.restapp.model;

import javax.persistence.*;

@Entity
@Table(name="auto")
public class Auto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="auto_ID")
	private int autoID;
	@Column(name="auto_nazwa")
	private String autoNazwa;
	
	public int getId() {
		return autoID;
	}
	public void setId(int autoID) {
		this.autoID = autoID;
	}
	public String getAutoNazwa() {
		return autoNazwa;
	}
	public void setAutoNazwa(String autoNazwa) {
		this.autoNazwa = autoNazwa;
	}
	
	
}
