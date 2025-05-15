/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;
import com.testando.carro.Sistemas.SistemaDeCombustivel;
import com.testando.carro.Sistemas.SistemaEletrico;

/**
 *
 * @author mandr
 */
public class Motor extends ComponenteCarro{

    // Atributos
    public SistemaDeCombustivel sistemaC;
    public SistemaEletrico sistemaE;
    private String tipo;
    private int potencia;
    private double cilindrada;
    public boolean ligado;

    public Motor(SistemaDeCombustivel sistemaC, SistemaEletrico sistemaE, String tipo, int potencia, double cilindrada, boolean ligado, String estado, String material, String marca) {
        super(estado, material, marca);
        this.sistemaE = sistemaE;
        this.sistemaC = sistemaC;
        this.tipo = tipo;
        this.potencia = potencia;
        this.cilindrada = cilindrada;
        this.ligado = ligado;
    }

    

    

    // Liga o motor (versão anterior do método preservada e integrada)
    

    // Método legado preservado
    public void ligarMotor() {
        if (!this.ligado) {
            if (sistemaE.verificarBateria() && sistemaC.verificarNivel() > 0) {
                this.ligado = true;
                System.out.println("Motor ligado");
            } else if (!sistemaE.verificarBateria()) {
                System.out.println("ERRO: A bateria está vazia");
            } else if (sistemaC.verificarNivel() <= 0) {
                System.out.println("ERRO: Sem combustivel");
            }
        } else {
            System.out.println("O motor já está ligado");
        }
        
    }

    // Desliga o motor
    public void desligar() {
        if (this.ligado) {
            this.ligado = false;
        }
        System.out.println("Motor desligado");
    }

    // Verifica o estado do motor
    @Override
    public void verificarEstado() {
        if (ligado) {
            estado = "Ligado";
        } else {
            estado = "Desligado";
        }
        System.out.println("Motor: " + estado);
        
    }
    
    public void acelerar() {
        if (ligado && sistemaC.verificarNivel() > 0) {
            this.sistemaC.ajustarNivel(cilindrada * potencia / 500);
        }
    }
}
