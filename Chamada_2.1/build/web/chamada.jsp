<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>        
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>

        <div class="container p-2 mt-5 bg-white text-dark">
            <div class="row container-fluid">                
                <div class="col-xl-5 text-center"><h4>Nome do Aluno</h4></div>
                <div class="col-xl-1 text-center"><h4>Presenças</h4></div>
                <div class="col-xl-2 text-center "><h4>Faltas</h4></div>
                <div class="col-xl-1 "><h4>1</h4></div>
                <div class="col-xl-1 "><h4>2</h4></div>
                <div class="col-xl-1 "><h4>3</h4></div>
                <div class="col-xl-1 "><h4>4</h4></div>
            </div>              
        </div>  
        <form class="form-inline" action="/Chamada_2.1">
            <div class="container p-2 bg-white text-dark">
                <div class="row container-fluid">                
                    <div class="col-xl-5 text-center"><h5>Wellingtu Ferrado</h5></div>
                    <div class="col-xl-1 text-center"><h5>30</h5></div>
                    <div class="col-xl-2 text-center"><h5>4</h5></div>
                    <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check1" name="option1" value="1"></div>
                    <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check2" name="option2" value="2"></div>
                    <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check3" name="option3" value="3"></div>
                    <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check4" name="option4" value="4"></div>
                </div>  
            </div>
            <div class="container p-2">
                <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
        </form>
    </body>
</html>
