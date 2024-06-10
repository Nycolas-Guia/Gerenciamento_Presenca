package model;  // Indicando o pacote

import java.sql.*; //Imports de bibliotecas nessessárias
import java.util.ArrayList;
import web.AppListener;

public class Curso {
    private long rowId;
    private String sigla;
    private String nome;
    private String desc;

    public static String getCreateStatement(){ // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS CURSO("
                + "sg_curso VARCHAR(5) UNIQUE NOT NULL,"
                + "nm_curso VARCHAR(50) NOT NULL,"
                + "ds_curso VARCHAR(150)"
                + ")";
    }        
    
    public static ArrayList<Curso> getCurso() throws Exception { // Função que mostra todos os cursos dentro da tabela CURSO
        ArrayList<Curso> list = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT rowid, * from CURSO"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela CURSO
            long rowId = rs.getLong("rowid"); // Cria uma variável para armazenar o rowid do curso na tabela
            String sigla = rs.getString("sg_curso"); // Cria uma variável para armazenar a sigla do curso na tabela
            String nome = rs.getString("nm_curso"); // Cria uma variável para armazenar o nome do curso na tabela
            String desc = rs.getString("ds_curso");  // Cria uma variável para armazenar a descrição do curso na tabela           
            list.add(new Curso(rowId, sigla, nome, desc)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return list; // Retorna a lista com os cursos
    }
 
    public static void insertCurso(String sigla, String nome, String desc) throws Exception { // Função para inserir novos cursos ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO CURSO(sg_curso, nm_curso, ds_curso)"  // Cria uma variavel com um código sql dentro
                + "VALUES(?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, sigla); // Seta a sigla como primeira variável a ser inserida na tabela
        stmt.setString(2, nome); // Seta o nome como segunda variável a ser inserida na tabela
        stmt.setString(3, desc); // Seta a descrição como terceira variável a ser inserida na tabela
        stmt.execute(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }

    public Curso(long rowId, String sigla, String nome, String desc) { // Construtor das variáveis existentes na classe
        this.rowId = rowId;
        this.sigla = sigla;
        this.nome = nome;
        this.desc = desc;
    }

    public String getSigla() {  // Getters e Setters das variáveis existentes na classe
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}