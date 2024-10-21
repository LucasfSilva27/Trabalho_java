
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
