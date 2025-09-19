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


    // ATUALIZAR STATUS
    public void atualizarStatusEntrega() {
        System.out.println("\n\n--- Atualizar Status da Entrega ---\n\n");

        DateUtils.listarTodasEntregas();
        System.out.print("ID da Entrega: ");
        int entregaId = input.nextInt();
        input.nextLine();

        System.out.print("Novo Status \n\n1- EM_ROTA\n2- ENTREGUE\n3- ATRASADA\n\nOpção: ");
        int escolha = input.nextInt();

        String novoStatus;

        switch (escolha) {

            case 1:
                novoStatus = "EM_ROTA";
                break;

            case 2:
                novoStatus = "ENTREGUE";
                break;

            case 3:
                novoStatus = "ATRASADA";
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        try {
            entregaService.atualizarStatus(entregaId, novoStatus);

        } catch (Exception e) {
            System.out.println("\n\nFalha ao atualizar o status da entrega!");
            e.printStackTrace();
        }
    }

}
