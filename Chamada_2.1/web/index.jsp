<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%if(username != null){%>
        <center><h1>Página Inicial</h1></center> 
        <h2> Olá, professor!</h2>
        <h3><a href='teste.jsp'> Testes</a></h3>
        <%}%>
    </body>
</html>

