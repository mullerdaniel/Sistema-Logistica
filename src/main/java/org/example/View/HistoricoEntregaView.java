package org.example.View;

import org.example.Model.HistoricoEntrega;
import org.example.Service.HistoricoEntregaService;
import org.example.Utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HistoricoEntregaView {
    private Scanner input = new Scanner(System.in);
    private HistoricoEntregaService historicoEntregaService = new HistoricoEntregaService();
    private DateUtils dateUtils_Entrega = new DateUtils();




    // REGISTRAR EVENTO DE ENTREGA
    public void CadastrarHistorico() {
        System.out.println("\n\n--------Registrar Evento de Entrega (Histórico)--------\n\n");

        dateUtils_Entrega.listarTodasEntregas();

        System.out.print("ID da Entrega: ");
        int entregaId = input.nextInt();
        input.nextLine();

        System.out.print("Data do Evento (yyyy-MM-dd HH:mm): ");
        String dataEventoStr = input.nextLine();
        LocalDateTime dataEvento;

        try {
            dataEvento = LocalDateTime.parse(dataEventoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido!");
            return;
        }

        System.out.print("Descrição do Evento: ");
        String descricao = input.nextLine();

        HistoricoEntrega historico = new HistoricoEntrega(entregaId, dataEvento, descricao);
        historicoEntregaService.CadastrarHistorico(historico);
    }
}
