package com.nagarro.test;

import java.io.File;

import com.nagarro.utils.ImageUtility;

public class Test {

	public static void main(String[] args) {
//		User user=new User("raghvendra Singh", "raghvendra", "1234");
//		UserDao ud=new UserDao(SessionObj.getSession());
//		try {
//			ud.save(user);
//			
//			System.out.println("User registered...");
//		} catch (AlreadyRegisteredException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
//		
//		if(ud.isUserExists(user.getUsername())) System.out.println("user exists");
//		
//		try {
//			System.out.println(ud.getUserByUsername(user.getUsername()));
//		} catch (IdentityNotFound e) {
//			System.out.println(e.getMessage());
//		}
		
		//test product
//		Product product=new Product("Hp laptop", 3, 8,"laptop1212");
//		ProductDao pd=new ProductDao(SessionObj.getSession());
//		try {
//			pd.save(product);
//		} catch (AlreadyRegisteredException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println(pd.getAllProducts());
//		
//		String str=new ImageUtility().genrateFileName("xyz.jpg");
//		System.out.println(str);
		
		//test image utitlity
		
		ImageUtility iu=new ImageUtility();
		
		String path="C:"+File.separator+"Users"+File.separator+"raghvendragoud"+File.separator+"eclipse-workspace"+File.separator+".metadata"+File.separator+".plugins"+File.separator+"org.eclipse.wst.server.core"+File.separator+"tmp0"+File.separator+"wtpwebapps"+File.separator+"ecommerce"+File.separator+"images"+File.separator+"products"+File.separator+"img1.jpg25";
		if(iu.deleteImageFromDir(path)) {
			System.out.println("image deleted");
		}else {
			System.out.println("image not deleted");
		}
		System.out.println(path);
	}
}
