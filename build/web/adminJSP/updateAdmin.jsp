<%-- 
    Document   : updateAdmin
    Created on : Feb 20, 2023, 9:00:34 PM
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
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.Admin" %>
        <%
            Vector<Admin> vec = (Vector<Admin>)request.getAttribute("data");
            Admin ad = vec.get(0);
        %>
        <form action="AdminController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="admin">Admin</label></td>
                    <td><input type="text" name="admin" id="admin" value="<%=ad.getAdmin()%>" readonly></td>
                </tr>

                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" name="password" id="password" value="<%=ad.getPassword()%>" readonly></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Update Admin" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
