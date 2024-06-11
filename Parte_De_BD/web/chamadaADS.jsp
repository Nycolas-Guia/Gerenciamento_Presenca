<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chamada</title>
        <%@include file="WEB-INF/jspf/html-head-libs.jspf"%>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navbar.jspf"%>        
        <%@include file="WEB-INF/jspf/html-body-libs.jspf"%>

        <div id="app">
            <div v-if="shared.session">
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
                <form class="form-inline" action="/Chamada_Final">
                    <div class="container p-2 bg-white text-dark">
                        <div class="row container-fluid">                
                            <table class="table">
                                <tr v-for="aluno in lista" :key="item.rowid">
                                    <div class="col-xl-5-text-center">{{ item.ra }}</div>
                                    <div class="col-xl-5-text-center">{{ item.nome }}</div>
                                    <div class="col-xl-5-text-center">{{ item.faltas }}</div>                                    
                                <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check1" name="option1" value="1"></div>
                                <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check2" name="option2" value="2"></div>
                                <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check3" name="option3" value="3"></div>
                                <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check4" name="option4" value="4"></div>
                            
                            <!--<div class="col-xl-5 text-center"><h5>Wellingtu Ferrado</h5></div>
                            <div class="col-xl-1 text-center"><h5>30</h5></div>
                            <div class="col-xl-2 text-center"><h5>4</h5></div>
                            <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check1" name="option1" value="1"></div>
                            <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check2" name="option2" value="2"></div>
                            <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check3" name="option3" value="3"></div>
                            <div class="col-xl-1"> <input type="checkbox" class="form-check-input w-25 h-75 border-black" id="check4" name="option4" value="4"></div>-->
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="container p-2">
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            const app = Vue.createApp({
            data() {
            return{
            shared: shared,
                    error: null,
                    ra: '',
                    nome: '',
                    faltas: '',
                    lista[],
            }
            },
                    methods: {
                    async request(url = "", method, data) {
                    try {
                    const response = await fetch(url, {
                    method: method,
                            headers: {"Content-Type": "application/json", },
                            body: JSON.stringify(data)
                    });
                    if (response.status == 200) {
                    return response.json();
                    } else {
                    this.error = response.statusText;
                    }
                    } catch (e) {
                    this.error = e;
                    return null;
                    }
                    },
                            async loadLista(){
                    const data = await fetch this.request("/Chamada_Final/api/ADS", "GET");
                    if (data){this.lista = data.Alunos}
                    },
                            async addFalta(){
                    const data = await fetch this.request("/Chamada_Final/api/falta", "POST", {"RA": this.ra, "falta": this.faltas});
                    if (data){
                    this.ra =
                    }
                    }

                    },
                    mounted() {

            }

            });
            app.mount('#app');
        </script>

    </body>
</html>