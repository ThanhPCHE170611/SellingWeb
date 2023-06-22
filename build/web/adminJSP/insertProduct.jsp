<%-- 
    Document   : insertProduct
    Created on : Mar 6, 2023, 9:11:42 PM
    Author     : pthanh
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InsertProduct</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <form action="ProductController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="pid">Pid</label></td>
                    <td><input type="text" name="pid" id="pid"></td>
                </tr>
                <tr>
                    <td><label for="pname">Pname</label></td>
                    <td><input type="text" name="pname" id="pname"></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity"></td>
                </tr>
                <tr>
                    <td><label for="price">Price</label></td>
                    <td><input type="text" name="price" id="price"></td>
                </tr>
                <tr>
                    <td><label for="image">Image</label></td>
                    <td><input type="text" name="image" id="image"></td>
                </tr>
                <tr>
                    <td><label for="description">Description</label></td>
                    <td><input type="text" name="description" id="description"></td>
                </tr>
                <% ResultSet rsCat = (ResultSet)  request.getAttribute("dataCat"); %>
                <tr>
                    <td>Category:</td>
                    
                    <td><select id="CateID" name="CateID">
                            <% while(rsCat.next()){ %>
                            <option value="<%= rsCat.getInt(1) %>"><%= rsCat.getString(2) %></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Product" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
