<%-- 
    Document   : ViewProduct
    Created on : Feb 16, 2023, 8:06:03 AM
    Author     : pthanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
        
    </head>
    <body>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 16px;
            }

            caption {
                text-align: left;
                font-size: 24px;
                font-weight: bold;
                padding-bottom: 10px;
            }

            th {
                background-color: #f7f7f7;
                font-weight: bold;
                text-align: left;
                padding: 10px;
                border: 1px solid #ddd;
            }

            td {
                border: 1px solid #ddd;
                padding: 10px;
            }

            td img {
                display: block;
                max-width: 100%;
                height: auto;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            a {
                text-decoration: none;
                color: #1a1a1a;
                padding: 5px 10px;
                border: 1px solid #1a1a1a;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            a:hover {
                background-color: #1a1a1a;
                color: #fff;
            }
        </style>
        <jsp:include page="adminIndex.jsp" ></jsp:include>
        
        <div>
            <form action="ProductController">
                        <input type="hidden" name="go" value="search">
                        <div class="input-group">
                            <input type="text" id="name" name="name" class="form-control" placeholder="Search for Product">
                            <div class="input-group-append">
                                <button type="submit" id="submit" class="input-group-text bg-transparent text-primary">
                                    search
                                </button>
                            </div>
                        </div>
                    </form>
        </div>
        <%
            ResultSet rs =(ResultSet) request.getAttribute("data");
            String title =(String) request.getAttribute("title");
        %>
        <table>
            <caption>Product List</caption>
            <tr>
                <th>Product ID</th>
                <th>ProductName</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% while (rs.next()){ %>
            <tr>

                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(9)%> </td>
                <td><%=rs.getInt(3)%></td>
                <td><%=rs.getDouble(4)%> </td>
                <td><img src="<%=rs.getString(5)%>" width="100" height="150"></td>
                <td><%= rs.getString(6)%></td>
                <td><a href="ProductController?go=update&id=<%=rs.getString(1)%>&cateID=<%=rs.getInt(8)%>">Update</a>
                <td><a href="ProductController?go=delete&id=<%=  rs.getString(1)%>">Delete</a>

            </tr>
            <%}%>
        </table>
        <a href="ProductController?go=insert">Insert Product</a>
    </body>
</html>
