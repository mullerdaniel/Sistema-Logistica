package org.example.Dao;

import org.example.Conexao;
import org.example.Model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDAO {


    // CADASTRAR MOTORISTA
    public void cadastrarMotorista(Motorista motorista) throws SQLException {
        String query = "INSERT INTO Motorista (nome, cnh, veiculo, cidade_base) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade_base());

            stmt.executeUpdate();
        }
    }


}
