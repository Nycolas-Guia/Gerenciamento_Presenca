<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Desenvolvimento de Software Multiplataforma</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
        <%if(username != null){%>
        <h1>Desenvolvimento de Software Multiplataforma</h1>
        <%}%>
    </body>
</html>