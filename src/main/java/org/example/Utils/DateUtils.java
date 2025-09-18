package org.example.Utils;

import org.example.Conexao;
import org.example.Dao.EntregaDAO;
import org.example.Model.Entrega;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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


    // LISTAR TODAS AS ENTREGAS
    public static void listarTodasEntregas() {
        EntregaDAO entregaDAO = new EntregaDAO();

        try {
            List<Entrega> entregas = entregaDAO.listarTodasEntregas();

            if (entregas == null || entregas.isEmpty()) {
                System.out.println("\nNenhuma entrega encontrada!");
                return;
            }

            System.out.println("\n-------- Lista de Todas as Entregas --------\n");

            for (Entrega entrega : entregas) {
                System.out.println(
                        "ID: " + entrega.getId() +
                                " | Pedido ID: " + entrega.getPedido_id() +
                                " | Motorista ID: " + entrega.getMotorista_id() +
                                " | Data Saída: " + entrega.getData_saida() +
                                " | Data Entrega Prevista: " + entrega.getData_entrega() +
                                " | Status: " + entrega.getStatus()
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar entregas!");
            e.printStackTrace();
        }
    }


    // LISTAR TODAS AS ENTREGAS COM CLIENTE E MOTORISTA
    public static void listarEntregasComClienteEMotorista() {
        String sql = "SELECT e.id AS entrega_id, e.data_saida, e.data_entrega, e.status, " +
                "c.nome AS cliente_nome, c.cidade AS cliente_cidade, " +
                "m.nome AS motorista_nome, m.veiculo AS motorista_veiculo " +
                "FROM Entrega e " +
                "JOIN Pedido p ON e.pedido_id = p.id " +
                "JOIN Cliente c ON p.cliente_id = c.id " +
                "JOIN Motorista m ON e.motorista_id = m.id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n\n--- Entregas com Cliente e Motorista ---\n\n");

            while (rs.next()) {
                System.out.println(
                        "Entrega ID: " + rs.getInt("entrega_id") +
                                " | Status: " + rs.getString("status") +
                                " | Data Saída: " + rs.getString("data_saida") +
                                " | Data Entrega: " + rs.getString("data_entrega") +
                                " | Cliente: " + rs.getString("cliente_nome") +
                                " (" + rs.getString("cliente_cidade") + ")" +
                                " | Motorista: " + rs.getString("motorista_nome") +
                                " (" + rs.getString("motorista_veiculo") + ")"
                );
            }

        } catch (SQLException e) {
            System.out.println("\n\nErro ao listar entregas com cliente e motorista!");
            e.printStackTrace();
        }
    }


}

