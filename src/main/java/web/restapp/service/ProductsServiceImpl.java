package web.restapp.service;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.restapp.model.Products;

@Transactional
public class ProductsServiceImpl implements ProductsService{

	private SessionFactory sessionFactory;
	private Session session;
	
	
	@Autowired
	public ProductsServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addProduct(Products product) {
		session = this.sessionFactory.getCurrentSession();
		session.save(product);
	}
	
	public boolean isProductExist(Products product) {
		
		session = this.sessionFactory.getCurrentSession();
		Products existProduct = findById(product.getId());
		
		return existProduct != null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Products> getProducts() {
		session = this.sessionFactory.getCurrentSession();
		List<Products> list = session.createQuery("from Products").list();
		return list;
	}
	
	public Products findById(int id) {
		session = this.sessionFactory.getCurrentSession();
		Products product = (Products) this.session.get(Products.class, id);
		return product;
	}
	
	public void updateProduct(Products product) {
		session = this.sessionFactory.getCurrentSession();
		Products productToUpdate = findById(product.getId());
		productToUpdate.setName(product.getName());
		session.update(productToUpdate);
	} 
	
	public void deleteProduct(int id) {
		session = this.sessionFactory.getCurrentSession();
		Products product = findById(id);
		session.delete(product);
	}
	
}
