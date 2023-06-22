<%-- 
    Document   : updateBill
    Created on : Feb 22, 2023, 8:10:13 PM
    Author     : pthanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
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
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.BillDetail" %>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("data");
            int statusB = (int) request.getAttribute("statusB");
            String bid = "";
        %>
        <table>
            <tr>
                <th>Bid</th>
                <th>Pid</th>
                <th>Buy Quantity</th>
                <th>Buy Price</th>
                <th>Subtotal</th>
            </tr>
            
            <% while (rs.next()) {%>
            <tr>
                <td><%= rs.getString(1) %></td>
                <td><%= rs.getString(2) %></td>
                <td><%= rs.getInt(3) %></td>
                <td><%= rs.getDouble(4) %></td>
                <td><%= rs.getDouble(5) %></td>
            </tr>
            <% bid = rs.getString(1); %>
            <%}%>
        </table>
        <form action="BillController" method="post">
            <input type="hidden" name="go" value="update">
            <% String error = (String) request.getAttribute("error");%>
            <%if(error!=null){%>
            <p style="color:red; font-weight: bold"><%= error %></p>
            <%}%>
            <tr>
                <td><label for="bid">Bid</label></td>
                <td>
                    <input type="text" name="bid" id="bid" value="<%= bid %>" readonly>
                </td>
            </tr>
            <p></p>
            <tr>
                <td><label for="status">Status</label></td>
                <td><input type="radio" name="status" id="status" value="0" <%= (statusB == 0?"checked" : "") %>>Wait
                    <input type="radio" name="status" id="status" value="1" <%= (statusB == 1?"checked" : "") %> > Process
                    <input type="radio" name="status" id="status" value="2" <%= (statusB == 2?"checked" : "") %>> Done
                    <input type="radio" name="status" id="status" value="3" <%= (statusB == 3?"checked" : "") %>> Removed
                </td>
            </tr>
            <p></p>

            <tr>
                <td><input type="submit" value="Update Bill" name="submit" ></td>
                <td><input type="reset" value="Reset"></td>
            </tr>
        </form>
    </body>
</html>
