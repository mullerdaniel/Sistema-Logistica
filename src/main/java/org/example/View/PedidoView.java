package org.example.View;

import org.example.Dao.PedidoDAO;
import org.example.Model.Pedido;
import org.example.Service.PedidoService;
import org.example.Utils.DateUtils;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PedidoView {
    Scanner input = new Scanner(System.in);
    PedidoService pedidoService = new PedidoService();
    PedidoDAO pedidoDAO = new PedidoDAO();

    public void criarPedido() {
        System.out.println("\n\n--------Criar Pedido--------\n\n");

        DateUtils.listarTodosClientes();

        System.out.print("ID do Cliente: ");
        int clienteId = input.nextInt();

        input.nextLine();


        // Verifica se o cliente existe
        if (!pedidoDAO.existeCliente(clienteId)) {
            System.out.println("Cliente não encontrado! Crie o cliente antes de criar um pedido.");
            return;
        }

        System.out.print("Data do Pedido (yyyy-MM-dd HH:mm): ");
        String data = input.nextLine();
        LocalDateTime dataPedido;

        try {
            dataPedido = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido!");
            return;
        }

        System.out.print("Volume M³: ");
        double volumeM3 = input.nextDouble();

        System.out.print("Peso (Kg): ");
        double pesoKg = input.nextDouble();
        input.nextLine();

        String status = "PENDENTE";

        Pedido pedido = new Pedido(clienteId, dataPedido, volumeM3, pesoKg, status);
        pedidoService.criarPedido(pedido);
    }
}
