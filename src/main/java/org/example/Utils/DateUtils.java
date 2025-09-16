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


    // LISTA TODOS OS PEDIDOS
    public static void listarTodosPedidos() {
        String sql = "SELECT id, cliente_id, data_pedido, volume_m3, peso_kg FROM Pedido";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Pedidos cadastrados ---");

            while (rs.next()) {
                int id = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                String dataPedido = rs.getString("data_pedido");
                double volume = rs.getDouble("volume_m3");
                double peso = rs.getDouble("peso_kg");

                System.out.printf("ID: %d | ClienteID: %d | DataPedido: %s | Volume_M3: %.2f | Peso_KG: %.2f%n",
                        id, clienteId, dataPedido, volume, peso);
            }
            System.out.println("----------------------------\n");

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos!");
            e.printStackTrace();
        }
    }


    // LISTA TODOS OS MOTORISTAS
    public static void listarTodosMotoristas() {
        String sql = "SELECT id, nome, cnh, veiculo, cidade_base FROM Motorista";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Motoristas cadastrados ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnh = rs.getString("cnh");
                String veiculo = rs.getString("veiculo");
                String cidadeBase = rs.getString("cidade_base");

                System.out.printf("ID: %d | Nome: %s | CNH: %s | Veículo: %s | Cidade Base: %s%n",
                        id, nome, cnh, veiculo, cidadeBase);
            }
            System.out.println("----------------------------\n");

        } catch (SQLException e) {
            System.out.println("Erro ao listar motoristas!");
            e.printStackTrace();
        }
    }
    }

