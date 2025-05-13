/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;
import com.testando.carro.Sistemas.SistemaDeCombustivel;

/**
 *
 * @author Abner
 */
public class Motor extends ComponenteCarro{

    // Atributos
    public SistemaDeCombustivel sistemaC;
    private String tipo;
    private int potencia;
    private double cilindrada;
    public boolean ligado;

    public Motor(SistemaDeCombustivel sistemaC, String tipo, int potencia, double cilindrada, boolean ligado, String estado, String material, String marca) {
        super(estado, material, marca);
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
            this.ligado = true;
        }
        System.out.println("Motor ligado");
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
        System.out.println(estado);
        
    }
}
