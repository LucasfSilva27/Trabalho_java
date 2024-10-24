
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lsilva
 */
public class LigaBD {
    
    public static Connection liga() throws SQLException{
        String url = "jdbc:mysql://192.168.12.49:3306/multibanco?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "admin";
        String pass = "admin";
        Connection con = null;
        con = DriverManager.getConnection(url,user,pass);
        return con;
    }
    public void inserDadosUser(String nome,int nif,int conta,int senha ) throws SQLException{
        String query = "INSERT INTO user(nome,nif,conta,senha) VALUES ('"+nome+"',"+nif+","+conta+","+senha+")";
        Connection con = liga();
        PreparedStatement ps = con.prepareStatement(query);
        ps.execute();
    }
    public void criarConta(String t_conta,String nome, int conta,float saldo ) throws SQLException {
    String query = "INSERT INTO nova_conta(tipo_conta,nome,conta,saldo) VALUES ('"+t_conta+"',"+nome+","+conta+","+saldo+")";
    Connection con = liga();
    PreparedStatement ps = con.prepareStatement(query);
    ps.execute();
  //  }

  
}

    ResultSet executa(String query) {
        
        return null;
        
    }
    public static void registarAtividade(String nomeUtilizador, String descricao, double valor, double saldo, int conta) {
        String sql = "INSERT INTO atividades (nome_utilizador, descricao, valor, saldo, conta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = liga();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUtilizador);
            stmt.setString(2, descricao);
            stmt.setDouble(3, valor);
            stmt.setDouble(4, saldo);
            stmt.setInt(5, conta);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar atividade: " + e.getMessage());
        }
    }
    public static  ResultSet pesquisaDados(int nomeUtilizador) throws SQLException {   
        
       // String query = "SELECT nome_utilizador, data_operacao, descricao, valor, saldo FROM atividades";
    String query = "SELECT nome_utilizador, data_operacao, descricao, valor, saldo FROM atividades WHERE conta = ?";
    Connection con = liga();
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, nomeUtilizador); // Define o valor do parâmetro `conta` na consulta
    ResultSet rs = (ResultSet) ps.executeQuery();
    return rs;
}
}
