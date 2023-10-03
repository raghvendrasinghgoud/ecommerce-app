package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.dao.ProductDao;
import com.nagarro.entities.Product;
import com.nagarro.entities.User;
import com.nagarro.exceptions.IdentityNotFound;
import com.nagarro.utils.ImageUtility;
import com.nagarro.utils.SessionObj;

/**
 * Servlet implementation class DeleteProductServlet
 */
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();  
        User user=(User)session.getAttribute("user");	
        
        if(user==null){
    		response.sendRedirect("index.jsp");
    	}
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		ProductDao pd=new ProductDao(SessionObj.getSession());
		try {
			Product product=pd.getProductById(id);
			pd.deleteProduct(product);
			
			
			//deleting product image
			ImageUtility iu=new ImageUtility();
			
			String path=request.getRealPath("images")+File.separator+"products"+File.separator+product.getImage();
			
			
			
			if(iu.deleteImageFromDir(path)) {
				System.out.println("image also deleted");
			}else {
				System.out.println("image not deleted");
			}
			
			System.out.println("product deleted");
			response.sendRedirect("manage.jsp");
		} catch (IdentityNotFound e) {
			// TODO Auto-generated catch block
			System.out.println("product not found to be deleted");
			
			e.printStackTrace();
		}
		
		
	}

}
