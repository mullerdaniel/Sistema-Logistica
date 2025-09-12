package org.example.View;

import org.example.Model.Cliente;
import org.example.Model.Motorista;
import org.example.Model.Pedido;
import org.example.Service.ClienteService;
import org.example.Service.MotoristaService;
import org.example.Service.PedidoService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuView {
    private static Scanner input = new Scanner(System.in);

    private static ClienteService clienteService = new ClienteService();
    private static MotoristaService motoristaService = new MotoristaService();
    private static PedidoService pedidoService = new PedidoService();

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n\n----------- Sistema de Logística de Entregas -----------\n\n");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Motorista");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Atribuir Pedido a Motorista (Gerar Entrega)");
            System.out.println("5 - Registrar Evento de Entrega (Histórico)");
            System.out.println("6 - Atualizar Status da Entrega");
            System.out.println("7 - Listar Todas as Entregas com Cliente e Motorista");
            System.out.println("8 - Relatório: Total de Entregas por Motorista");
            System.out.println("9 - Relatório: Clientes com Maior Volume Entregue");
            System.out.println("10 - Relatório: Pedidos Pendentes por Estado");
            System.out.println("11 - Relatório: Entregas Atrasadas por Cidade");
            System.out.println("12 - Buscar Pedido por CPF/CNPJ do Cliente");
            System.out.println("13 - Cancelar Pedido");
            System.out.println("14 - Excluir Entrega (com validação)");
            System.out.println("15 - Excluir Cliente (com verificação de dependência)");
            System.out.println("16 - Excluir Motorista (com verificação de dependência)");
            System.out.println("0 - Sair");
            System.out.println("\n\nOpção: ");

            opcao = input.nextInt();
            input.nextLine(); // limpar buffer

            switch (opcao) {
                case 1: {
                    cadastrarCliente();
                }

                case 2: {
                    cadastrarMotorista();
                }

                case 3: {
                    criarPedido();
                }

                case 0: {
                    System.out.println("\nEncerrando o sistema...");
                }

                default -> System.out.println("\nOpção inválida, tente novamente!");
            }

        } while (opcao != 0);
    }

    // CADASTRAR CLIENTE
    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF/CNPJ: ");
        String cpf_cnpj = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Cidade: ");
        String cidade = input.nextLine();
        System.out.print("Estado (UF): ");
        String estado = input.nextLine();

        Cliente cliente = new Cliente(nome, cpf_cnpj, endereco, cidade, estado);
        clienteService.cadastrarCliente(cliente);
    }

    // CADASTRAR MOTORISTA
    private static void cadastrarMotorista() {
        System.out.println("\n--- Cadastro de Motorista ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CNH: ");
        String cnh = input.nextLine();
        System.out.print("Veículo: ");
        String veiculo = input.nextLine();
        System.out.print("Cidade Base: ");
        String cidade_base = input.nextLine();

        Motorista motorista = new Motorista(nome, cnh, veiculo, cidade_base);
        motoristaService.cadastrarMotorista(motorista);
    }

    // CRIAR PEDIDO
    private static void criarPedido() throws SQLException {
        System.out.println("\n--- Criar Pedido ---");

        System.out.print("ID do Cliente: ");
        int cliente_id = input.nextInt();
        input.nextLine();

        System.out.print("Data do pedido (yyyy/MM/dd HH:mm): ");
        String dataInput = input.nextLine();
        LocalDateTime data_pedido;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            data_pedido = LocalDateTime.parse(dataInput, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("⚠️ Formato inválido. Use 'yyyy/MM/dd HH:mm'.");
            return;
        }

        System.out.print("Volume em m³: ");
        double volume_m3 = input.nextDouble();
        System.out.print("Peso em Kg: ");
        double peso_kg = input.nextDouble();
        input.nextLine();

        System.out.print("Status (PENDENTE, ENTREGUE, CANCELADO): ");
        String status = input.nextLine().toUpperCase();

        if (status.equals("PENDENTE") || status.equals("ENTREGUE") || status.equals("CANCELADO")) {
            Pedido pedido = new Pedido(cliente_id, data_pedido, volume_m3, peso_kg, status);
            pedidoService.criarPedido(pedido);
        } else {
            System.out.println("⚠️ Status inválido. Pedido não criado.");
        }
    }
}
