package com.nagarro.test;

import com.nagarro.dao.UserDao;
import com.nagarro.entities.User;
import com.nagarro.exceptions.AlreadyRegisteredException;
import com.nagarro.utils.SessionObj;

public class Test {

	private void psvm() {
	
		User user=new User("Raghvendra Singh", "raghvendra", "1234");
		
		//db operations
		UserDao ud=new UserDao(SessionObj.getSession());
				try {
					ud.save(user);
				} catch (AlreadyRegisteredException e) {
					System.out.println(e.getMessage());
				}
		
				//check user exist function or save function
		if(ud.isUserExists("raghvendra")) System.out.println("Exists user");
		
		
				

	}
}
