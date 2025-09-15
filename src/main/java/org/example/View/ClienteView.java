package org.example.View;

import org.example.Model.Cliente;
import org.example.Service.ClienteService;
import java.util.Scanner;

public class ClienteView {
    Scanner input = new Scanner(System.in);
    ClienteService clienteService = new ClienteService();


    // CADASTRAR CLIENTE
    public void cadastrarCliente() {
        System.out.println("\n--------Cadastrar Cliente--------\n");

        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF/CNPJ: ");
        String cpfCnpj = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Cidade: ");
        String cidade = input.nextLine();
        System.out.print("Estado: ");
        String estado = input.nextLine();

        Cliente cliente = new Cliente(nome, cpfCnpj, endereco, cidade, estado);
        clienteService.cadastrarCliente(cliente);
    }
}
