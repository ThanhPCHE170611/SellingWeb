<%-- 
    Document   : insertBill
    Created on : Feb 26, 2023, 11:13:57 AM
    Author     : pthanh
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Bill Page</title>
          <link rel="stylesheet" href="style/styleInsert.css">
    </head>
    <body>
        <form action="../BillController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="bid">BillID</label></td>
                    <td><input type="text" name="bid" id="bid"></td>
                </tr>
                
                <tr>
                    <td><label for="recAddress">RecAddress</label></td>
                    <td><input type="text" name="recAddress" id="recAddress"></td>
                </tr>

                <tr>
                    <td><label for="recPhone">RecPhone</label></td>
                    <td><input type="text" name="recPhone" id="recAddress"></td>
                </tr>

                <tr>
                    <td><label for="note">Note</label></td>
                    <td><input type="text" name="note" id="note"></td>
                </tr>
                <tr>
                    <td><label for="totalmoney">Total Money</label></td>
                    <td><input type="text" name="totolmoney" id="totolmoney"></td>
                </tr>
                <% ResultSet rs = (ResultSet) request.getAttribute("dataCus"); %>
                 <tr>
                    <td>Customer:</td>
                    <td><select id="cusID" name="cusID">
                            <%while(rs.next()){%>
                            <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Bill" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
