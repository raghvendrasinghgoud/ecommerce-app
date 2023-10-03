package com.nagarro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.dao.UserDao;
import com.nagarro.entities.User;
import com.nagarro.exceptions.IdentityNotFound;
import com.nagarro.utils.SessionObj;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		UserDao ud=new UserDao(SessionObj.getSession());
		
		HttpSession ss=request.getSession();
		try {
			User user=ud.getUserByUsername(username);
			if(user.getPassword().equals(password)) {
				
				ss.setAttribute("user", user);
				
				System.out.println("user logged in");
				response.sendRedirect("manage.jsp");
			}else {
				ss.setAttribute("msg", "* Password does not matched");
				response.sendRedirect("index.jsp");
			}
		} catch (IdentityNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ss.setAttribute("msg", "* Invalid username");
			response.sendRedirect("index.jsp");
		}
	}

}
