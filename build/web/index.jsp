<%-- 
    Document   : index
    Created on : Sep 14, 2025, 8:46:23 PM
    Author     : qnhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator - Máy Tính</title>
    </head>
    <body>
        <!-- Main heading for the calculator page -->
        <h1>Máy Tính</h1>

        <!-- Calculator form that sends data to MainController servlet -->
        <form action="b2controller" method="GET">
            <!-- First number input field -->
            Num1: <input type="text" name="num1" value="${num1}" /><br>
            
            <!-- Second number input field -->
            Num2: <input type="text" name="num2" value="${num2}" /><br>
            
            <!-- Operation selection dropdown -->
            Phép tính:   <select name="phepTinh"><br>
                <option value = "+">Cộng</option> 
                <option value = "-">Trừ</option>
                <option value = "x">Nhân</option>
                <option value = ":">Chia</option>
            </select>      <br>

            <!-- Submit button to perform calculation -->
            <input type="submit" value="Calculate" />
        </form>
        
       
        <!-- JSP scriptlet to retrieve data from servlet -->
        <%
            // Get attributes passed from MainController servlet
            String error = (String) request.getAttribute("error");
            Double result = (Double) request.getAttribute("result");
            String num1 = (String) request.getAttribute("num1");
            String num2 = (String) request.getAttribute("num2");
            String phepTinh = (String) request.getAttribute("phepTinh");
        %>
        
        <!-- Display error message if calculation failed -->
        <% if (error != null && !error.isEmpty()) { %>
               <div style="color: red;">Error: <%= error %></div>
        <% } %>
        
        <!-- Display calculation result if successful -->
        <% if (result != null && (error == null || error.isEmpty())) { %>
               <div style="color: red;">Result: <%= num1 %> <%= phepTinh %> <%= num2 %> = <%= result %></div>
        <% }         %>
        
        <!-- End of calculator page -->


    </body>
</html>
