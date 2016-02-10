package web.restapp.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Products implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="category_name")
	private String name;
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Products products;
	
	public Products() {}
	public Products(int id, String name, Products products) {
		this.id = id;
		this.name = name;
		this.products = products;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	
}
