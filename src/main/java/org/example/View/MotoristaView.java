package org.example.View;

import org.example.Model.Motorista;
import org.example.Service.MotoristaService;
import java.util.Scanner;

public class MotoristaView {
    Scanner input = new Scanner(System.in);
    MotoristaService motoristaService = new MotoristaService();


    // CADASTRAR MOTORISTAS
    public void cadastrarMotorista() {
        System.out.println("\n--------Cadastrar Motorista--------\n");

        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CNH: ");
        String cnh = input.nextLine();
        System.out.print("Veículo: ");
        String veiculo = input.nextLine();
        System.out.print("Cidade base: ");
        String cidadeBase = input.nextLine();

        Motorista motorista = new Motorista(nome, cnh, veiculo, cidadeBase);
        motoristaService.cadastrarMotorista(motorista);
    }
}
