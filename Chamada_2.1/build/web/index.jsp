<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body style="background-color: light-grey; color: black">
        <center><h1>Página Inicial do Sistema de Chamadas</h1></center>
        <a href='ads.jsp'><h2>Análise e Desenvolvimento de Sistemas </h2></a>
        <a href='dsm.jsp'><h2>Desenvolvimento de Software Multiplataforma </h2></a>
        <a href='pq.jsp'><h2>Processos Químicos </h2></a>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        
        <%if(username != null){%>
        
        <div class="m-2">
            <h1>Página Inicial</h1>            
        </div>
        <%}%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
    </body>
</html>
