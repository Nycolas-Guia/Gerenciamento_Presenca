<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Análise e Desenvolvimento de Sistemas</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
        <%if(username != null){%>
        <h1>Análise e Desenvolvimento de Sistemas</h1>
        <%}%>
    </body>
</html>
