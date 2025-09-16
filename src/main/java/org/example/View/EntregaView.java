package org.example.View;

import org.example.Model.Entrega;
import org.example.Service.EntregaService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EntregaView {
    private Scanner input = new Scanner(System.in);
    private EntregaService entregaService = new EntregaService();


    // GERAR ENTREGA
    public void gerarEntrega() {
        System.out.println("\n\n--------Atribuir Pedido a Motorista 'Gerar Entrega'--------\n\n");

        System.out.print("ID do Pedido: ");
        int pedidoId = input.nextInt();

        System.out.print("ID do Motorista: ");
        int motoristaId = input.nextInt();
        input.nextLine();

        System.out.print("Data de Saída (yyyy-MM-dd HH:mm): ");
        String dataSaidaStr = input.nextLine();
        LocalDateTime dataSaida = LocalDateTime.parse(dataSaidaStr);

        System.out.print("Data de Entrega Prevista (yyyy-MM-dd HH:mm): ");
        String dataEntregaStr = input.nextLine();
        LocalDateTime dataEntrega = LocalDateTime.parse(dataEntregaStr);

        String status = "EM_ROTA";

        Entrega entrega = new Entrega(pedidoId, motoristaId, dataSaida, dataEntrega, status);
        entregaService.gerarEntrega(entrega);
    }
}
