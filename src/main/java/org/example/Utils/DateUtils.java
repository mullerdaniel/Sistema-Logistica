package org.example.Utils;

import org.example.Conexao;
import org.example.Dao.EntregaDAO;
import org.example.Model.Entrega;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

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
        String query = "SELECT e.id AS entrega_id, e.data_saida, e.data_entrega, e.status, " +
                "c.nome AS cliente_nome, c.cidade AS cliente_cidade, " +
                "m.nome AS motorista_nome, m.veiculo AS motorista_veiculo " +
                "FROM Entrega e " +
                "JOIN Pedido p ON e.pedido_id = p.id " +
                "JOIN Cliente c ON p.cliente_id = c.id " +
                "JOIN Motorista m ON e.motorista_id = m.id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

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


    // RELATORIO DE TOTAL DE ENTREGAS POR MOTORISTA
    public static void relatorioTotalEntregasPorMotorista() {
        String query = "SELECT m.nome AS motorista_nome, COUNT(e.id) AS total_entregas " +
                "FROM Motorista m " +
                "LEFT JOIN Entrega e ON m.id = e.motorista_id " +
                "GROUP BY m.nome";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n\n--- Total de Entregas por Motorista ---\n\n");

            while (rs.next()) {
                String motoristaNome = rs.getString("motorista_nome");
                int totalEntregas = rs.getInt("total_entregas");

                System.out.println("Motorista: " + motoristaNome + " | Total de Entregas: " + totalEntregas);
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao gerar relatório de entregas por motorista!");
            e.printStackTrace();
        }
    }


    // RELATORIO COM CLIENTES COM MAIOR VOLUME ENTREGUE
    public static void relatorioClientesMaiorVolume() {
        String query = "SELECT c.nome AS cliente_nome, SUM(p.volume_m3) AS total_volume " +
                "FROM Cliente c " +
                "JOIN Pedido p ON c.id = p.cliente_id " +
                "JOIN Entrega e ON p.id = e.pedido_id " +
                "WHERE e.status = 'ENTREGUE' " +
                "GROUP BY c.nome " +
                "ORDER BY total_volume DESC";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n\n--- Clientes com Maior Volume Entregue ---\n\n");

            while (rs.next()) {
                String clienteNome = rs.getString("cliente_nome");
                double totalVolume = rs.getDouble("total_volume");

                System.out.println("Cliente: " + clienteNome + " | Total Volume Entregue: " + totalVolume + " m³");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao gerar relatório de clientes com maior volume!");
            e.printStackTrace();
        }
    }


    // RELATORIO DE PEDIDOS PENDENTES POR ESTADO
    public static void relatorioPedidosPendentesPorEstado() {
        String query = "SELECT c.estado AS estado, COUNT(p.id) AS total_pendentes " +
                "FROM Cliente c " +
                "JOIN Pedido p ON c.id = p.cliente_id " +
                "JOIN Entrega e ON p.id = e.pedido_id " +
                "WHERE e.status <> 'ENTREGUE' " +
                "GROUP BY c.estado " +
                "ORDER BY c.estado";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n\n--- Pedidos Pendentes por Estado ---\n\n");

            while (rs.next()) {
                String estado = rs.getString("estado");
                int totalPendentes = rs.getInt("total_pendentes");

                System.out.println("Estado: " + estado + " | Total de Pedidos Pendentes: " + totalPendentes);
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao gerar relatório de pedidos pendentes por estado!");
            e.printStackTrace();
        }
    }


    // RELATORIO ENTREGAS ATRASADAS POR CIDADE
    public static void relatorioEntregasAtrasadasPorCidade() {
        String query = "SELECT c.cidade AS cidade, COUNT(e.id) AS total_atrasadas " +
                "FROM Entrega e " +
                "JOIN Pedido p ON e.pedido_id = p.id " +
                "JOIN Cliente c ON p.cliente_id = c.id " +
                "WHERE e.status = 'ATRASADA' " +
                "GROUP BY c.cidade " +
                "ORDER BY total_atrasadas DESC";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n\n--- Entregas Atrasadas por Cidade ---\n\n");

            while (rs.next()) {
                String cidade = rs.getString("cidade");
                int totalAtrasadas = rs.getInt("total_atrasadas");

                System.out.println("Cidade: " + cidade + " | Total de Entregas Atrasadas: " + totalAtrasadas);
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao gerar relatório de entregas atrasadas por cidade!");
            e.printStackTrace();
        }
    }


    // BUSCAR PEDIDO POR CPF/CNPJ DO CLIENTE
    public static void buscarPedidoPorCpfouCnpj() {
        Scanner input = new Scanner(System.in);

        listarTodosClientes();
        System.out.print("\nDigite o CPF ou CNPJ do cliente: ");
        String cpfCnpj = input.nextLine();

        String query = "SELECT p.id AS pedido_id, p.data_pedido, p.volume_m3, p.peso_kg, e.status AS status_entrega " +
                "FROM Cliente c " +
                "JOIN Pedido p ON c.id = p.cliente_id " +
                "LEFT JOIN Entrega e ON p.id = e.pedido_id " +
                "WHERE c.cpf_cnpj = '" + cpfCnpj + "'";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n\n--- Pedidos do Cliente " + cpfCnpj + " ---\n\n");

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                int pedidoId = rs.getInt("pedido_id");
                String dataPedido = rs.getString("data_pedido");
                double volume = rs.getDouble("volume_m3");
                double peso = rs.getDouble("peso_kg");
                String statusEntrega = rs.getString("status_entrega");

                System.out.println("Pedido ID: " + pedidoId);
                System.out.println("Data do Pedido: " + dataPedido);
                System.out.println("Volume (m³): " + volume);
                System.out.println("Peso (kg): " + peso);
                System.out.println("Status da Entrega: " + (statusEntrega != null ? statusEntrega : "Não atribuída"));
                System.out.println("----------------------------------------\n");
            }

            if (!encontrou) {
                System.out.println("Nenhum pedido encontrado para esse CPF/CNPJ.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedidos por CPF/CNPJ!");
            e.printStackTrace();
        }
    }


    // CANCELAR PEDIDO
    public static void cancelarPedido() {
        Scanner input = new Scanner(System.in);

        listarTodosPedidos();
        System.out.print("\nDigite o ID do pedido que deseja cancelar: ");
        int id_pedido = input.nextInt();
        input.nextLine();

        String query = "UPDATE Pedido SET status = 'CANCELADO' WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_pedido);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("\nPedido cancelado com sucesso!");
            } else {
                System.out.println("\nNenhum pedido encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao cancelar o pedido!");
            e.printStackTrace();
        }
    }


    // EXCLUIR ENTREGA COM VALIDACAO
    public static void excluirEntregaComValidacao() {
        Scanner input = new Scanner(System.in);

        listarTodasEntregas();

        System.out.print("\nDigite o ID da entrega que deseja excluir: ");
        int entregaId = input.nextInt();
        input.nextLine();

        String query = "SELECT status FROM Entrega WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement checkStmt = conn.prepareStatement(query)) {

            checkStmt.setInt(1, entregaId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("\nNenhuma entrega encontrada com esse ID.");
                return;
            }

            String status = rs.getString("status");

            if (status.equals("ENTREGUE") || status.equals("ATRASADA")) {
                System.out.println("\nNão é possível excluir esta entrega. Status: " + status);
                return;
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao verificar a entrega!");
            e.printStackTrace();
            return;
        }

        String deleteSql = "DELETE FROM Entrega WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            deleteStmt.setInt(1, entregaId);
            int linhasAfetadas = deleteStmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("\nEntrega excluída com sucesso!");

            } else {
                System.out.println("\nNenhuma entrega encontrada para exclusão.");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao excluir a entrega!");
            e.printStackTrace();
        }
    }


    // EXCLUIR CLIENTE COM VERIFICAÇÃO DE DEPENDÊNCIA
    public static void excluirClienteComVerificacao() {
        Scanner input = new Scanner(System.in);

        listarTodosClientes();
        System.out.print("\nDigite o ID do cliente que deseja excluir: ");
        int clienteId = input.nextInt();
        input.nextLine();

        String query = "SELECT COUNT(*) AS total FROM Pedido WHERE cliente_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement checkStmt = conn.prepareStatement(query)) {

            checkStmt.setInt(1, clienteId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                System.out.println("\nNão é possível excluir o cliente, pois ele possui pedidos vinculados.");
                return;
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao verificar pedidos do cliente!");
            e.printStackTrace();
            return;
        }

        String deletequery = "DELETE FROM Cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement deleteStmt = conn.prepareStatement(deletequery)) {

            deleteStmt.setInt(1, clienteId);
            int linhasAfetadas = deleteStmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("\nCliente excluído com sucesso!");
            } else {
                System.out.println("\nNenhum cliente encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao excluir o cliente!");
            e.printStackTrace();
        }
    }


    // EXCLUIR MOTORISTA COM VERIFICAÇÃO DE DEPENDÊNCIA
    public static void excluirMotoristaComVerificacao() {
        Scanner input = new Scanner(System.in);

        listarTodosMotoristas();
        System.out.print("\nDigite o ID do motorista que deseja excluir: ");
        int motoristaId = input.nextInt();
        input.nextLine();

        String checkSql = "SELECT COUNT(*) AS total FROM Entrega WHERE motorista_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, motoristaId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                System.out.println("\nNão é possível excluir o motorista, pois ele possui entregas vinculadas.");
                return;
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao verificar entregas do motorista!");
            e.printStackTrace();
            return;
        }

        String deleteSql = "DELETE FROM Motorista WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            deleteStmt.setInt(1, motoristaId);
            int linhasAfetadas = deleteStmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("\nMotorista excluído com sucesso!");
            } else {
                System.out.println("\nNenhum motorista encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao excluir o motorista!");
            e.printStackTrace();
        }
    }
}

