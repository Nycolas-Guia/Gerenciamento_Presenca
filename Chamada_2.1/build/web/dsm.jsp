<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body style="background-color: grey; color: black">
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%if(username != null){%>
        <h1 style="margin: .5rem">Desenvolvimento de Software Multiplataforma</h1>
        <%}%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
    </body>
</html>
