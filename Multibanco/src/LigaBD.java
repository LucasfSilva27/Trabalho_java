
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
        String url = "jdbc:mysql://192.168.12.49:3306/multibanco?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "admin";
        String pass = "admin";
        Connection con = null;
        con = DriverManager.getConnection(url,user,pass);
        return con;
    }
    public void inserDadosUser(String nome,float saldo,int conta,int senha ) throws SQLException{
        String query = "INSERT INTO registro(nome,saldo,conta,senha) VALUES ('"+nome+"',"+saldo+","+conta+","+senha+")";
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

    void inserDadosUser(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
