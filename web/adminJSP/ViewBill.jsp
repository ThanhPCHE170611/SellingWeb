<%-- 
    Document   : ViewBill
    Created on : Feb 14, 2023, 12:00:27 PM
    Author     : pthanh
--%>
<%@page import="java.sql.ResultSet,entity.Bill"%>
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
        <!--search-->
         <div>
            <form action="BillController">
                        <input type="hidden" name="go" value="search">
                        <div class="input-group">
                            <input type="text" id="name" name="name" class="form-control" placeholder="Search for BillID">
                            <div class="input-group-append">
                                <button type="submit" id="submit" class="input-group-text bg-transparent text-primary">
                                    search
                                </button>
                            </div>
                        </div>
                    </form>
        </div>
        
        
        <%@page import="dao.DAOBill"%>
        <!--import lien tiep-->
        <%@page import="entity.Bill,java.util.Vector"%>
        <% 
            ResultSet rs =(ResultSet) request.getAttribute("data");
            String title =(String) request.getAttribute("title");
        %>
        <table>
            <caption>Bill List</caption>
            <tr>
                <th>BillID</th>
                <th>Date Create</th>
                <th>Rec Address</th>
                <th>Rec Phone</th>
                <th>Note</th>
                <th>Total Money</th>
                <th>Status</th>
                <th>CustomerID</th>
                <th>View</th>
                <th>Delete</th>
            </tr>
            <% while(rs.next()) { %>
            <% String status = ""; %>
            <% switch(rs.getInt(7)){
                    case 0:
                        status = "Wait";
                        break; 
                    case 1:
                        status = "Process";
                        break;
                    case 2:
                        status = "Done";
                        break;
                    case 3:
                        status = "Removed";
                        break;
                }
            %>
            <tr> 
                <td><%= rs.getString(1)  %></td>
                <td><%= rs.getString(2)%></td>
                <td><%= rs.getString(3) %></td>
                <td><%= rs.getString(4)%></td>
                <td><%=  rs.getString(5) %></td>
                <td><%=  rs.getDouble(6)%></td>
                <td><%= status %></td>
                <td><%=  rs.getInt(8) %></td>
                <td><a href="BillController?go=update&id=<%=rs.getString(1)%>">Detail</a>
                <td><a href="BillController?go=delete&id=<%=rs.getString(1)%>">Delete</a>
            </tr>
            <%}%>
        </table>
        <a href="BillController?go=insert">Insert Bill</a>
    </body>
</html>
