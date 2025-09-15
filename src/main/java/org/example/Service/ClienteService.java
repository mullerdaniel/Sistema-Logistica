package org.example.Service;

import org.example.Dao.ClienteDAO;
import org.example.Model.Cliente;
import java.sql.SQLException;

public class ClienteService {
    ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(Cliente cliente) {
        try {
            clienteDAO.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Falha ao cadastrar cliente!");
            e.printStackTrace();
        }
    }
}
