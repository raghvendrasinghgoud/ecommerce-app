package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nagarro.dao.ProductDao;
import com.nagarro.entities.Product;
import com.nagarro.entities.User;
import com.nagarro.exceptions.AlreadyRegisteredException;
import com.nagarro.exceptions.IdentityNotFound;
import com.nagarro.utils.ImageUtility;
import com.nagarro.utils.SessionObj;

/**
 * Servlet implementation class EditProductServlet
 */
@MultipartConfig
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
        User user=(User)session.getAttribute("user");	
        
        if(user==null){
    		response.sendRedirect("index.jsp");
    	}
		
        int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("tit");
		System.out.println(title);
		
		int quantity=Integer.parseInt(request.getParameter("quan"));
		int size=Integer.parseInt(request.getParameter("si"));
		System.out.println(request.getParameter("quan"));
		System.out.println(request.getParameter("si"));
		Part image=request.getPart("imgf");
		
		
		System.out.println("Partname="+ image.getSize());
		try {
			
		
		ProductDao pd=new ProductDao(SessionObj.getSession());
		Product product=pd.getProductById(id);
		if(image.getSize()>0) {
		
				System.out.println("inside image operations");
			//deleting product old image
			ImageUtility iu=new ImageUtility();
			
			String path=request.getRealPath("images")+File.separator+"products"+File.separator+product.getImage();
			
			
			
			if(iu.deleteImageFromDir(path)) {
				System.out.println("image also deleted");
			}else {
				System.out.println("image not deleted");
			}
			
			//saving new image to directory
			String imageFileName =iu.genrateFileName(image.getSubmittedFileName());
		String newPath=request.getRealPath("images")+File.separator+"products"+File.separator+imageFileName;
		System.out.println(path);
		//image saved to product folder
		if(iu.saveImageToDir(image, newPath)) {
			System.out.println("new image saved");
		}else {
			System.out.println("new image not saved");
		}
		product.setImage(imageFileName);
		}
		//save product to DB
		
		
		
		product.setTitle(title);
		product.setQuantity(quantity);
		product.setSize(size);
			pd.updateProduct(product);
		
		}catch (IdentityNotFound e) {
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect("manage.jsp");
	}

}
