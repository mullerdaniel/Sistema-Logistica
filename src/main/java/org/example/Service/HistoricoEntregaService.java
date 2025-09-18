package org.example.Service;

import org.example.Dao.HistoricoEntregaDAO;
import org.example.Model.HistoricoEntrega;

import java.sql.SQLException;

public class HistoricoEntregaService {
    private HistoricoEntregaDAO historicoEntregaDAO = new HistoricoEntregaDAO();

    public void CadastrarHistorico(HistoricoEntrega historico) {

        try {
            historicoEntregaDAO.CadastrarHistorico(historico);
            System.out.println("\n\nEvento registrado com sucesso no histórico!");

        } catch (SQLException e) {
            System.out.println("\n\nFalha ao registrar evento no histórico!");
            e.printStackTrace();
        }
    }
}
