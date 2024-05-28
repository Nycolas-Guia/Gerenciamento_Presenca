package model;

import java.util.ArrayList;
import java.sql.*;
import web.AppListener;
import static web.AppListener.initializeLog;

public class Aluno {

    private long rowId;
    private int RA;
    private String nome;       
    private String curso;
    private int semestre;
    

    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS aluno("
                + "id_ra NUMERIC(13) UNIQUE NOT NULL,"
                + "nm_aluno VARCHAR(50) NOT NULL,"
                + "sg_curso VARCHAR(5) NOT NULL"
                + "qt_semestre CHAR(1) NOT NULL"
                + ")";      
    }
    

    public static ArrayList<Aluno> getAluno() throws Exception {
        ArrayList<Aluno> lista = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * FROM aluno");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            int RA = rs.getInt("id_ra");
            String nome = rs.getString("nm_aluno");
            String curso = rs.getString("sg_curso");
            int semestre = rs.getInt("qt_semestre");
            lista.add(new Aluno(rowId, RA, nome, curso, semestre));
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }

    
    public static void inserirAluno(int RA, String nome, String curso, char semestre) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO usuario(id_ra, nm_aluno, sg_curso, qt_semestre) "
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, RA);
        stmt.setString(2, nome);
        stmt.setString(3, curso);
        stmt.setString(4, String.valueOf(semestre));
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void updateAluno(int RA, String nome, String curso, char semestre) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "UPDATE usuario SET nm_aluno=?, sg_curso=?, qt_semestre=? WHERE id_ra=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, curso);
        stmt.setString(3, String.valueOf(semestre));
        stmt.setInt(4, RA);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void deleteUser(int RA) throws Exception{
        Connection con = AppListener.getConnection();
        String sql = "DELETE FROM users WHERE id_ra = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, RA);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Aluno(long rowId, int RA, String nome, String curso, int semestre) {
        this.rowId = rowId;
        this.RA = RA;
        this.nome = nome;       
        this.curso = curso;
        this.semestre = semestre;
    }

    public long getRowId() {
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
