package model; // Indicando o pacote

import java.sql.*; //Imports de bibliotecas nessessárias
import web.AppListener;
import java.util.ArrayList;
import static web.AppListener.initializeLog;

public class Semestre {
    
    private long rowId;
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
        ResultSet rs = stmt.executeQuery("Select rowId, * FROM SEMESTRE"); // Executa o código SQL entre parenteses
        while (rs.next()) { // while que funcionará até que não tenha mais colunas dentro da tabela FALTA
            long rowId = rs.getLong("rowid"); // Cria uma variável para armazenar o rowid do aluno na tabela
            int semestre = rs.getInt("qt_semestre"); // Cria uma variável para armazenar a quantidade de semestres na tabela
            lista.add(new Semestre(rowId, semestre)); // Adiciona as variáveis dentro array
        }
        con.close(); // Fecha as conexões que foram criadas no começo da função
        stmt.close();
        rs.close();
        return lista; // Retorna a lista com os semestres
    }

    public Semestre(long rowId, int semestre) { // Construtor das variáveis existentes na classe
        this.rowId = rowId;
        this.semestre = semestre;
    }

    public long getRowId() { // Getters e Setters das variáveis existentes na classe
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public int getCiclo() {
        return semestre;
    }

    public void setCiclo(int semestre) {
        this.semestre = semestre;
    }
    
    
    
}
