package web.restapp.service;

import java.util.List;

import web.restapp.model.Products;

public interface ProductsService {

	public void addProduct(Products products);
	public boolean isProductExist(Products products);
	public List<Products> getProducts();
	public Products findById(int id);
	public void updateProduct(Products products);
	public void deleteProduct(int id);
}
