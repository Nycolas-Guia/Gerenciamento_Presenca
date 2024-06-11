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
    public static final String URL = "jdbc:sqlite:chamada.db"; // A URL
    public static String initializeLog = ""; // Cria uma variável com uma atribuição vazia
    public static Exception exception = null; // Cria um objeto Exception nulo

    @Override
    public void contextInitialized(ServletContextEvent sce) { // Quando a classe for chamada irá iniciar essa função
        try{
            Connection c = AppListener.getConnection(); // Cria uma conexão com o banco                                    
            Statement s = c.createStatement(); // Executa consultas dentro do banco de dados conectado

            /*s.execute("DELETE FROM CURSO");
            s.execute("DELETE FROM SEMESTRE");
            s.execute("DELETE FROM USUARIO");
            s.execute("DELETE FROM MATERIA");
            s.execute("DELETE FROM FALTA");
            s.execute("DELETE FROM ALUNO");
            /**/

            initializeLog += new Date() + ": Iniciando criação do banco de dados;"; // Adiciona a variavel a data e um texto            
            
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
            
            s.execute(Semestre.getCreateStatement());
            if(Semestre.getSemestre().isEmpty()){
                Semestre.inserirSemestre(1);
                Semestre.inserirSemestre(2);
                Semestre.inserirSemestre(3);
                Semestre.inserirSemestre(4);
                Semestre.inserirSemestre(5);
                Semestre.inserirSemestre(6);
            }                        
            
            initializeLog += "Criando a tabela USUARIO caso não exista..."; // Adiciona um texto na variavel
            s.execute(Usuario.getCreateStatement()); // Executa a função getCreateStatement dentro da classe Usuario
            if(Usuario.getUsuario().isEmpty()){ // Caso o array de usuarios esteja vazio, esse if será acionado
                initializeLog += "Adicionando usuário padrão..."; // Adiciona um texto na variavel
                Usuario.inserirUsuario("Pupo", "1234"); // Adiciona um usuario
                initializeLog += "Conta criada; "; // Adiciona um texto na variavel
            }
            
            s.execute(Materia.getCreateStatement());            
            if(Materia.getMateria().isEmpty()){                
                Materia.inserirMateria("Programação Orientada a Objeto", "Ricardo Pupo", "ADS", 4);
                Materia.inserirMateria("Estrutura de Dados", "Ricardo Pupo", "DSM", 2);
            }
            
            initializeLog += "Criando a tabela ALUNO caso não exista..."; // Adiciona um texto na variavel
            s.execute(Aluno.getCreateStatement()); // Executa a função getCreateStatement dentro da classe Aluno            
            s.execute(Falta.getCreateStatement());
            if(Aluno.getAluno().isEmpty()){ // Caso o array de alunos esteja vazio, esse if será acionado
                initializeLog += "Adicionando aluno padrão..."; // Adiciona um texto na variavel
                Aluno.inserirAluno(0000000000001, "Lucas Ferreira", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000001, 2);
                Aluno.inserirAluno(0000000000002, "Maria Silva", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000002, 0);
                Aluno.inserirAluno(0000000000003, "Pedro Souza", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000003, 0);
                Aluno.inserirAluno(0000000000004, "Ana Costa", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000004, 0);
                Aluno.inserirAluno(0000000000005, "João Oliveira", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000005, 0);
                Aluno.inserirAluno(0000000000006, "Laura Pereira", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000006, 0);
                Aluno.inserirAluno(0000000000007, "Gabriel Lima", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000007, 0);
                Aluno.inserirAluno(0000000000010, "Júlia Gomes", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000010, 0);
                Aluno.inserirAluno(0000000000011, "Carlos Rocha", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000011, 0);
                Aluno.inserirAluno(0000000000012, "Fernanda Santos", "ADS", 4); // Adiciona um aluno
                Falta.inserirFalta(0000000000012, 0);
                Aluno.inserirAluno(0000000000013, "Bruno Almeida", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000013, 0);
                Aluno.inserirAluno(0000000000014, "Mariana Melo", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000014, 0);
                Aluno.inserirAluno(0000000000015, "Rafael Barbosa", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000015, 0);
                Aluno.inserirAluno(0000000000016, "Beatriz Moreira", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000016, 0);
                Aluno.inserirAluno(0000000000017, "Daniel Araújo", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000017, 0);
                Aluno.inserirAluno(0000000000020, "Camila Ribeiro", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000020, 0);
                Aluno.inserirAluno(0000000000021, "Felipe Martins", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000021, 0);
                Aluno.inserirAluno(0000000000022, "Letícia Nascimento", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000022, 0);
                Aluno.inserirAluno(0000000000023, "Thiago Correia", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000023, 0);
                Aluno.inserirAluno(0000000000024, "Sofia Carvalho", "DSM", 2); // Adiciona um aluno
                Falta.inserirFalta(0000000000024, 0);
                initializeLog += "Contas criada; "; // Adiciona um texto na variavel
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
