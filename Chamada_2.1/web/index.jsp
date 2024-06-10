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
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>
        <%if(username != null){%>
        <div class="container p-5 mt-5">
            <div class="container col-sm-5" style="margin-right: 5rem">
                <div class="card p-3 text-center border-2 border-dark" style="width: 20rem;">                
                    <div class="card-body">
                        <h5 class="card-title">ADS</h5>
                        <p class="card-text">Análise e Desenvolvimento de Sistemas</p>
                        <a href="selecionaTurma.jsp" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
            <div class="container col-sm-5 ">
                <div class="card p-3 text-center border-2 border-dark" style="width: 20rem;">                
                    <div class="card-body">
                        <h5 class="card-title">DSM</h5>
                        <p class="card-text">Desenvolvimento de Software Multiplataforma</p>
                        <a href="selecionaTurma.jsp" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
    </body>
</html>

