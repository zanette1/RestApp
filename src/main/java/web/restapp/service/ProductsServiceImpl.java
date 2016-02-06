package web.restapp.service;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.restapp.model.Products;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService{

	private SessionFactory sessionFactory;
	private Session session;
	Transaction tx;
	
	@Autowired
	public ProductsServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addProduct(Products product) {
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		session.save(product);
		tx.commit();
	}
	
	public boolean isProductExist(Products product) {
		
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		Products existProduct = findById(product.getId());
		
		return existProduct != null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Products> getProducts() {
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		List<Products> list = session.createQuery("from Products").list();
		tx.commit();
		return list;
	}
	
	public Products findById(int id) {
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		Products product = (Products) this.session.get(Products.class, id);
		return product;
	}
	
	public void updateProduct(Products product) {
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		Products productToUpdate = findById(product.getId());
		productToUpdate.setName(product.getName());
		session.update(productToUpdate);
		tx.commit();
	} 
	
	public void deleteProduct(int id) {
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		Products product = findById(id);
		session.delete(product);
		tx.commit();
	}
	
	
}
