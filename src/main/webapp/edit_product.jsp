<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.nagarro.utils.SessionObj"%>
<%@page import="com.nagarro.dao.ProductDao"%>
<%@page import="com.nagarro.entities.Product" %>
<%@page import="com.nagarro.entities.User" %>
<%@page import="java.util.List" %>
<%
	int id=Integer.parseInt(request.getParameter("id"));
	System.out.println(id);
	HttpSession ss=request.getSession();
	User user=(User)ss.getAttribute("user");
	if(user==null){
		response.sendRedirect("index.jsp");
	}
	
	Product product=new ProductDao(SessionObj.getSession()).getProductById(id);
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit product</title>
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
	
	<form action="editproductpath"  onsubmit='return formv2()' id="productform" method="post" enctype="multipart/form-data">
    <p><b>Please enter product details to add to stock</b></p>
    <table>
    <tr style="visibility: hidden;" >
            <td>
                <label for="id">Id</label>
            </td>
            <td>
                <input type="text" name="id" id="id" value="<%= product.getId() %>" >
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td>
                <label for="tit">Title</label>
            </td>
            <td>
                <input type="text" name="tit" id="title" value="<%= product.getTitle() %>" >
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
                <input type="text" name="quan" id="quantity" value="<%= product.getQuantity() %>" >
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
                <input type="text" name="si" id="size" value="<%=product.getSize() %>" >
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
                <input type="text" name="ima" id="image" value="<%= product.getImage() %>" disabled>
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

<script src="JS/script.js"></script>
<script src="JS/editScript.js"></script>
</body>
</html>