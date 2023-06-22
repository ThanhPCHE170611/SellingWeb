<%-- 
    Document   : insertBillDetail
    Created on : Feb 26, 2023, 11:58:11 AM
    Author     : pthanh
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Bill Detail Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <form action="BillDetailController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <% ResultSet rsB = (ResultSet) request.getAttribute("dataB"); %>
                 <tr>
                    <td>Bill:</td>
                    <td><select id="bid" name="bid">
                            <%while(rsB.next()){%>
                            <option value="<%=rsB.getString(1)%>"><%=rsB.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <% ResultSet rsP = (ResultSet) request.getAttribute("dataP"); %>
                 <tr>
                    <td>Product:</td>
                    <td><select id="pid" name="pid">
                            <%while(rsP.next()){%>
                            <option value="<%=rsP.getString(1)%>"><%=rsP.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td><label for="buyQuantity">BuyQuantity</label></td>
                    <td><input type="text" name="buyQuantity" id="buyQuantity"></td>
                </tr>
                <tr>
                    <td><label for="buyPrice">BuyPrice</label></td>
                    <td><input type="text" name="buyPrice" id="buyPrice"></td>
                </tr>
                <tr>
                    <td><label for="subtotal">SubTotal</label></td>
                    <td><input type="text" name="subtotal" id="subtotal"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Bill Detail" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
