package org.example.Utils;

import org.example.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DateUtils {


    // LISTA TODOS OS CLIENTES
    public static void listarTodosClientes() {
        String sql = "SELECT id, nome, cpf_cnpj, cidade, estado FROM Cliente";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Clientes cadastrados ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpfCnpj = rs.getString("cpf_cnpj");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                System.out.printf("ID: %d | Nome: %s | CPF/CNPJ: %s | Cidade: %s | Estado: %s%n",
                        id, nome, cpfCnpj, cidade, estado);
            }
            System.out.println("----------------------------\n");

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes!");
            e.printStackTrace();
        }
    }



}
