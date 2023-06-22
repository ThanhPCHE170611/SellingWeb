

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,entity.Category"%>
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
        <%@page import="dao.DAOCategory"%>
        <!--import lien tiep-->
        <%@page import="entity.Category,java.util.Vector"%>
        <%
            ResultSet rs =(ResultSet) request.getAttribute("data");
            String title =(String) request.getAttribute("title");
        %>
        <table>
            <caption>Category List</caption>
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%while(rs.next()){ %>
            <tr> 
                <td><%=rs.getInt(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><a href="CategoryController?go=update&id=<%=rs.getInt(1)%>">Update</a>
                <td><a href="CategoryController?go=delete&id=<%=rs.getInt(1)%>">Delete</a>
            </tr>
            <%}%>
        </table>
    </body>
</html>
