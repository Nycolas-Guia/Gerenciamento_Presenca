package model; // Indicando o pacote

import java.sql.Connection; //Imports de bibliotecas nessessárias
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.AppListener;


public class Materia {
    
    private String sigla;
    private String nome;
    private String professor;
    private int ciclo;
    
    public static String getCreateStatement(){ // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS MATERIA("
                + "nm_materia VARCHAR(50) NOT NULL,"
                + "nm_professor VARCHAR(40) DEFAULT 'Nilson Silva',"
                + "sg_curso VARCHAR(5) UNIQUE NOT NULL, FOREIGN KEY (sg_curso) REFERENCES CURSO (sg_curso),"
                + "qt_semestre CHAR(1) UNIQUE NOT NULL, FOREIGN KEY (qt_semestre) REFERENCES SEMESTRE (qt_semestre)"
                + ")";
    }
    
    public static ArrayList<Materia> getMateria() throws Exception { // Função que mostra todas as materias dentro da tabela Materia
        ArrayList<Materia> lista = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT * FROM MATERIA"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela MATERIA            
            String materia = rs.getString("nm_materia"); // Cria uma variável para armazenar o curso do aluno na tabela
            String professor = rs.getString("nm_professor"); // Cria uma variável para armazenar o ra do aluno na tabela
            String sigla = rs.getString("sg_curso"); // Cria uma variável para armazenar o nome do aluno na tabela
            int semestre = rs.getInt("qt_semestre"); // Cria uma variável para armazenar o semestre atual do aluno na tabela
            lista.add(new Materia(materia, professor, sigla, semestre)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com os alunos
    }
    
    public static void inserirMateria(String nome, String professor, String sigla, int semestre)
                throws Exception { // Função para inserir novos alunos ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO MATERIA(nm_materia, nm_professor, sg_curso, qt_semestre) " 
                + "VALUES(?, ?, ?, ?)"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado
        stmt.setString(1, nome); // Seta o nome como segunda variável a ser inserida na tabela
        stmt.setString(2, professor); // Seta o curso como terceira variável a ser inserida na tabela
        stmt.setString(3, sigla); // Seta o RA como primeira variável a ser inserida na tabela
        stmt.setInt(4, semestre); // Seta o semestre atual como quarta variável a ser inserida na tabela
        stmt.execute(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }

    public Materia(String nome, String professor, String sigla, int ciclo) { // Construtor das variáveis existentes na classe
        this.nome = nome;
        this.professor = professor;
        this.sigla = sigla;
        this.ciclo = ciclo;
    }


    public int getCiclo() { // Getters e Setters das variáveis existentes na classe
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    
}
