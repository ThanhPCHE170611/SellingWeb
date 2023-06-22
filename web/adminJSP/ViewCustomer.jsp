<%-- 
    Document   : ViewCustomer
    Created on : Feb 14, 2023, 10:44:18 AM
    Author     : pthanh
--%>
<%@page import="java.sql.ResultSet,entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </head>
    <body>
        <jsp:include page="adminIndex.jsp"></jsp:include>
        <!--import doc lap-->
        <%@page import="dao.DAOCustomer"%>
        <!--import lien tiep-->
        <%@page import="entity.Customer,java.util.Vector"%>
        <div class="search-bar">
                <!--search-->
                <form action="CustomerController">
                        <input type="hidden" name="go" value="search">
                        <div class="input-group">
                            <input type="text" id="name" name="name" class="form-control" placeholder="Search for Customer">
                            <div class="input-group-append">
                                <button type="submit" class="input-group-text bg-transparent text-primary">
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
                        <caption>Customer List</caption>
                        <tr>
                        <th>CustomerID</th>
                        <th>Customer Name</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Update</th>
                        <th>Delete</th>
                        </tr>
        <%while(rs.next()){  %>
        <tr>
            <td><%=rs.getInt(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getString(5)%></td>
            <td><%=rs.getString(6)%></td>
            <td><a href="CustomerController?go=update&id=<%=rs.getString(1)%>">Update</a></td>
            <td><a href="CustomerController?go=delete&id=<%=rs.getString(1)%>">Delete</a></td>
        </tr>
        <%}%>
        </table>
    </body>
</html>
