<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Processos Químicos</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
        <link rel="stylesheet" type="text/css" href="style/Generico.css">
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%if(username != null){%>
        <h1>Processos Químicos</h1>
        <%}%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
    </body>
</html>
