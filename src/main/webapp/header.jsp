
<%@page import="com.nagarro.entities.User" %>
<%
	String username="";
	String logout="";
	String log="";
	HttpSession ss=request.getSession();
	User user=(User)ss.getAttribute("user");
	if(user!=null){
		username="Hi "+user.getUsername();
		logout="logoutpath";
		log="logout";
	}
%>
<header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <p style="width: 85%;" class="text-center fs-3">
             <b>Product Management Tool</b>
            </p>
                
                  <p class="mt-3 text-end"><%= username %></p>
                   <a class="nav-link" href="<%= logout %>"><button class="btn btn-light"><%= log %></button></a>
                            
          </nav>
    </header>