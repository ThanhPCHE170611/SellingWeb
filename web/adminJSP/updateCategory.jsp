
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.Category" %>
        <%
            Vector<Category> vec = (Vector<Category>)request.getAttribute("data");
            Category cat = vec.get(0);
        %>
        <form action="CategoryController" method="post">
        <input type="hidden" name="go" value="update">
        <table>
            <tr>
                <td><label for="cateID">Category ID</label></td>
                <td><input type="text" name="cateID" id="cateID" value="<%=cat.getCateId()%>" readonly></td>
            </tr>
            <tr>
                <td><label for="cateName">Category Name</label></td>
                <td><input type="text" name="cateName" id="cateName" value="<%=cat.getCateName()%>"></td>
            </tr>
            <tr>
                <td><label for="status">Status</label></td>
                <td><input type="radio" name="status" id="status" value="1" checked>Enable
                    <input type="radio" name="status" id="status" value="0"> Disable
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Update Category" name="submit" ></td>
                <td><input type="reset" value="Reset"></td>
            </tr>
        </table>
    </form>
    </body>
</html>
