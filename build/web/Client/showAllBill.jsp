<%-- 
    Document   : showAllBill
    Created on : Mar 20, 2023, 11:47:07 PM
    Author     : pthanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>MultiShop - Online Shop Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <%@page import="java.sql.ResultSet,entity.Customer,dao.DAOCustomer"%>
        <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-1 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center h-100">
                    <a class="text-body mr-3" href="">About</a>
                    <a class="text-body mr-3" href="">Help</a>
                    <a class="text-body mr-3" href="">FAQs</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <% int cid = 0; %>
                    <!--login-->
                    <% if (session == null || session.getAttribute("cid") == null) { %>
                    <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">My Account</button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="button"> <a href="ClientController?go=login">Sign in</a> </button>
                        </div>
                    </div>
                    <%} else {
                        DAOCustomer dao = new DAOCustomer();
                        cid = (int) session.getAttribute("cid");
                        Customer cus = (Customer) dao.getAllCustomer("select * from Customer where cid='" + cid + "'").get(0);
                    %>
                    <div class="btn-group">
                        <h5 style="font-weight: bold;">Welcome, <%= cus.getCname()%></h5>
                    </div>
                    <div class="btn-group">
                        <p><a href="ClientController?go=logout">Logout</a></p>
                    </div>
                    <%}%>
                    <div class="btn-group mx-2">
                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">USD</button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="button">EUR</button>
                            <button class="dropdown-item" type="button">GBP</button>
                            <button class="dropdown-item" type="button">CAD</button>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">EN</button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="button">FR</button>
                            <button class="dropdown-item" type="button">AR</button>
                            <button class="dropdown-item" type="button">RU</button>
                        </div>
                    </div>
                </div>
                <div class="d-inline-flex align-items-center d-block d-lg-none">
                    <a href="" class="btn px-0 ml-2">
                        <i class="fas fa-heart text-dark"></i>
                        <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;" value="99+">99+</span>
                    </a>
                    <a href="ClientController?go=cart" class="btn px-0 ml-2">
                        <i class="fas fa-shopping-cart text-dark"></i>
                        <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
            <div class="col-lg-4">
                <a href="" class="text-decoration-none">
                    <span class="h1 text-uppercase text-primary bg-dark px-2">Multi</span>
                    <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Shop</span>
                </a>
            </div>
            <div class="col-lg-4 col-6 text-left">
                <form action="ClientController">
                    <input type="hidden" name="go" value="search">
                    <div class="input-group">
                        <input type="text" id="pname" name="pname" class="form-control" placeholder="Search for products">
                        <div class="input-group-append">
                            <button type="submit" class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-4 col-6 text-right">
                <p class="m-0">Customer Service</p>
                <h5 class="m-0">+012 345 6789</h5>
            </div>
        </div>
    </div>
    <!-- Topbar End -->
    <% ResultSet rs = (ResultSet) request.getAttribute("dataBill"); %>
    <form action="ClientController" method="post">
        <input type="hidden" name="go" value="updateBillStatus">
        <table border="1">
        <tr>
            <th>BillID</th>
            <th>TotalMoney</th>
            <th>Status</th>
            <th>Remove</th>
        </tr>
        <% while(rs.next()){ %>
        <% String status = "";
            switch(rs.getInt(7)){
                case 0 :
                    status = "Wait";
                    break;
                case 1 :
                    status = "Process";
                    break;
                case 2 :
                    status = "Done";
                    break;
                case 3 :
                    status = "Removed";
                    break;
            }
        %>
        <tr>
            <td><%=rs.getString(1)%></td>
            <td><%= rs.getDouble(6) %></td>
            <td><%= status %></td>
            <% if(status.equals("Wait")){ %>
            <td> <a href="ClientController?go=removeBill&bid=<%= rs.getString(1) %>&cid=<%=rs.getInt(8)%>">Remove</a> </td>
            <% } %>
        </tr>
        <%}%>
        </table>
    </form>