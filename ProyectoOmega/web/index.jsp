<%-- 
    Document   : index
    Created on : May 13, 2020, 6:48:44 PM
    Author     : Mike
--%>

<%
    if(request.getSession().getAttribute("username") == null){
        response.sendRedirect("login.jsp?auth_error=1");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>¡Bienvenid@ a Chirrup!</h1>
        
    </body>
</html>
