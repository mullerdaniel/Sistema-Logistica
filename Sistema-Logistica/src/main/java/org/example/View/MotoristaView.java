package org.example.View;

import org.example.Model.Motorista;
import org.example.Service.MotoristaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MotoristaView {
    private MotoristaService motoristaService;
    private Scanner scanner;

    public MotoristaView() {
        this.motoristaService = new MotoristaService();
        this.scanner = new Scanner(System.in);
    }


    // CADASTRAR MOTORISTA
    public void cadastrarMotorista() {
        System.out.println("\n--- Cadastrar Motorista ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        System.out.print("Veículo: ");
        String veiculo = scanner.nextLine();

        System.out.print("Cidade base: ");
        String cidadeBase = scanner.nextLine();

        Motorista motorista = new Motorista(nome, cnh, veiculo, cidadeBase);
        motoristaService.cadastrarMotorista(motorista);

        System.out.println("Motorista cadastrado com sucesso!");
    }


}
