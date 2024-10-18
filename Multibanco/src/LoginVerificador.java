
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    public boolean verificarLogin(int numeroConta, int senha) {
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
}
