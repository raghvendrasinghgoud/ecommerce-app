package com.nagarro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.entities.Product;
import com.nagarro.exceptions.AlreadyRegisteredException;
import com.nagarro.exceptions.IdentityNotFound;

public class ProductDao {

	private final Session session;

	/**
	 * @param session
	 */
	public ProductDao(Session session) {
		super();
		this.session = session;
	}
	
	public boolean isProductExist(int id) {
		if(session.find(Product.class, id)==null)
				return false;
		
		return true;
	}
	
	public void save(Product product) throws AlreadyRegisteredException {
		
		if(isProductExist(product.getId())) {
			throw new AlreadyRegisteredException("product already exists");
		}
		
		Transaction tx=session.beginTransaction();
		
		session.save(product);
		
		tx.commit();
	}
	
	public Product getProductById(int id) throws IdentityNotFound {
		Product product=null;
		product=session.find(Product.class, id);
		
		if(product==null)
				throw new IdentityNotFound("product not registered");
		return product;
	}
	
	public List<Product> getAllProducts(){
		Query<Product> query=session.createQuery("from Product",Product.class);
		
		return query.getResultList();
	}
	
	public void deleteProduct(Product product) {
		Transaction tx=session.beginTransaction();
			session.delete(product);
		tx.commit();
	}
	
	public void updateProduct(Product product) {
		Transaction tx=session.beginTransaction();
		session.update(product);
		tx.commit();
	}
}
