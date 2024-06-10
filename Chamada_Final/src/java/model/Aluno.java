package model; // Indicando o pacote

import java.util.ArrayList; //Imports de bibliotecas nessessárias
import java.sql.*;
import web.AppListener;
import static web.AppListener.initializeLog;

public class Aluno {

    private long rowId; 
    private int RA;
    private String nome;       
    private String curso;
    private int semestre;
    

    public static String getCreateStatement() { // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS ALUNO("
                + "id_ra NUMERIC(13) UNIQUE NOT NULL,"
                + "nm_aluno VARCHAR(50) NOT NULL,"
                + "sg_curso VARCHAR(5) NOT NULL, FOREIGN KEY (sg_curso) REFERENCES CURSO (sg_curso),"
                + "qt_semestre CHAR(1) NOT NULL, FOREIGN KEY (qt_semestre) REFERENCES SEMESTRE (qt_semestre)"
                + ")";      
    }
    
    public static ArrayList<Aluno> getAluno() throws Exception { // Função que mostra todos os alunos dentro da tabela ALUNO
        ArrayList<Aluno> lista = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM ALUNO"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela ALUNO
            long rowId = rs.getLong("rowid"); // Cria uma variável para armazenar o rowid do aluno na tabela
            int RA = rs.getInt("id_ra"); // Cria uma variável para armazenar o ra do aluno na tabela
            String nome = rs.getString("nm_aluno"); // Cria uma variável para armazenar o nome do aluno na tabela
            String curso = rs.getString("sg_curso"); // Cria uma variável para armazenar o curso do aluno na tabela
            int semestre = rs.getInt("qt_semestre"); // Cria uma variável para armazenar o semestre atual do aluno na tabela
            lista.add(new Aluno(rowId, RA, nome, curso, semestre)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com os alunos
    }
        
        public static void inserirAluno(int RA, String nome, String curso, int semestre)
                throws Exception { // Função para inserir novos alunos ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO ALUNO(id_ra, nm_aluno, sg_curso, qt_semestre) " 
                + "VALUES(?, ?, ?, ?)"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setInt(1, RA); // Seta o RA como primeira variável a ser inserida na tabela
        stmt.setString(2, nome); // Seta o nome como segunda variável a ser inserida na tabela
        stmt.setString(3, curso); // Seta o curso como terceira variável a ser inserida na tabela
        stmt.setInt(4, semestre); // Seta o semestre atual como quarta variável a ser inserida na tabela
        stmt.execute(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }
        
        public static void updateAluno(int RA, String nome, String curso, int semestre)
                throws Exception { // Função para atualizar aluno que JÁ ESTÃO dentro da tabela
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "UPDATE ALUNO SET nm_aluno=?, sg_curso=?, qt_semestre=? WHERE id_ra=?"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, nome);  // Seta o nome como primeira variável a ser atualizada
        stmt.setString(2, curso); // Seta o curso como segunda variável a ser atualizada
        stmt.setInt(3, semestre); // Seta o semestre atual como terceira variável a ser atualizada
        stmt.setInt(4, RA); // Seta o RA como quarta variável, será o identificador do aluno
        stmt.execute(); // Executa os códigos acima
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }
    
    public static void deleteUsuario(int RA) throws Exception{ // Função que deleta Alunos da tabela
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "DELETE FROM ALUNO WHERE id_ra = ?"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setLong(1, RA); // Seta o RA como primeira variável, será o identificador do aluno
        stmt.execute(); // Executa os códigos acima
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }
    
    public Aluno(long rowId, int RA, String nome, String curso, int semestre) { // Construtor das variáveis existentes na classe
        this.rowId = rowId;
        this.RA = RA;
        this.nome = nome;       
        this.curso = curso;
        this.semestre = semestre;
    }

    public long getRowId() { // Getters e Setters das variáveis existentes na classe
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
    
    public int getRA() {
        return RA;
    }

    public void setRa(int RA) {
        this.RA = RA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }    

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }    

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

}
