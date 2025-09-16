package org.example.View;

import org.example.Model.Entrega;
import org.example.Service.EntregaService;
import org.example.Utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntregaView {

    DateUtils dateUtils_Pedido = new DateUtils();
    DateUtils dateUtils_Motorista = new DateUtils();

    private Scanner input = new Scanner(System.in);
    private EntregaService entregaService = new EntregaService();


    // GERAR ENTREGA
    public void gerarEntrega() {
        System.out.println("\n\n--------Atribuir Pedido a Motorista 'Gerar Entrega'--------\n\n");

        dateUtils_Pedido.listarTodosPedidos();

        System.out.print("ID do Pedido: ");
        int pedidoId = input.nextInt();
        input.nextLine();

        dateUtils_Motorista.listarTodosMotoristas();

        System.out.print("ID do Motorista: ");
        int motoristaId = input.nextInt();
        input.nextLine();

        System.out.print("Data de Saída (yyyy-MM-dd HH:mm): ");
        String dataSaidaStr = input.nextLine();
        LocalDateTime dataSaida;
        try {
            dataSaida = LocalDateTime.parse(dataSaidaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data de saída inválido!");
            return;
        }

        System.out.print("Data de Entrega Prevista (yyyy-MM-dd HH:mm): ");
        String dataEntregaStr = input.nextLine();
        LocalDateTime dataEntrega;
        try {
            dataEntrega = LocalDateTime.parse(dataEntregaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data de entrega inválido!");
            return;
        }

        String status = "EM_ROTA";

        Entrega entrega = new Entrega(pedidoId, motoristaId, dataSaida, dataEntrega, status);
        entregaService.gerarEntrega(entrega);
    }
}
