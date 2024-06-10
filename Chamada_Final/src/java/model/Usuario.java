package model; // Indicando o pacote

import java.sql.*; //Imports de bibliotecas nessessárias
import java.util.ArrayList;
import web.*;

public class Usuario {

    private long rowId;
    private String login;
    private String senha;
    
    public static String getCreateStatement() { // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS USUARIO("
                + "nm_login VARCHAR(50) UNIQUE NOT NULL,"
                + "nm_senha VARCHAR NOT NULL"
                + ")";
    }

    public static ArrayList<Usuario> getUsuario() throws Exception { // Função que mostra todos os usuarios dentro da tabela USUARIO
        ArrayList<Usuario> lista = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM USUARIO"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela USUARIO
            long rowId = rs.getLong("rowid"); // Cria uma variável para armazenar o rowid do usuario na tabela
            String login = rs.getString("nm_login"); // Cria uma variável para armazenar o nome do usuario na tabela
            String senha = rs.getString("nm_senha"); // Cria uma variável para armazenar a senha do usuario na tabela
            lista.add(new Usuario(rowId, login, senha)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com os usuarios
    }

    public static Usuario getUsuario(String login, String senha) throws Exception { // Função de checagem de login
        Usuario user = null; // Cria um objeto com valor nulo
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "SELECT rowid, * from USUARIO WHERE nm_login=? AND nm_senha=?"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, login); // Seta o login como primeira variável a ser inserida na tabela
        stmt.setString(2, AppListener.getMd5Hash(senha)); // Seta o hash da senha como segunda variável a ser inserida na tabela
        ResultSet rs = stmt.executeQuery(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        if (rs.next()) { // Condicional que só será acessado caso os logins e senhas acima se igualar com algum armazenado na tabela
            long rowId = rs.getLong("rowid"); // Cria uma variável para armazenar o rowid do usuario na tabela
            String nome = rs.getString("nm_login"); // Cria uma variável para armazenar o nome do usuario na tabela
            String pass = rs.getString("nm_senha"); // Cria uma variável para armazenar a senha do aluno na tabela
            user = new Usuario(rowId, nome, pass); // Adiciona as variáveis dentro array
        }
        rs.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        con.close();
        return user; // Retorna a lista com os usuarios
    }

    public static void inserirUsuario(String login, String senha) throws Exception { // Função para inserir novos usuarios ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO USUARIO (nm_login, nm_senha) " 
                + "VALUES(?, ?)";// Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, login); // Seta o login como primeira variável a ser inserido na tabela
        stmt.setString(2, AppListener.getMd5Hash(senha)); // Seta o hash da senha como primeira variável a ser inserida na tabela
        stmt.execute(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }

    public static void deleteUsuario(String rowId) throws Exception { // Função que deleta Alunos da tabela
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "DELETE FROM USUARIO WHERE nm_login = ?"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, rowId); // Seta o rowid como primeira variável, será o identificador do usuario
        stmt.execute(); // Executa os códigos acima
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }

    public Usuario(long rowId, String login, String senha) { // Construtor das variáveis existentes na classe
        this.rowId = rowId;
        this.login = login;
        this.senha = senha;
    }

    public long getRowId() { // Getters e Setters das variáveis existentes na classe
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
