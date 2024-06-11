package web; // Indicando o pacote

import java.io.IOException; //Imports de bibliotecas nessessárias
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "ApiServlet", urlPatterns = {"/api/*"}) // Define o padrão de URL
public class ApiServlet extends HttpServlet {

    private JSONObject getJSONBody(BufferedReader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return new JSONObject(buffer.toString());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject file = new JSONObject();
        try {
            if (request.getRequestURI().endsWith("/api/session")) { // If que será acionado caso a URI termine com session
                processSession(file, request, response); // Acessa a função processSession
            } else if (request.getRequestURI().endsWith("/api/aluno")) { // If que será acionado caso a URI termine com aluno
                processAluno(file, request, response); // Acessa a função processAluno
            } else if (request.getRequestURI().endsWith("/api/ADS")) { // If que será acionado caso a URI termine com aluno
                processADS(file, request, response); // Acessa a função processAluno
            } else if (request.getRequestURI().endsWith("/api/DSM")) { // If que será acionado caso a URI termine com aluno
                processDSM(file, request, response); // Acessa a função processAluno
            } else if (request.getRequestURI().endsWith("/api/falta")) { // If que será acionado caso a URI termine com call
                processFalta(file, request, response); // Acessa a função processCall
            } else {
                response.sendError(400, "URL Inválida"); // Informa ao cliente que a solicitação não pôde ser processada
                file.put("erro", "URL Inválida"); // Adiciona ao JSONobject o erro
            }
        } catch (Exception ex) { // Caso aconteça algo inesperado
            response.sendError(500, "Erro interno: " + ex.getLocalizedMessage()); // Informa ao cliente que a solicitação não pôde ser processada
        }
        response.getWriter().print(file.toString()); // Passa o JSONObject para String

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Uma lista de chamadas, onde o professor poderá gerenciar as faltas e presenças de alunos de vários cursos e semestres.";
    }

