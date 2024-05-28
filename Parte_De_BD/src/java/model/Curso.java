package model;

import java.sql.*;
import java.util.ArrayList;
import web.AppListener;

public class Curso {
    private long rowId;
    private String sigla;
    private String nome;
    private String desc;

    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS curso("
                + "sg_curso VARCHAR(5) UNIQUE NOT NULL,"
                + "nm_curso VARCHAR(50) NOT NULL,"
                + "ds_curso VARCHAR(150)"
                + ")";
    }        
    
    public static ArrayList<Curso> getCurso() throws Exception {
        ArrayList<Curso> list = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * from curso");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            String sigla = rs.getString("sg_curso");
            String nome = rs.getString("nm_curso");
            String desc = rs.getString("ds_curso");            
            list.add(new Curso(rowId, sigla, nome, desc));
        }
        con.close();
        stmt.close();
        rs.close();
        return list;
    }
 
    public static void insertCurso(String sigla, String nome, String desc) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO curso(sg_curso, nm_curso, ds_curso)"
                + "VALUES(?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, sigla);
        stmt.setString(2, nome);
        stmt.setString(3, desc);        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Curso(long rowId, String sigla, String nome, String desc) {
        this.rowId = rowId;
        this.sigla = sigla;
        this.nome = nome;
        this.desc = desc;
    }

    public String getSigla() {
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