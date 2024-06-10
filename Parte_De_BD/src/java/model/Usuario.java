package model;

import java.sql.*;
import java.util.ArrayList;
import web.*;

public class Usuario {

    private long rowId;
    private String login;
    private String senha;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS USUARIO("
                + "nm_login VARCHAR(50) UNIQUE NOT NULL,"
                + "desc_senha VARCHAR NOT NULL"
                + ")";
    }

    public static ArrayList<Usuario> getUsuario() throws Exception {
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM USUARIO");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            String login = rs.getString("nm_login");
            String senha = rs.getString("desc_senha");
            lista.add(new Usuario(rowId, login, senha));
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
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
            String nome = rs.getString("nm_login");
            String pass = rs.getString("desc_senha");
            user = new Usuario(rowId, nome, pass);
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }

    public static void inserirUsuario(String login, String senha) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO USUARIO (nm_login, desc_senha) "
                + "VALUES(?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, AppListener.getMd5Hash(senha));
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void deleteUsuario(String rowId) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "DELETE FROM USUARIO WHERE nm_login = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, rowId);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Usuario(long rowId, String login, String senha) {
        this.rowId = rowId;
        this.login = login;
        this.senha = senha;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
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
