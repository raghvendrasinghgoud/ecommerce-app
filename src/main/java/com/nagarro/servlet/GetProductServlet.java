package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
import com.nagarro.exceptions.SizeLimitExceedException;
import com.nagarro.utils.ImageUtility;
import com.nagarro.utils.SessionObj;

/**
 * Servlet implementation class GetProductServlet
 */
@MultipartConfig
public class GetProductServlet extends HttpServlet {
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
		
		String title=request.getParameter("tit");
		System.out.println(title);
	
		int quantity=Integer.parseInt(request.getParameter("quan"));
		int size=Integer.parseInt(request.getParameter("si"));
		System.out.println(request.getParameter("quan"));
		System.out.println(request.getParameter("si"));
		Part image=request.getPart("imgf");
		
		//saving image to directory
		ImageUtility iu=new ImageUtility();
		String imageFileName=iu.genrateFileName(image.getSubmittedFileName());
		String path=request.getRealPath("images")+File.separator+"products"+File.separator+imageFileName;
		System.out.println(path);
		//image saved to product folder
		iu.saveImageToDir(image, path);
		
		//create product object
		Product product=new Product(title, quantity, size, imageFileName);
		
		//save product to DB
		ProductDao pd=new ProductDao(SessionObj.getSession());
		
		//check if all images greater than 10mb;
		File file=new File(request.getRealPath("images")+File.separator+"products");
		File[] files=file.listFiles();
		System.out.println("Files length= "+files.length);
		//setting files in map with size
		HashMap<String,Float> map=new HashMap<>();
		for(File f:files) {
			map.put(f.getName(), (float)f.length()/1000000);
		}
		
		List<Product> products=pd.getAllProducts();
		
		float count=0;
		for(Product p: products) {
			count+=map.get(p.getImage());
			System.out.println(map.get(p.getImage()));
		}
		try {
			
			System.out.println("file size= "+count);
		if(count>10) {
			throw new SizeLimitExceedException("cannot add image files limit exceeded by 10 MB");
		}else {
		
			session.setAttribute("fileerror", null);
				try {
					pd.save(product);
				
				} catch (AlreadyRegisteredException e) {
		//			// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.sendRedirect("manage.jsp");
		}
		}catch(SizeLimitExceedException e) {
			System.out.println("catch limit exception");
			session.setAttribute("fileerror", e.getMessage());
			response.sendRedirect("manage.jsp");
		}
	}

}
