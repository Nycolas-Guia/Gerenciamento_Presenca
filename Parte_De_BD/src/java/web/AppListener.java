package web;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.sql.*;
import model.*;

@WebListener
public class AppListener implements ServletContextListener{
    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:callapp.db";
    public static String initializeLog = "";
    public static Exception exception = null;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            Connection c = AppListener.getConnection();
            Statement s = c.createStatement();
            initializeLog += new Date() + ": Iniciando criação do banco de dados;";
            initializeLog += "Criando a tabela users caso não exista...";            
            s.execute(User.getCreateStatement());
            if(User.getUsers().isEmpty()){
                initializeLog += "Adicionando usuários padrão...";
                User.insertUser("Admin", "administrador", "ADMIN", "1234");
                initializeLog += "Admin adicionado; ";
            }
            initializeLog += "Criando a tabela courses caso não exista...";
            s.execute(Course.getCreateStatement());            
            if(Course.getCourses().isEmpty()){
                initializeLog += "Adicionando cursos padrão...";
                Course.insertCourse("ADS", "Análise e Desenvolvimento de Sistemas", "Curso de Análise e Desenvolvimento de Sistemas. Voltado para análise e BackEnd.");
                Course.insertCourse("PQ", "Processos Químicos", "Curso de Química. Voltado para quem quer atuar na indústria petroquímica, eletroquímicaindústria petroquímica, eletroquímica, farmacêutica e de produção de insumos.");
                Course.insertCourse("COMEX", "Comércio Exterior", "Curso de Comércio Exterior. Voltado para quem gerencia operações de comércio exterior, tais como: transações cambiais, despacho e legislação aduaneira, exportação, importação, contratos e logística internacional.");
                Course.insertCourse("GE", "Gestão Empresarial", "Curso de Gestão de Empresas. Voltado para quem quer atuar como gestor em empresas de pequeno à grande porte ou seu próprio negócio.");
                Course.insertCourse("DSM", "Desenvolvimento de Software Multiplataforma", "Curso de Desenvolvimento. Voltado para o desenvolvimento de novas tecnologias, como IOT, Mobile, Inteligência Artificial, Nuvem, entre outros;.");
                initializeLog += "Cursos adicionados; ";
            }
            initializeLog += "Feito!\n";
            s.close();
            c.close();
            
        }catch (Exception ex){
            initializeLog += "Erro: " + ex.getMessage();
            exception = ex;
        }
    }
    
    public static String getMd5Hash(String text) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(text.getBytes(), 0, text.length());
        return new BigInteger(1, m.digest()).toString();
    }
    
    public static Connection getConnection() throws Exception{
        Class.forName(CLASS_NAME);
        return DriverManager.getConnection(URL);
    }
    
    
}
