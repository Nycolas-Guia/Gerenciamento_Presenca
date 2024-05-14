package model;

import java.util.ArrayList;
import java.sql.*;
import web.AppListener;
import static web.AppListener.initializeLog;

public class User {

    private long rowId;
    private String nome;
    private String login;
    private String role; //SECR || CORD || PROF || ALUN
    private String senhaHash;

    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS user("
                + "login VARCHAR(50) UNIQUE NOT NULL,"
                + "nome VARCHAR(100) NOT NULL,"
                + "role VARCHAR (4) NOT NULL,"
                + "senha_hash VARCHAR NOT NULL"
                + ")";
        
    }
    

    public static ArrayList<User> getUsers() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM users");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            String login = rs.getString("login");
            String nome = rs.getString("nome");
            String role = rs.getString("role");
            String senhaHash = rs.getString("senha_hash");
            list.add(new User(rowId, login, nome, role, senhaHash));
        }
        con.close();
        stmt.close();
        rs.close();
        return list;
    }

    public static User getUser(String login, String senha) throws Exception {
        User user = null;
        Connection con = AppListener.getConnection();
        String sql = "SELECT rowid, * from users WHERE login=? AND senha_hash=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, AppListener.getMd5Hash(senha));
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            long rowId = rs.getLong("rowid");
            String nome = rs.getString("nome");
            String role = rs.getString("role");
            String senhaHash = rs.getString("senha_hash");
            user = new User(rowId, login, nome, role, senhaHash);
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }

    public static void insertUser(String login, String nome, String role, String senha) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO users(login, nome, role, senha_hash) "
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, nome);
        stmt.setString(3, role);
        stmt.setString(4, AppListener.getMd5Hash(senha));
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void updateUser(String login, String nome, String role, String senha) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "UPDATE users SET nome=?, role=?, senha_hash=? WHERE login=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, role);
        stmt.setString(3, AppListener.getMd5Hash(senha));
        stmt.setString(4, login);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void deleteUser(long rowId) throws Exception{
        Connection con = AppListener.getConnection();
        String sql = "DELETE FROM users WHERE rowid = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, rowId);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public User(long rowId,String login, String nome, String role, String senhaHash) {
        this.rowId = rowId;
        this.login = login;
        this.nome = nome;
        this.role = role;
        this.senhaHash = senhaHash;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return senhaHash;
    }

    public void setPasswordHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

}
