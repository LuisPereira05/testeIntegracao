/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.testando.carro;

import com.testando.carro.Sistemas.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Abner
 */
public class Carro {

    String modelo;
    int ano;
    String cor;
    String placa;
    double quilometragem;
    SistemaDeCombustivel sistemaC;
    SistemaEletrico sistemaE;
    SistemaDeTransmissao sistemaT;
    Motor motor;
    Freios freios;
    Luzes luzes;
    Painel painel;
    Pneus pneus;
    ArrayList<Porta> portas;
    Suspensao suspensao;
    Transmissao transmissao;

    public void ligar() {
        if (sistemaC.verificarNivel() > 0 && sistemaE.verificarBateria()) {
            sistemaE.ativarParteEletrica();
            motor.ligarMotor();
            painel.ligarDisplay();
            System.out.println("Carro ligado.");
        } else {
            System.out.println("Falha ao ligar o carro. Verifique bateria e combustível.");
        }
    }

    public void desligar() {
        painel.desligarDisplay();
        motor.desligar(); // Corrigido aqui
        System.out.println("Carro desligado.");
    }

    public void atualizarQuilometragem(double km) {
        if (km > 0) {
            this.quilometragem += km;
            System.out.println("Quilometragem atualizada para: " + this.quilometragem);
        } else {
            System.out.println("Quilometragem inválida.");
        }
    }

    public void acelerar() {
        System.out.println("Carro acelerando");
        this.motor.acelerar();
    }

    public Carro(String modelo, int ano, String cor, String placa, double quilometragem, SistemaDeCombustivel sistemaC, SistemaEletrico sistemaE, SistemaDeTransmissao sistemaT, Motor motor, Freios freios, Luzes luzes, Painel painel, Pneus pneus, ArrayList<Porta> portas, Suspensao suspensao, Transmissao transmissao) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.sistemaC = sistemaC;
        this.sistemaE = sistemaE;
        this.sistemaT = sistemaT;
        this.motor = motor;
        this.freios = freios;
        this.luzes = luzes;
        this.painel = painel;
        this.pneus = pneus;
        this.portas = portas;
        this.suspensao = suspensao;
        this.transmissao = transmissao;
    }

    public void verificarTudo() {
        System.out.println("Combustivel: " + sistemaC.verificarNivel());

        System.out.print("Bateria: ");
        String a = this.sistemaE.verificarBateria() ? "Carregada" : "Descarregada)";
        System.out.print(a + "\n");

        motor.verificarEstado();
        freios.verificarEstado();
        luzes.verificarEstado();
        painel.verificarEstado();
        pneus.verificarEstado();
        for (Porta porta : portas) {
            porta.verificarEstado();
        }
        suspensao.verificarEstado();
        transmissao.verificarEstado();
    }

    public static void main(String[] args) {

        SistemaDeTransmissao sistemaT = new SistemaDeTransmissao("Sequencial", 6, "Aço", "Koenigsegg", 0);
        Transmissao transmissao = new Transmissao(sistemaT, 0, "Otimo");

        SistemaEletrico sistemaE = new SistemaEletrico(12.0, 48.0, "AGM", true, "HAGEN");
        Painel painel = new Painel(sistemaE, "Desligado", "Vidro", "Philips", "Smart", "LED", false);
        Banco banco = new Banco(sistemaE, "Normal", "Coro", "Volvo", "Vermelho", "Comfort");
        Luzes luzes = new Luzes(sistemaE, "LED", 1, "branco", "Fisheye", "Vidro", "Phillips");

        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Gasolina", 75.0, 0.0, "GM", true);
        Motor motor = new Motor(sistemaC, sistemaE, "V10", 1200, 5.0, false, "Otimo", "Aço", "Bugatti");

        Freios freios = new Freios("ABS", 12.0, 0.0, "Desativados", "Ceramica", "Volvo");
        Pneus pneus = new Pneus("15x6", "tuner", 40.0, 38.0, "aluminio", "Chevrolet");
        Suspensao suspensao = new Suspensao(sistemaE, "esportivo", 20.0, 2, "nova", "fibra de carbono", "Ferrari");

        ArrayList<Porta> portas = new ArrayList<>();
        String chave = "12345";
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira esquerda", true, "Fechada", "fibra de carbono", "Volvo", chave));
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira direita", true, "Fechada", "fibra de carbono", "Volvo", chave));

        Carro carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaE, sistemaT, motor, freios, luzes, painel, pneus, portas, suspensao, transmissao);
        //Carro carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaE, sistemaT, );
        // Realizar as ações de ligar o motor, ativar a transmissão e o sistema elétrico

        portas.get(0).destravar(chave);

        System.out.println(sistemaC.abastecer(70.0));

        portas.get(0).abrir();
        sistemaE.ativarParteEletrica();
        portas.get(0).fechar();
        motor.ligarMotor();
        painel.ligarDisplay();
        transmissao.aumentarMarcha();

        // Exibir o status no painel
        carro.verificarTudo();

        // Acelerar o carro
        carro.acelerar();
    }
}
