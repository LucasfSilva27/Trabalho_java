
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lcosta
 */
public class LoginVerificador {
      // Método para verificar se a conta existe no banco de dados
    public static boolean verificarLogin(int numeroConta, int senha) {
        String sql = "SELECT * FROM registro WHERE conta = ? AND senha = ?";
        boolean loginValido = false;

        try (Connection liga = LigaBD.liga();
             PreparedStatement stmt = liga.prepareStatement(sql)) {

            // Definir os parâmetros da consulta SQL
            stmt.setInt(1, numeroConta);
            stmt.setInt(2, senha);

            // Executar a consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar se há resultados, ou seja, se a conta existe
            if (rs.next()) {
                loginValido = true;
                System.out.println("Login válido!");
            } else {
                System.out.println("Número da conta ou senha inválidos.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
        }

        return loginValido;
    }
    
public static double mostrarsaldo(int numeroConta) {
    String sqlSelect = "SELECT saldo FROM registro WHERE conta = ?";
    double saldoAtual = 0;
    
    try (Connection liga = LigaBD.liga()) {
        if (liga == null) {
            System.out.println("Erro: Conexão com o banco de dados falhou.");
            return saldoAtual; // Retorna 0 se a conexão falhar
        }
        // Primeiro, buscar o saldo atual da conta
        
        try (PreparedStatement stmtSelect = liga.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, numeroConta);
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                saldoAtual = rs.getDouble("saldo");
                System.out.println("Saldo atual: " + saldoAtual);
                return saldoAtual;
            } else {
                System.out.println("Conta não encontrada.");
                return saldoAtual; // Retorna 0 se a conta não for encontrada
            }
        }
    }   catch (SQLException ex) {
            Logger.getLogger(LoginVerificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       
}
public static double alterarSaldo(int numeroConta, double deposito) {
    String sqlSelect = "SELECT saldo FROM registro WHERE conta = ?";
    String sqlUpdate = "UPDATE registro SET saldo = ? WHERE conta = ?";
    double saldoAtualizado = 0;

    try (Connection liga = LigaBD.liga()) {
        if (liga == null) {
            System.out.println("Erro: Conexão com o banco de dados falhou.");
            return saldoAtualizado; // Retorna 0 se a conexão falhar
        }

        // Validar o depósito
        if (deposito <= 0) {
            System.out.println("O valor do depósito deve ser maior que zero.");
            return saldoAtualizado; // Retorna 0 se o depósito não for válido
        }

        // Primeiro, buscar o saldo atual da conta
        double saldoAtual = 0;
        try (PreparedStatement stmtSelect = liga.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, numeroConta);
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                saldoAtual = rs.getDouble("saldo");
                System.out.println("Saldo atual: " + saldoAtual);
            } else {
                System.out.println("Conta não encontrada.");
                return saldoAtualizado; // Retorna 0 se a conta não for encontrada
            }
        }

        // Agora, somar o depósito ao saldo atual
        saldoAtualizado = saldoAtual + deposito;

        // Atualizar o saldo no banco de dados
        try (PreparedStatement stmtUpdate = liga.prepareStatement(sqlUpdate)) {
            stmtUpdate.setDouble(1, saldoAtualizado);
            stmtUpdate.setInt(2, numeroConta);
            int rowsAffected = stmtUpdate.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Saldo atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o saldo.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar o saldo: " + e.getMessage());
    }

    return saldoAtualizado; // Retorna o saldo atualizado
}
public static double sacarSaldo(int numeroConta, double valorSaque) {
    String sqlSelect = "SELECT saldo FROM registro WHERE conta = ?";
    String sqlUpdate = "UPDATE registro SET saldo = ? WHERE conta = ?";
    double saldoAtualizado = 0;

    try (Connection liga = LigaBD.liga()) {
        if (liga == null) {
            System.out.println("Erro: Conexão com o banco de dados falhou.");
            return saldoAtualizado; // Retorna 0 se a conexão falhar
        }

        // Validar o valor do saque
        if (valorSaque <= 0) {
            System.out.println("O valor do saque deve ser maior que zero.");
            return saldoAtualizado; // Retorna 0 se o valor do saque não for válido
        }

        // Primeiro, buscar o saldo atual da conta
        double saldoAtual = 0;
        try (PreparedStatement stmtSelect = liga.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, numeroConta);
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                saldoAtual = rs.getDouble("saldo");
                System.out.println("Saldo atual: " + saldoAtual);
            } else {
                System.out.println("Conta não encontrada.");
                return saldoAtualizado; // Retorna 0 se a conta não for encontrada
            }
        }

        // Verificar se há saldo suficiente para o saque
        if (saldoAtual < valorSaque) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return saldoAtualizado; // Retorna 0 se não houver saldo suficiente
        }

        // Subtrair o valor do saque do saldo atual
        saldoAtualizado = saldoAtual - valorSaque;

        // Atualizar o saldo no banco de dados
        try (PreparedStatement stmtUpdate = liga.prepareStatement(sqlUpdate)) {
            stmtUpdate.setDouble(1, saldoAtualizado);
            stmtUpdate.setInt(2, numeroConta);
            int rowsAffected = stmtUpdate.executeUpdate();

            if (rowsAffected > 0) {
                System.o-ut.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o saldo.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Erro ao realizar o saque: " + e.getMessage());
    }

    return saldoAtualizado; // Retorna o saldo atualizado após o saque
}
}
