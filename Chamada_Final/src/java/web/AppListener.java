package web; // Indicando o pacote

import jakarta.servlet.ServletContextEvent; //Imports de bibliotecas nessessárias
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
    public static final String CLASS_NAME = "org.sqlite.JDBC"; // Nome da classe dentro do SQLite
    public static final String URL = "jdbc:sqlite:callapp.db"; // A URL
    public static String initializeLog = ""; // Cria uma variável com uma atribuição vazia
    public static Exception exception = null; // Cria um objeto Exception nulo

    @Override
    public void contextInitialized(ServletContextEvent sce) { // Quando a classe for chamada irá iniciar essa função
        try{
            Connection c = AppListener.getConnection(); // Cria uma conexão com o banco
            Statement s = c.createStatement(); // Executa consultas dentro do banco de dados conectado
            initializeLog += new Date() + ": Iniciando criação do banco de dados;"; // Adiciona a variavel a data e um texto
            initializeLog += "Criando a tabela ALUNO caso não exista..."; // Adiciona um texto na variavel
            s.execute(Aluno.getCreateStatement()); // Executa a função getCreateStatement dentro da classe Aluno
            if(Aluno.getAluno().isEmpty()){ // Caso o array de alunos esteja vazio, esse if será acionado
                initializeLog += "Adicionando aluno padrão..."; // Adiciona um texto na variavel
                Aluno.inserirAluno(0000000000001, "Gustavo", "ADS", 1); // Adiciona um aluno
                initializeLog += "Conta criada; "; // Adiciona um texto na variavel
            }
            initializeLog += "Criando a tabela USUARIO caso não exista..."; // Adiciona um texto na variavel
            s.execute(Usuario.getCreateStatement()); // Executa a função getCreateStatement dentro da classe Usuario
            if(Usuario.getUsuario().isEmpty()){ // Caso o array de usuarios esteja vazio, esse if será acionado
                initializeLog += "Adicionando usuário padrão..."; // Adiciona um texto na variavel
                Usuario.inserirUsuario("Gustavo", "1234"); // Adiciona um usuario
                initializeLog += "Conta criada; "; // Adiciona um texto na variavel
            }
            initializeLog += "Criando a tabela CURSO caso não exista..."; // Adiciona um texto na variavel
            s.execute(Curso.getCreateStatement()); // Executa a função getCreateStatement dentro da classe Usuario
            if(Curso.getCurso().isEmpty()){ // Caso o array de cursos esteja vazio, esse if será acionado
                initializeLog += "Adicionando cursos padrão..."; // Adiciona um texto na variavel
                Curso.insertCurso("ADS", "Análise e Desenvolvimento de Sistemas", "Curso de Análise e Desenvolvimento de Sistemas. Voltado para análise e BackEnd."); // Adiciona um curso
                Curso.insertCurso("PQ", "Processos Químicos", "Curso de Química. Voltado para quem quer atuar na indústria petroquímica, eletroquímicaindústria petroquímica, eletroquímica, farmacêutica e de produção de insumos."); // Adiciona um curso
                Curso.insertCurso("COMEX", "Comércio Exterior", "Curso de Comércio Exterior. Voltado para quem gerencia operações de comércio exterior, tais como: transações cambiais, despacho e legislação aduaneira, exportação, importação, contratos e logística internacional."); // Adiciona um curso
                Curso.insertCurso("GE", "Gestão Empresarial", "Curso de Gestão de Empresas. Voltado para quem quer atuar como gestor em empresas de pequeno à grande porte ou seu próprio negócio."); // Adiciona um curso
                Curso.insertCurso("DSM", "Desenvolvimento de Software Multiplataforma", "Curso de Desenvolvimento. Voltado para o desenvolvimento de novas tecnologias, como IOT, Mobile, Inteligência Artificial, Nuvem, entre outros;."); // Adiciona um curso
                initializeLog += "Cursos adicionados; "; // Adiciona um texto na variavel
            }
            initializeLog += "Feito!\n"; // Adiciona um texto na variavel
            s.close(); // Fecha as conexões que foram criadas no começo da função
            c.close();
            
        }catch (Exception ex){ // Analisa se algum erro foi captado
            initializeLog += "Erro: " + ex.getMessage();  // Adiciona um texto na variavel, mostrando o erro
            exception = ex; // Adiciona o erro no objeto de Exception
        }
    }
    
    public static String getMd5Hash(String text) throws NoSuchAlgorithmException{ // Função que criará o hash da senha do usuário
        MessageDigest m = MessageDigest.getInstance("MD5"); // Pega uma linha da classe MessageDigest que implementa o algoritmo MD5. Gerando um hash de 128 bits
        m.update(text.getBytes(), 0, text.length()); // Converte o texto/senha em um array de bytes
        return new BigInteger(1, m.digest()).toString(); // Calcula o hash MD5 do texto atualizado e retorna o hash como string do número
    }
    
    public static Connection getConnection() throws Exception{ // Função que fará a conexão com o SQLite
        Class.forName(CLASS_NAME); // Registra a classe do driver como a variável definida no começo da classe
        return DriverManager.getConnection(URL); // Retorna uma coneção com o banco de dados
    }
    
    
}
