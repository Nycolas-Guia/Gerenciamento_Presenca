package model;

import java.sql.*;
import java.util.ArrayList;
import web.*;

public class Usuario {
    
    private Long rowId;
    private String login;
    private String senha;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS USUARIO("
                + "nm_login VARCHAR(50) UNIQUE NOT NULL,"
                + "desc_senha VARCHAR(30) NOT NULL"
                + ")";      
    }      

    public static Usuario getUsuario(String login, String senha) throws Exception {
        Usuario user = null;
        Connection con = AppListener.getConnection();
        String sql = "SELECT rowid, * from USUARIO WHERE nm_login=? AND desc_senha=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, AppListener.getMd5Hash(senha));
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            long rowId = rs.getLong("rowid");
            int RA = rs.getInt("id_ra");
            String nome = rs.getString("nome");
            String curso = rs.getString("sg_curso");
            char semestre = rs.getString("qt_semestre").charAt(0);           
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }
    
        public static ArrayList<Usuario> getUsuario() throws Exception {
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM USUARIO");
        while (rs.next()) {
            String login = rs.getString("nm_login");
            String senha = rs.getString("desc_senha");
            lista.add(new Usuario(login, senha));
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }
        
        public static void inserirUsuario(String login, String senha) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO USUARIO (nm_login, desc_senha) "
                + "VALUES(?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        stmt.execute();
        stmt.close();
        con.close();
    }
        
        public static void deleteUsuario(int rowId) throws Exception{
        Connection con = AppListener.getConnection();
        String sql = "DELETE FROM USUARIO WHERE rowId = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, rowId);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
