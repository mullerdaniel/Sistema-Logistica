package org.example.View;

import java.util.Scanner;

public class MenuView {
    Scanner input = new Scanner(System.in);
    ClienteView clienteView = new ClienteView();
    MotoristaView motoristaView = new MotoristaView();
    PedidoView pedidoView = new PedidoView();

    public void exibirMenu() {
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
            System.out.print("\nOpção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1: {
                    clienteView.cadastrarCliente();
                    break;
                }

                case 2: {
                    motoristaView.cadastrarMotorista();
                    break;
                }

                case 3: {
                    pedidoView.criarPedido();
                    break;
                }

                case 0: {
                    System.out.println("Saindo do sistema...");
                    break;
                }

                default: {
                    System.out.println("Opção inválida. Tente novamente!");
                    break;
                }
            }

        } while (opcao != 0);
    }
}
