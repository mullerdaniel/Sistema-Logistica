package org.example.Service;

import org.example.Dao.PedidoDAO;
import org.example.Model.Pedido;
import java.sql.SQLException;

public class PedidoService {
    PedidoDAO pedidoDAO = new PedidoDAO();

    public void criarPedido(Pedido pedido) {
        try {
            pedidoDAO.criarPedido(pedido);
            System.out.println("Pedido criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Falha ao criar pedido!");
            e.printStackTrace();
        }
    }
}
