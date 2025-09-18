package org.example.Dao;

import org.example.Conexao;
import org.example.Model.Pedido;

import java.sql.*;

public class PedidoDAO {


    // CRIAR PEDIDO
    public void criarPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO Pedido (cliente_id, data_pedido, volume_m3, peso_kg, status) VALUES (?,?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, pedido.getCliente_id());
            stmt.setTimestamp(2, Timestamp.valueOf(pedido.getData_pedido()));
            stmt.setDouble(3, pedido.getVolume_m3());
            stmt.setDouble(4, pedido.getPeso_kg());
            stmt.setString(5, pedido.getStatus());

            stmt.executeUpdate();
        }
    }


    // VERIFICA SE O ID DO CLIENTE ESTÁ DISPONIVEL
    public boolean existeCliente(int id) {
        String sql = "SELECT id FROM Cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }
}
