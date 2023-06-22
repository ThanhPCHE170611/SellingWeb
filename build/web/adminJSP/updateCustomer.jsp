<%-- 
    Document   : updateCustomer
    Created on : Feb 21, 2023, 9:25:37 AM
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
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.Customer" %>
        <%
            Vector<Customer> vec = (Vector<Customer>)request.getAttribute("data");
            Customer cus = vec.get(0);
        %>
        <form action="CustomerController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                   <td><label for="cid">Cid</label></td>
                   <td><input type="text" name="cid" id="cid" value="<%=cus.getCid()%>" readonly></td> 
                </tr>

                <tr>
                    <td><label for="cname">Customer Name</label></td>
                    <td><input type="text" name="cname" id="cname" value="<%=cus.getCname()%>" ></td>
                </tr>

                <tr>
                    <td><label for="username">Username</label></td>
                    <td><input type="text" name="username" id="username" value="<%=cus.getUsername()%>"></td>
                </tr>

                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" name="password" id="password" value="<%=cus.getPassword()%>" readonly></td>
                </tr>

                <tr>
                    <td><label for="address">Address</label></td>
                    <td><input type="text" name="address" id="address" value="<%=cus.getAddress()%>"></td>
                </tr>

                <tr>
                    <td><label for="status">Status</label></td>
                    <td><input type="radio" name="status" id="status" value="1" checked>Enable
                        <input type="radio" name="status" id="status" value="0"> Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="update Customer" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
