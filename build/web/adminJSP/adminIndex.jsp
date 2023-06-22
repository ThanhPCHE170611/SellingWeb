<%-- 
    Document   : adminIndex
    Created on : Mar 4, 2023, 6:38:17 PM
    Author     : pthanh
--%>

<%@page import="dao.DAOCustomer"%>
<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .top-bar p{
                color: white;
            }
            .top-bar {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                flex-wrap: nowrap;
                margin-top: 5px;
                background-color: #333;
                overflow: hidden;
                padding: 0 18px 0 18px;
            }
            .nav-bar ul{
                list-style-type: none;
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                flex-wrap: nowrap;
            }
            .nav-bar {
                margin-top: 5px;
                background-color: #333;
                overflow: hidden;
                padding: 0 18px 0 18px;
            }

            .nav-bar a {
                float: left;
                color: #f2f2f2;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                margin-left: 0px;
            }

            .nav-bar a:hover {
                background-color: #ddd;
                color: black;
            }

            .nav-bar a.active {
                background-color: #4CAF50;
                color: white;
            }
        </style>
    </head>
    <body>
        <% 
           DAOCustomer daoc = new DAOCustomer();
           int cid =(int) session.getAttribute("cid");
           Customer cus = daoc.getAllCustomer("select * from Customer where cid='" + cid +"'").get(0);
        %>
        <div class="top-bar">
            <p> RollNumber: <%= cus.getCid() %> </p>
            <p> FullName: <%= cus.getCname() %> </p>
            <p> Welcome: <%= cus.getCname() %> </p>
            <a href="ClientController?go=logout" style="color: white;">LogOut</a>
        </div>
            
        <div class="nav-bar">
            <ul>
                <li> <a href="AdminController">Home</a></li>
                <li> <a href="CustomerController">Customer Manager</a></li>
                <li> <a href="ProductController">Product Manager</a></li>
                <li> <a href="BillController">Bill Manager</a></li>
            </ul>
        </div>
    </body>
</html>
