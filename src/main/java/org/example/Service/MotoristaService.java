package org.example.Service;

import org.example.Dao.MotoristaDAO;
import org.example.Model.Motorista;
import java.sql.SQLException;

public class MotoristaService {
    MotoristaDAO motoristaDAO = new MotoristaDAO();

    public void cadastrarMotorista(Motorista motorista) {
        try {
            motoristaDAO.cadastrarMotorista(motorista);
            System.out.println("Motorista cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Falha ao cadastrar motorista!");
            e.printStackTrace();
        }
    }
}
