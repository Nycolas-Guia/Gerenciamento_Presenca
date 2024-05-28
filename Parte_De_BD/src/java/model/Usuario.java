package model;

public class Usuario {

    public static Aluno getAluno(String login, String senha) throws Exception {
        Aluno user = null;
        Connection con = AppListener.getConnection();
        String sql = "SELECT rowid, * from users WHERE login=? AND senha_hash=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, AppListener.getMd5Hash(senha));
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            long rowId = rs.getLong("rowid");
            String nome = rs.getString("nome");
            char periodo = rs.getString("cargo").charAt(0);
            String senhaHash = rs.getString("senha_hash");
            user = new Aluno(rowId, login, nome, periodo, senhaHash);
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }
}
