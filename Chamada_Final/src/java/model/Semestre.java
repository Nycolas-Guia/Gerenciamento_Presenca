package model; // Indicando o pacote

import java.sql.*; //Imports de bibliotecas nessessárias
import web.AppListener;
import java.util.ArrayList;
import static web.AppListener.initializeLog;

public class Semestre {
        
    private int semestre;
    
    public static String getCreateStatement(){ // Função que cria a tabela caso não exista
        return "CREATE TABLE IF NOT EXISTS SEMESTRE("
                + "qt_semestre CHAR(1) UNIQUE NOT NULL"
                + ")";
    }
    
    public static ArrayList<Semestre> getSemestre() throws Exception{ // Função que mostra os semestres dentro da tabela SEMESTRE
        ArrayList<Semestre> lista = new ArrayList<>(); // Cria um array
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        Statement stmt = con.createStatement(); // Executa consultas dentro do banco de dados conectado
        ResultSet rs = stmt.executeQuery("SELECT * FROM SEMESTRE"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela FALTA
            int semestre = rs.getInt("qt_semestre"); // Cria uma variável para armazenar a quantidade de semestres na tabela
            lista.add(new Semestre(semestre)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com os semestres
    }
    
    public static void inserirSemestre(int semestre)
                throws Exception { // Função para inserir novos alunos ao banco
        Connection con = AppListener.getConnection(); // Cria uma conexão com o banco
        String sql = "INSERT INTO SEMESTRE(qt_semestre) "
                + "VALUES(?)"; // Cria uma variavel com um código sql dentro
        PreparedStatement stmt = con.prepareStatement(sql); // Prepara o código SQL para ser executado 
        stmt.setInt(1, semestre); // Seta os semestre como quarta variável a ser inserida na tabela
        stmt.execute(); // Executa os códigos acima, inserindo as variáveis dentro da tabela
        stmt.close(); // Fecha as conexões que foram criadas
        con.close();
    }

    public Semestre(int semestre) { // Construtor das variáveis existentes na classe
        this.semestre = semestre;
    }

    public int getCiclo() {
        return semestre;
    }

    public void setCiclo(int semestre) {
        this.semestre = semestre;
    }
    
    
    
}
