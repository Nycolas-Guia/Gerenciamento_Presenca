package model;

import java.sql.*;
import java.util.ArrayList;
import web.AppListener;

public class Course {
    private long rowId;
    private String abb;
    private String name;
    private String desc;

    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS courses("
                + "abb VARCHAR(5) UNIQUE NOT NULL,"
                + "name VARCHAR(50) NOT NULL,"
                + "desc VARCHAR(100)"
                + ")";
    }        
    
    public static ArrayList<Course> getCourses() throws Exception {
        ArrayList<Course> list = new ArrayList<>();
        Connection con = AppListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowid, * from courses");
        while (rs.next()) {
            long rowId = rs.getLong("rowid");
            String abb = rs.getString("abb");
            String name = rs.getString("name");
            String desc = rs.getString("desc");            
            list.add(new Course(rowId, abb, name, desc));
        }
        con.close();
        stmt.close();
        rs.close();
        return list;
    }
 
    public static void insertCourse(String abb, String name, String desc) throws Exception {
        Connection con = AppListener.getConnection();
        String sql = "INSERT INTO courses(abb, name, desc)"
                + "VALUES(?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, abb);
        stmt.setString(2, name);
        stmt.setString(3, desc);        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Course(long rowId, String abb, String name, String desc) {
        this.rowId = rowId;
        this.abb = abb;
        this.name = name;
        this.desc = desc;
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}