    private void processSession(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getMethod().toLowerCase().equals("put")) { // If que será acionado caso o método seja put
            JSONObject body = getJSONBody(request.getReader()); // Cria um objeto json
            String login = body.getString("login"); // Cria uma variavel que responderá caso esteja escrito login no body do json object
            String senha = body.getString("senha"); // Cria uma variavel que responderá caso esteja escrito senha no body do json object
            Usuario u = Usuario.getUsuario(login, senha); // Acessa a função getUsuario na classe Usuario e define um objeto como o retorno dessa função
            if (u == null) { // Caso objeto definido volte nulo
                response.sendError(403, "Login ou senha incorreta"); // Erro de login ou senha incorreto
            } else {
                request.getSession().setAttribute("Usuario", u); // Conecta o usuario a sessão 
                file.put("id", u.getRowId()); // Mostra o id
                file.put("Login", u.getLogin()); // Mostra o login
                file.put("Senha", u.getSenha()); // Mostra a senha
                file.put("message", "Conectado"); // Mostra uma mensagem de conectado
            }

        } else if (request.getMethod().toLowerCase().equals("delete")) { // If que será acionado caso o método seja delete
            request.getSession().removeAttribute("Usuario");  // Desconecta o usuario a sessão 
            file.put("message", "Desconectado"); // Exibe uma mensagem de desconexão

        } else if (request.getMethod().toLowerCase().equals("get")) { // If que será acionado caso o método seja get
            if (request.getSession().getAttribute("Usuario") == null) { // Caso não tenha nenhuma sessão conectada
                response.sendError(403, "Nao conectado"); // Exibe erro de não conexão
            } else {
                Usuario u = (Usuario) request.getSession().getAttribute("Usuario"); // Define um objeto como atributo do usuario, mostrando que existe conexão
                file.put("id", u.getRowId()); // Mostra o rowid
                file.put("Login", u.getLogin()); // Mostra o login
                file.put("Senha", u.getSenha()); // Mostra a senha
            }
        } else { // Caso o método não corresponda a nenhuma das opções
            response.sendError(405, "Método não permitido"); // Mostra um erro de Método inválido
        }
    }

    private void processAluno(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        if(request.getSession().getAttribute("Usuario") == null){  // Caso não tenha nenhuma sessão conectada
            response.sendError(401, "Não autorizado: não conectado");// Exibe erro de não conexão
            
        } else if(request.getMethod().toLowerCase().equals("get")){ // If que será acionado caso o método seja get
            file.put("Alunos", new JSONArray(Aluno.getAluno())); // Exibe o array localizado na classe Aluno                  
            
        } else if(request.getMethod().toLowerCase().equals("post")){ // If que será acionado caso o método seja post
            JSONObject body = getJSONBody(request.getReader()); // Cria um objeto json
            Aluno a = (Aluno) request.getSession().getAttribute("Aluno"); // Define um objeto como atributo do aluno, mostrando que existe conexão
            int RA = body.getInt("ra"); // Cria uma variavel que responderá caso esteja escrito ra no body do json object
            String nome = body.getString("nome"); // Cria uma variavel que responderá caso esteja escrito nome no body do json object
            String curso = body.getString("curso"); // Cria uma variavel que responderá caso esteja escrito curso no body do json object
            int semestre = body.getInt("semestre"); // Cria uma variavel que responderá caso esteja escrito semestre no body do json object
            Aluno.inserirAluno(RA, nome, curso, semestre); // Chama a função que insere alunos
            file.put("message", "Aluno adicionado"); // Exibe uma mensagem de aluno adicionado
        
        } else if(request.getMethod().toLowerCase().equals("put")){ // If que será acionado caso o método seja put
            JSONObject body = getJSONBody(request.getReader()); // Cria um objeto json
            int RA = body.getInt("ra");  // Cria uma variavel que responderá caso esteja escrito ra no body do json object
            String nome = body.getString("nome");  // Cria uma variavel que responderá caso esteja escrito nome no body do json object
            String curso = body.getString("curso");  // Cria uma variavel que responderá caso esteja escrito curso no body do json object
            int semestre = body.getInt("semestre");  // Cria uma variavel que responderá caso esteja escrito semestre no body do json object
            Aluno.updateAluno(RA, nome, curso, semestre); //Chama a função que atualiza alunos
            file.put("message", "Aluno atualizado"); // Exibe uma mensagem de aluno atualizado
            
        
        } else if(request.getMethod().toLowerCase().equals("delete")){ // If que será acionado caso o método seja delete
            int RA = Integer.parseInt(request.getParameter("id_ra")); // Pede o ra do aluno
            Aluno.deleteUsuario(RA); // Deleta o aluno com o ra correspondente
            file.put("message", "Aluno deletado"); // Exibe uma mensagem de aluno deletado

        } else{ // Caso o método não corresponda a nenhuma das opções
            response.sendError(405, "Método não permitido"); // Mostra um erro de Método inválido
        
        }
    }
    
    private void processADS(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(request.getSession().getAttribute("Usuario") == null){  // Caso não tenha nenhuma sessão conectada
            response.sendError(401, "Não autorizado: não conectado");// Exibe erro de não conexão
            
        } else if(request.getMethod().toLowerCase().equals("get")){ // If que será acionado caso o método seja get
            file.put("AlunosADS", new JSONArray(Aluno.alunoADS())); // Exibe o array localizado na classe Aluno                  
            
        } else{ // Caso o método não corresponda a nenhuma das opções
            response.sendError(405, "Método não permitido"); // Mostra um erro de Método inválido
        
        }
        
    }
    
    private void processFalta(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(request.getSession().getAttribute("Usuario") == null){  // Caso não tenha nenhuma sessão conectada
            response.sendError(401, "Não autorizado: não conectado");// Exibe erro de não conexão
            
        } else if(request.getMethod().toLowerCase().equals("get")){ // If que será acionado caso o método seja get                        
            file.put("Faltas", new JSONArray(Falta.getFalta())); // Exibe o array localizado na classe Aluno                  
            
        } else if(request.getMethod().toLowerCase().equals("post")){ // If que será acionado caso o método seja get                        
            JSONObject body = getJSONBody(request.getReader()); // Cria um objeto json
            int RA = body.getInt("ra"); // Cria uma variavel que responderá caso esteja escrito ra no body do json object
            int falta = body.getInt("falta");
            Falta.inserirFalta(RA, falta);
            
        } else{ // Caso o método não corresponda a nenhuma das opções
            response.sendError(405, "Método não permitido"); // Mostra um erro de Método inválido
        
        }
        
    }
    
    private void processDSM(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(request.getSession().getAttribute("Usuario") == null){  // Caso não tenha nenhuma sessão conectada
            response.sendError(401, "Não autorizado: não conectado");// Exibe erro de não conexão
            
        } else if(request.getMethod().toLowerCase().equals("get")){ // If que será acionado caso o método seja get
            file.put("AlunosDSM", new JSONArray(Aluno.alunoDSM())); // Exibe o array localizado na classe Aluno                  
            
        } else{ // Caso o método não corresponda a nenhuma das opções
            response.sendError(405, "Método não permitido"); // Mostra um erro de Método inválido
        
        }
        
    }    

}