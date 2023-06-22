<%-- 
    Document   : insertAdmin
    Created on : Feb 26, 2023, 10:59:37 AM
    Author     : pthanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Admin Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <form action="../AdminController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="admin">Admin</label></td>
                    <td><input type="text" name="admin" id="admin"></td>
                </tr>

                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" name="password" id="password"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Admin" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
