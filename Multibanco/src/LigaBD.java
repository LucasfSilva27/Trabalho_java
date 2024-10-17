
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        String url = "jdbc:mysql://localhost:3306/rh?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";
        
        Connection con = null;
        con = DriverManager.getConnection(url,user,pass);
        return con;
    }
    public void inserDadosUser(int conta,String nome,String senha,float saldo ) throws SQLException{
        String query = "INSERT INTO registro(conta,nome,login,senha) VALUES ('"+conta+"','"+nome+"','"+senha+"',"+saldo+")";
        Connection con = liga();
        PreparedStatement ps = con.prepareStatement(query);
        ps.execute();
    }
  //  public static Resultset pesquisaDados() throws SQLException {
  //  String query = "SELECT * FROM user";
  // Connection con = liga();
  //  PreparedStatement ps = con.prepareStatement(query);
  //  ResultSet rs = ps.executeQuery();
  //  return (Resultset) rs;
  //  }
}
