package org.example.View;

import org.example.Model.Cliente;
import org.example.Service.ClienteService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private ClienteService clienteService;
    private Scanner scanner;

    public ClienteView() {
        this.clienteService = new ClienteService();
        this.scanner = new Scanner(System.in);
    }


    // CADASTRAR CLIENTE
    public void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF/CNPJ: ");
        String cpfCnpj = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpfCnpj, endereco, cidade, estado);
        clienteService.cadastrarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

}
