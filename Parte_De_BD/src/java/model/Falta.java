package model; // Indicando o pacote

import java.util.ArrayList; //Imports de bibliotecas nessessárias
import java.sql.*;
import web.*;

public class Falta {
    
    private int faltas;
    private int RA;
    
    public static String getCreateStatement() throws Exception{ // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS FALTA("
                + "qt_falta NUMERIC(3),"
                + "id_ra NUMERIC(13)"
                + ")";
    }
    
    public static ArrayList<Falta> getFalta() throws Exception { // Função que mostra as faltas dentro da tabela FALTAS
        ArrayList<Falta> lista = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT id_ra, * FROM FALTA"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela FALTA
            int RA = rs.getInt("id_ra"); // Cria uma variável para armazenar o rowid do aluno na tabela
            int faltas = rs.getInt("qt_falta"); // Cria uma variável para armazenar a quantidade de faltas do aluno na tabela
            lista.add(new Falta(RA, faltas)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com as faltas
    }
    
    public static void inserirFalta(int RA, int faltas) throws Exception { // Função para inserir novos faltas ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO FALTA(id_ra, qt_falta) "
                + "VALUES(?, ? + qt_falta)"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setInt(1, RA); // Seta o RA como primeira variável a ser inserida na tabela
        stmt.setInt(2, faltas); // Seta a falta como segunda variável a ser inserida na tabela
        stmt.execute(); // Fecha as conexões que foram criadas
        stmt.close();
        con.close();
    }
    
    public static void updateFalta(int faltas, int RA) throws Exception { // Função para inserir novos faltas ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "UPDATE FALTA SET qt_falta=? WHERE id_ra=?"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setInt(1, faltas); // Seta a falta como segunda variável a ser inserida na tabela
        stmt.setInt(2, RA); // Seta o RA como primeira variável a ser inserida na tabela
        stmt.execute(); // Fecha as conexões que foram criadas
        stmt.close();
        con.close();
    }

    public Falta(int faltas, int RA) { // Construtor das variáveis existentes na classe
        this.faltas = faltas;
        this.RA = RA;
    }

    public int getFaltas() { // Getters e Setters das variáveis existentes na classe
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getRA() {
        return RA;
    }

    public void setRA(int RA) {
        this.RA = RA;
    }
    
    
}
