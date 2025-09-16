package org.example.Dao;

import org.example.Conexao;
import org.example.Model.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
}
