package org.example.View;

import org.example.Model.Pedido;
import org.example.Service.PedidoService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class PedidoView {
    private PedidoService pedidoService;
    private Scanner scanner;

    public PedidoView() {
        this.pedidoService = new PedidoService();
        this.scanner = new Scanner(System.in);
    }


    // CRIAR PEDIDO
    public void criarPedido() {
        try {
            System.out.println("\n--- Criar Pedido ---");
            System.out.print("Digite o ID do cliente: ");
            int clienteId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite a data do pedido (yyyy/MM/dd HH:mm): ");
            String dataInput = scanner.nextLine();
            LocalDateTime dataPedido = LocalDateTime.parse(dataInput, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

            System.out.print("Digite o volume em m³: ");
            double volume = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Digite o peso em kg: ");
            double peso = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Digite o status (PENDENTE, ENTREGUE, CANCELADO): ");
            String status = scanner.nextLine();

            Pedido pedido = new Pedido(clienteId, dataPedido, volume, peso, status.toUpperCase());
            pedidoService.criarPedido(pedido);

            System.out.println("Pedido criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }


}
