import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
    



public class Transferencia1 {
    Menu_autenticacao ma = new Menu_autenticacao();
    
    
 

      
    // Método para transferir dinheiro entre contas
    public boolean transferir() throws SQLException {
       
        String contaOrigem = "34567890";   // Conta de Marco
        String senhaOrigem = "4321";        // Senha de Marco
        String contaDestino = "98765432";  // Conta de Vanessa
        double valorTransferencia = 10.0;    // Valor a ser transferido

        
         Connection conn = null;
        PreparedStatement verificarSaldoStmt = null;
        PreparedStatement subtrairSaldoStmt = null;
        PreparedStatement adicionarSaldoStmt = null;
        ResultSet rs = null;

        try {
            // Conectar ao banco de dados
           String url = "jdbc:mysql://192.168.12.49:3306/multibanco?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
           String user = "admin";
           String pass = "admin";
           DriverManager.getConnection(url,user,pass);

            // Verificar saldo da conta de origem
            String verificarSaldoSQL = "SELECT saldo FROM registro WHERE conta = ? AND senha = ?";
            verificarSaldoStmt = conn.prepareStatement(verificarSaldoSQL);
            verificarSaldoStmt.setString(1, contaOrigem);
            
            senhaOrigem = null;
            verificarSaldoStmt.setString(2, senhaOrigem);
            rs = verificarSaldoStmt.executeQuery();

            if (rs.next()) {
                double saldoOrigem = rs.getDouble("saldo");
               double valor = 0;

                if (saldoOrigem >= valor) {
                    // Subtrair o valor da conta de origem
                    String subtrairSaldoSQL = "UPDATE registro SET saldo = saldo - ? WHERE conta = ? AND senha = ?";
                    subtrairSaldoStmt = conn.prepareStatement(subtrairSaldoSQL);
                    subtrairSaldoStmt.setDouble(1, valor);
                    subtrairSaldoStmt.setString(2, contaOrigem);
                    subtrairSaldoStmt.setString(3, senhaOrigem);
                    subtrairSaldoStmt.executeUpdate();

                    // Adicionar o valor à conta de destino
                    String adicionarSaldoSQL = "UPDATE registro SET saldo = saldo + ? WHERE conta = ?";
                    adicionarSaldoStmt = conn.prepareStatement(adicionarSaldoSQL);
                    adicionarSaldoStmt.setDouble(1, valor);
                    adicionarSaldoStmt.setString(2, contaDestino);
                    adicionarSaldoStmt.executeUpdate();

                    // Confirmar transação
                    conn.commit();
                    System.out.println("Transferência realizada com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente na conta de origem.");
                }
            } else {
                System.out.println("Conta de origem não encontrada ou senha incorreta.");
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Desfazer transação em caso de erro
                    System.out.println("Transferência falhou. Transação desfeita.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Fechar recursos
            if (rs != null) rs.close();
            if (verificarSaldoStmt != null) verificarSaldoStmt.close();
            if (subtrairSaldoStmt != null) subtrairSaldoStmt.close();
            if (adicionarSaldoStmt != null) adicionarSaldoStmt.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    }
