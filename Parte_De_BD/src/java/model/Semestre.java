package model;

import static web.AppListener.initializeLog;
import java.sql.*;
import web.AppListener;
import java.util.ArrayList;

public class Semestre {
    
    private long rowId;
    private int semestre;
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS semstre ("
                + "qt_semestre CHAR(1) UNIQUE NOT NULL"
                + ")";
    }
    
    public static ArrayList<Semestre> getSemestre() throws Exception{
        ArrayList<Semestre> lista = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select rowId, * FROM semestre");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            int semestre = rs.getInt("qt_semestre");
            lista.add(new Semestre(rowId, semestre));
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }

    public Semestre(long rowId, int semestre) {
        this.rowId = rowId;
        this.semestre = semestre;
    }

    public long getRowId() {
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
