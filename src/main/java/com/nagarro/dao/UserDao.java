package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.entities.User;
import com.nagarro.exceptions.AlreadyRegisteredException;
import com.nagarro.exceptions.IdentityNotFound;

public class UserDao {

	private final Session session;
	private Transaction tx;

	/**
	 * @param session
	 */
	public UserDao(Session session) {
		super();
		this.session = session;
	}
	
	public boolean isUserExists(String username) {
		
		if(session.find(User.class, username)==null) {
			return false;
		}
		return true;
	}
	
	public void save(User user) throws AlreadyRegisteredException {
		 tx= session.beginTransaction();
		if(isUserExists(user.getUsername())) {
			throw new AlreadyRegisteredException("username already registered");
		}
			session.save(user);
		tx.commit();
	}
	
	public User getUserByUsername(String username) throws IdentityNotFound {
		User user=null;
		user=session.find(User.class, username);
		
		if(user==null)
				throw new IdentityNotFound("User not registered");
		return user;
	}
	
	
}
