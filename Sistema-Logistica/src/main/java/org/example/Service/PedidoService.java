package org.example.Service;

import org.example.Dao.PedidoDAO;
import org.example.Model.Pedido;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoService {
    private PedidoDAO pedidoDAO;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
    }

    public void criarPedido(Pedido pedido) throws SQLException {
        pedidoDAO.criarPedido(pedido);
    }


}
