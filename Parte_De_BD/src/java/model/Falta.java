package model;

import java.util.ArrayList;
import java.sql.*;
import web.*;

public class Falta {
    
    private int faltas;
    private int RA;
    
    public static String getCreateStatement() throws Exception{
        return "CREATE TABLE IF NOT EXISTS FALTA("
                + "qt_falta NUMERIC(3),"
                + "id_ra NUMERIC(13) UNIQUE NOT NULL"
                + ")";
    }
    
    public static ArrayList<Falta> getFalta() throws Exception {
        ArrayList<Falta> lista = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id_ra, * FROM FALTA");
        while (rs.next()) {            
            int RA = rs.getInt("id_ra");
            int faltas = rs.getInt("qt_falta");            
            lista.add(new Falta(RA, faltas));
        }
        con.close();
        stmt.close();
        rs.close();
        return lista;
    }
    
    public static void inserirFalta(int RA, int faltas) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO FALTA(id_ra, qt_falta) "
                + "VALUES(?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, RA);
        stmt.setInt(2, faltas);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Falta(int faltas, int RA) {
        this.faltas = faltas;

        this.RA = RA;
    }

    public int getFaltas() {
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
