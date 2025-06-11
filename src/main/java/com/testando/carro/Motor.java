package com.testando.carro;

import com.testando.carro.Sistemas.SistemaDeCombustivel;
import com.testando.carro.Sistemas.SistemaEletrico;

/**
 * Classe que representa o motor do carro.
 * Integra com os sistemas elétrico e de combustível.
 * @author mandr
 */
public class Motor extends ComponenteCarro {

    // Atributos
    private SistemaDeCombustivel sistemaC;
    private SistemaEletrico sistemaE;
    private String tipo;
    private int potencia;
    private double cilindrada;
    private boolean ligado;

    // Construtor
    public Motor(SistemaDeCombustivel sistemaC, SistemaEletrico sistemaE, String tipo, int potencia, double cilindrada, boolean ligado, String estado, String material, String marca) {
        super(estado, material, marca);
        this.sistemaE = sistemaE;
        this.sistemaC = sistemaC;
        this.tipo = tipo;
        this.potencia = potencia;
        this.cilindrada = cilindrada;
        this.ligado = ligado;
    }

    // Método para ligar o motor com verificação dos sistemas
    public void ligarMotor() {
        if (!this.ligado) {
            if (sistemaE.verificarBateria() && sistemaC.verificarNivel() > 0) {
                this.ligado = true;
                System.out.println("Motor ligado");
            } else if (!sistemaE.verificarBateria()) {
                System.out.println("ERRO: A bateria está vazia");
            } else {
                System.out.println("ERRO: Sem combustivel");
            }
        } else {
            System.out.println("O motor já está ligado");
        }
    }

    // Método para desligar o motor
    public void desligar() {
        if (this.ligado) {
            this.ligado = false;
            System.out.println("Motor desligado");
        }
    }

    // Retorna se o motor está ligado
    public boolean isLigado() {
        return this.ligado;
    }

    // Verifica o estado do motor (ligado/desligado)
    @Override
    public void verificarEstado() {
        estado = ligado ? "Ligado" : "Desligado";
        System.out.println("Motor: " + estado);
    }

    // Simula o consumo de combustível ao acelerar
    public void acelerar() {
        if (ligado && sistemaC.verificarNivel() > 0) {
            double consumo = cilindrada * potencia / 500;
            sistemaC.consumir(consumo);  // substitui ajustarNivel()
        }
    }
}
