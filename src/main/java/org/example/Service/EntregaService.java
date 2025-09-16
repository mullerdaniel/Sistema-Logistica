package org.example.Service;

import org.example.Dao.EntregaDAO;
import org.example.Model.Entrega;

import java.sql.SQLException;

public class EntregaService {
    private EntregaDAO entregaDAO = new EntregaDAO();

    public void gerarEntrega(Entrega entrega) {

        try {
            entregaDAO.gerarEntrega(entrega);
            System.out.println("Entrega gerada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Falha ao gerar entrega!");
            e.printStackTrace();
        }
    }
}
