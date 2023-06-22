<%-- 
    Document   : ViewBillDetail
    Created on : Feb 15, 2023, 4:29:44 PM
    Author     : pthanh
--%>
<%@page import="java.sql.ResultSet,entity.BillDetail"%>
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
        <%@page import="dao.DAOBillDetail"%>
        <!--import lien tiep-->

        <%@page import="entity.BillDetail,java.util.Vector"%>
        <jsp:include page="adminIndex.jsp"></jsp:include>
        <% 
            ResultSet rs =(ResultSet) request.getAttribute("data");
            String title =(String) request.getAttribute("title");
        %>
        <table>
            <caption>Bill Detail List</caption>
            <tr>
                <th>Bill ID</th>
                <th>Product ID</th>
                <th>Buy Quantity</th>
                <th>Buy Price</th>
                <th>Subtotal</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% while(rs.next()){ %>
            <tr>
                <td><%= rs.getString(1) %></td>
                <td><%= rs.getString(2) %></td>
                <td><%= rs.getInt(3) %></td>
                <td><%= rs.getDouble(4) %></td>
                <td><%= rs.getDouble(4) %></td>
                <td><a href="BillDetailController?go=update&bid=<%=rs.getString(1)%>&pid=<%=rs.getString(2)%>">Update</a>
                <td><a href="BillDetailController?go=delete&bid=<%=rs.getString(1)%>&pid=<%=rs.getString(2)%>">Delete</a>
            </tr>
            <%}%>   
        </table>
        <a href="BillDetailController?go=insert">Insert Bill Detail</a>
    </body>
</html>
