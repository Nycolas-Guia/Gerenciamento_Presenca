<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selecione a Turma</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
        <%if(username != null){%>
        <div class="container p-5 mt-5">
            <div class="container" style="margin-right: 5rem">
                <div class="card p-3 text-center border-2 border-dark" style="width: 20rem;">                
                    <div class="card-body">
                        <h5 class="card-title">ADS - Vespertino</h5>
                        <p class="card-text">Turma 2022/2</p>
                        <a href="chamada.jsp" class="btn btn-primary">Iniciar Chamada</a>
                    </div>
                </div>
            </div>            
        </div>
        <!--<h3><a href='teste.jsp'> Testes</a></h3>-->
        <%}%>
    </body>
</html>

