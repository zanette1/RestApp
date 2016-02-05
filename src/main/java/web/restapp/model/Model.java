package web.restapp.model;

import javax.persistence.*;

@Entity
@Table(name="model")
public class Model {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="model_ID")
	private int modelID;
	@Column(name="model_nazwa")
	private String modelNazwa;
	@ManyToOne
	@JoinColumn(name="model_auto_id")
	private Auto auto;
	
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
	public String getModelNazwa() {
		return modelNazwa;
	}
	public void setModelNazwa(String modelNazwa) {
		this.modelNazwa = modelNazwa;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	

}
