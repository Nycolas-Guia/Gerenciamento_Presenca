package model;

public class Materia {
    
    private String sigla;
    private int ciclo;
    private String nome;
    private String professor;
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS MATERIA("
                + "sg_sigla VARCHAR(5) UNIQUE NOT NULL,"
                + "qt_semestre CHAR(1) UNIQUE NOT NULL,"
                + "nm_materia VARCHAR(50) NOT NULL,"
                + "nm_professor VARCHAR(40) DEFAULT 'Nilson Silva'"
                + ")";
    }

    public Materia(String sigla, int ciclo, String nome, String professor) {
        this.sigla = sigla;
        this.ciclo = ciclo;
        this.nome = nome;
        this.professor = professor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    
}
