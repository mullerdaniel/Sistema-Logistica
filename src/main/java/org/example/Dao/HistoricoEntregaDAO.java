package org.example.Dao;

import org.example.Conexao;
import org.example.Model.HistoricoEntrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoEntregaDAO {


    // CADASTRAR HISTORICO
    public void CadastrarHistorico(HistoricoEntrega historico) throws SQLException {
        String query = "INSERT INTO HistoricoEntrega (entrega_id, data_evento, descricao) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, historico.getEntrega_id());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(historico.getData_evento()));
            stmt.setString(3, historico.getDescricao());

            stmt.executeUpdate();
        }
    }
}
