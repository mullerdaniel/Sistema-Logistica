package org.example;

import org.example.Dao.ClienteDAO;
import org.example.Dao.MotoristaDAO;
import org.example.Dao.PedidoDAO;
import org.example.Model.Cliente;
import org.example.Model.Motorista;
import org.example.Model.Pedido;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


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
        int opcao = input.nextInt();


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
        }
    }



        // CADASTRAR CLIENTE
        private static void cadastrarCliente () {
            System.out.println("\n\n--------Cadastrar Cliente--------\n\n");
            input.nextLine();
            System.out.println("Escreva o nome do cliente: ");
            String nome = input.nextLine();
            System.out.println("\nDigite o cpf ou cnpj do cliente: ");
            String cpf_cnpj = input.nextLine();
            System.out.println("\nEscreva o endereço do cliente: ");
            String endereco = input.nextLine();
            System.out.println("\nEscreva a cidade do cliente: ");
            String cidade = input.nextLine();
            System.out.println("\nEscreva o estado do cliente: ");
            String estado = input.nextLine();

            var cliente = new Cliente(nome, cpf_cnpj, endereco, cidade, estado);
            var clientedao = new ClienteDAO();

            try {
                System.out.println("\nCliente cadastrado com sucesso!");
                clientedao.cadastrarCliente(cliente);

            } catch (SQLException e) {
                System.out.println("\nOcorreu um erro ao cadastrar o Cliente!");
                e.printStackTrace();
            }
        }


            // CADASTRAR MOTORISTA
            private static void cadastrarMotorista () {
                System.out.println("\n\n--------Cadastrar Motorista--------\n\n");
                input.nextLine();
                System.out.println("Escreva o nome do motorista: ");
                String nome_motorista = input.nextLine();
                System.out.println("\nDigite o tipo de CNH do motorista: ");
                String cnh = input.nextLine();
                System.out.println("\nEscreva o veiculo do motorista: ");
                String veiculo = input.nextLine();
                System.out.println("\nEscreva a cidade base do motorista: ");
                String cidade_base = input.nextLine();

                var motorista = new Motorista(nome_motorista, cnh, veiculo, cidade_base);
                var motoristadao = new MotoristaDAO();

                try {
                    System.out.println("\nMotorista cadastrado com sucesso!");
                    motoristadao.cadastrarMotorista(motorista);

                } catch (SQLException e) {
                    System.out.println("\nOcorreu um erro ao cadastrar o Motorista!");
                    e.printStackTrace();
                }
            }


            // CRIAR PEDIDO
            public static void criarPedido() {
                System.out.println("\n\n--------Criar Pedido--------\n\n");
                System.out.println("Digite o id do cliente: ");
                int cliente_id = input.nextInt();
                input.nextLine();

                System.out.println("Digite a data do pedido EX: (yyyy/MM/dd HH:mm) ");
                String dataInput = input.nextLine();

                LocalDateTime data_pedido;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    data_pedido = LocalDateTime.parse(dataInput, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Use 'yyyy/MM/dd HH:mm'. Pedido não criado.");
                    return;
                }

                System.out.println("Digite o volume M3 do pedido: ");
                Double volume_m3 = input.nextDouble();
                input.nextLine();
                System.out.println("Digite o peso do pedido em KG: ");
                Double peso_kg = input.nextDouble();
                input.nextLine();
                System.out.println("Digite o status do pedido ('PENDENTE', 'ENTREGUE', 'CANCELADO'): ");
                String status = input.nextLine();

                if (status.equalsIgnoreCase("pendente") || status.equalsIgnoreCase("entregue") || status.equalsIgnoreCase("cancelado")) {
                    var pedido = new Pedido(cliente_id, data_pedido, volume_m3, peso_kg, status);
                    var pedidodao = new PedidoDAO();

                    try {
                        pedidodao.criarPedido(pedido);
                        System.out.println("\nPedido criado com sucesso!");

                    } catch (SQLException e) {
                        System.out.println("\nOcorreu um erro ao criar o Pedido!");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("\nStatus inválido. Pedido não criado.");
                    return;
                }
            }






}

