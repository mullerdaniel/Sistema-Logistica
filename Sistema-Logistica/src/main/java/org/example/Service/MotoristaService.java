package org.example.Service;

import org.example.Dao.MotoristaDAO;
import org.example.Model.Motorista;

import java.sql.SQLException;

public class MotoristaService {
    private MotoristaDAO motoristaDAO = new MotoristaDAO();

    public void cadastrarMotorista(Motorista motorista) {

        try {
            motoristaDAO.cadastrarMotorista(motorista);
            System.out.println("\nMotorista cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar motorista.");
            e.printStackTrace();
        }
    }
}
