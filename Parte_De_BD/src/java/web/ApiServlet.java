package web;

import java.io.IOException;
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

@WebServlet(name = "ApiServlet", urlPatterns = {"/api/*"})
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
            if (request.getRequestURI().endsWith("/api/session")) {
                processSession(file, request, response);
            } else if (request.getRequestURI().endsWith("/api/users")) {
                processUsers(file, request, response);
            } else if (request.getRequestURI().endsWith("/api/call")) {
                processCall(file, request, response);
            } else {
                response.sendError(400, "URL Inválida");
                file.put("erro", "URL Inválida");
            }
        } catch (Exception ex) {
            response.sendError(500, "Erro interno: " + ex.getLocalizedMessage());
        }
        response.getWriter().print(file.toString());

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
        return "Breve descrição";
    }

    private void processSession(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getMethod().toLowerCase().equals("put")) {
            JSONObject body = getJSONBody(request.getReader());
            String login = body.getString("nm_login");
            String senha = body.getString("senha");
            Usuario u = Usuario.getUsuario(login, senha);
            Aluno a = null;
            if (u == null) {
                response.sendError(403, "Login ou senha incorreta");
            } else {
                request.getSession().setAttribute("Aluno", a);
                file.put("id", a.getRowId());
                file.put("RA", a.getRA());
                file.put("nome", a.getNome());
                file.put("curso", a.getCurso());
                file.put("semestre", a.getSemestre());
                file.put("message", "Conectado");
            }

        } else if (request.getMethod().toLowerCase().equals("delete")) {
            request.getSession().removeAttribute("USUARIO");
            file.put("message", "Desconectado");

        } else if (request.getMethod().toLowerCase().equals("get")) {
            if (request.getSession().getAttribute("USUARIO") == null) {
                response.sendError(403, "Não conectado");
            } else {
                Aluno a = (Aluno) request.getSession().getAttribute("ALUNO");
                file.put("id", a.getRowId());
                file.put("RA", a.getRA());
                file.put("nome", a.getNome());
                file.put("curso", a.getCurso());
                file.put("semestre", a.getSemestre());
            }
        } else {
            response.sendError(405, "Método não permitido");
        }
    }

    private void processUsers(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        if(request.getSession().getAttribute("USUARIO") == null){ 
            response.sendError(401, "Não autorizado: não conectado");                
            
        } else if(request.getMethod().toLowerCase().equals("get")){
            file.put("list", new JSONArray(Aluno.getAluno()));
            
        } else if(request.getMethod().toLowerCase().equals("post")){
            JSONObject body = getJSONBody(request.getReader());
            int RA = body.getInt("id_ra");
            String nome = body.getString("nome");
            String curso = body.getString("sg_curso");
            char semestre = body.getString("qt_semestre").charAt(0);
            Aluno.inserirAluno(RA, nome, curso, semestre);
            file.put("message", "Usuário adicionado");
        
        } else if(request.getMethod().toLowerCase().equals("put")){
            JSONObject body = getJSONBody(request.getReader());
            int RA = body.getInt("id_ra");
            String nome = body.getString("nome");
            String curso = body.getString("sg_curso");
            char semestre = body.getString("qt_semestre").charAt(0);
            Aluno.updateAluno(RA, nome, curso, semestre);
            file.put("message", "Usuário atualizado");
            
        
        } else if(request.getMethod().toLowerCase().equals("delete")){
            int RA = Integer.parseInt(request.getParameter("id_ra"));
            Aluno.deleteUsuario(RA);
            file.put("message", "Usuário deletado");

        } else{
            response.sendError(405, "Método não permitido");
        
        }
    }
    
    private void processCourse(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(request.getSession().getAttribute("USUARIO") == null){ 
            response.sendError(401, "Não autorizado: não conectado");
            
        }else if(request.getMethod().toLowerCase().equals("get")){
            file.put("list", new JSONArray(Curso.getCurso()));
            //PAREI AQUI
        }
    }

    private void processCall(JSONObject file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    }

}
