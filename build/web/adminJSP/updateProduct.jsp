<%-- 
    Document   : updateProduct
    Created on : Feb 16, 2023, 8:50:24 AM
    Author     : pthanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.Product" %>
        <%
            Vector<Product> vec = (Vector<Product>)request.getAttribute("data");
            Product pro = vec.get(0);
            int cateID = (int) request.getAttribute("cateId");
            ResultSet rs = (ResultSet) request.getAttribute("cateData");
        %>
       <form action="ProductController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    Category: <select name="CateID">
                    <% while(rs.next()){ %>
                        <option value="<%=rs.getInt(1)%>"
                                <%=(rs.getInt(1)==cateID?"selected":"")%>>
                            <%=rs.getString(2)%>
                        </option>
                    <%}%>
                    </select>
                </tr>
                <tr>
                    <td><label for="pid">Pid</label></td>
                    <td><input type="text" name="pid" id="pid" value="<%=pro.getPid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="pname">Pname</label></td>
                    <td><input type="text" name="pname" id="pname" value=""></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity" value="" ></td>
                </tr>
                <tr>
                    <td><label for="price">Price</label></td>
                    <td><input type="text" name="price" id="price" value=""></td>
                </tr>
                <tr>
                    <td><label for="image">Image</label></td>
                    <td><input type="text" name="image" id="image" value="" ></td>
                </tr>
                <tr>
                    <td><label for="description">Description</label></td>
                    <td><input type="text" name="description" id="description" value=""></td>
                </tr>
                <tr>
                    <td><label for="status">Status</label></td>
                    <td><input type="radio" name="status" id="status" value="1">Enable
                        <input type="radio" name="status" id="status" value="0">Disable</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Product" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
