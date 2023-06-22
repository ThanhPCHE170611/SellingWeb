

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <%@page import="java.sql.ResultSet, java.util.Vector,entity.BillDetail" %>
        <%
            Vector<BillDetail> vec = (Vector<BillDetail>)request.getAttribute("data");
            BillDetail bi = vec.get(0);
        %>
        <form action="BillDetailController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="bid">BillID</label></td>
                    <td><input type="text" name="bid" id="bid" value="<%=bi.getBid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="pid">ProductID</label></td>
                    <td><input type="text" name="pid" id="pid" value="<%=bi.getPid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="buyQuantity">BuyQuantity</label></td>
                    <td><input type="text" name="buyQuantity" id="buyQuantity" value="<%=bi.getBuyQuantity()%>" ></td>
                </tr>
                <tr>
                    <td><label for="buyPrice">BuyPrice</label></td>
                    <td><input type="text" name="buyPrice" id="buyPrice" value="<%=bi.getBuyPrice()%>" ></td>
                </tr>
                <tr>
                    <td><label for="subtotal">SubTotal</label></td>
                    <td><input type="text" name="subtotal" id="subtotal" value="<%=bi.getSubtotal()%>" ></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Bill" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
