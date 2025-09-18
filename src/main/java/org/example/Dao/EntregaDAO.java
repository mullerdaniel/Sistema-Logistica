package org.example.Dao;

import org.example.Conexao;
import org.example.Model.Entrega;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {


    // GERAR ENTREGA
    public void gerarEntrega(Entrega entrega) throws SQLException {
        String query = "INSERT INTO Entrega (pedido_id, motorista_id, data_saida, data_entrega, status) VALUES (?,?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, entrega.getPedido_id());
            stmt.setInt(2, entrega.getMotorista_id());
            stmt.setTimestamp(3, Timestamp.valueOf(entrega.getData_saida()));
            stmt.setTimestamp(4, Timestamp.valueOf(entrega.getData_entrega()));
            stmt.setString(5, entrega.getStatus());

            stmt.executeUpdate();
        }
    }


    // LISTAR TODAS AS ENTREGAS
    public List<Entrega> listarTodasEntregas() throws SQLException {
        List<Entrega> entregas = new ArrayList<>();
        String query = "SELECT * FROM Entrega";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entrega entrega = new Entrega(
                        rs.getInt("pedido_id"),
                        rs.getInt("motorista_id"),
                        rs.getTimestamp("data_saida").toLocalDateTime(),
                        rs.getTimestamp("data_entrega").toLocalDateTime(),
                        rs.getString("status")
                );
                entrega.setId(rs.getInt("id"));
                entregas.add(entrega);
            }
        }
        return entregas;
    }


    // ATUALIZAR DADOS DA ENTREGA
    public void atualizarStatus(int entregaId, String novoStatus) throws SQLException {
        String query = "UPDATE Entrega SET status = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, entregaId);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Status da entrega atualizado com sucesso!");
            } else {
                System.out.println("Entrega não encontrada para atualização!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status da entrega!");
            e.printStackTrace();
        }
    }


}
