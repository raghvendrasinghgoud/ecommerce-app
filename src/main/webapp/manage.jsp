<!DOCTYPE html>

<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.nagarro.utils.SessionObj"%>
<%@page import="com.nagarro.dao.ProductDao"%>
<%@page import="com.nagarro.entities.Product" %>
<%@page import="com.nagarro.entities.User" %>
<%@page import="java.util.List" %>

<%
	HttpSession ss=request.getSession();
	User user=(User)ss.getAttribute("user");
	if(user==null){
		response.sendRedirect("index.jsp");
	}
	

	String error="";	
	if(session.getAttribute("fileerror")!=null){
		error=(String)session.getAttribute("fileerror");
	}
	
	
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management-Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        .error{
            color: red;
            visibility: none;
        }
    </style>
</head>
<body>
    
    <jsp:include page="header.jsp" />
    
    <section class="container mt-5">
        <form action="getproductpath"  onsubmit='return formv()' id="productform" method="post" enctype="multipart/form-data">
    <p><b>Please enter product details to add to stock</b></p>
    
   
    <p class="error text-end fs-3"><%= error %></p>
    <table>
        <tr>
            <td>
                <label for="tit">Title</label>
            </td>
            <td>
                <input type="text" name="tit" id="title">
            </td>
            <td>
                <p class="error" id="titleerror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="quan">Quantity</label>
            </td>
            <td>
                <input type="text" name="quan" id="quantity">
            </td>
            <td>
                <p class="error" id="quantityerror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="si">Size</label>
            </td>
            <td>
                <input type="text" name="si" id="size">
            </td>
            <td>
                <p class="error" id="sizeerror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="image">Image</label>
            </td>
            <td>
                <input type="text" name="ima" id="image" disabled>
            </td>
            <td>
                <input type="file" name="imgf" id="imgfile">
            </td>
            <td>
                <p class="error" id="imageerror"></p>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit">
</form>

<table class="table table-striped mt-5">
   <thead>
    <th>S.no</th>
    <th>Title</th>
    <th>Quantity</th>
    <th>Size</th>
    <th>Image</th>
    <th>Actions</th>
   </thead>
   <% 
   ProductDao pd=new ProductDao(SessionObj.getSession());
   List<Product> products=pd.getAllProducts();
	int sno=1;
   	for(Product product: products){
   %>
   <tr>
    <td><%= sno++ %></td>
    <td><%= product.getTitle() %></td>
    <td><%= product.getQuantity() %></td>
    <td><%= product.getSize() %></td>
    <td><img src="./images/products/<%= product.getImage() %>" width="150" height="120" alt="some image"></td>
    <td><a href="deleteproductpath?id=<%= product.getId() %>"><img src="./images/delete.png" width="25" height="30" alt="update icon"></a><a href="edit_product.jsp?id=<%= product.getId() %>"><img src="./images/pencil.png" width="25" height="30" alt="update icon"></a></td>
   </tr>
   <% } 
   sno=1; %>
  </table>
</section>

<script src="JS/script.js"></script>
</body>
</html>