/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author Abner
 */
public class Motor {

    // Atributos
    private String tipo;
    private int potencia;
    private double cilindrada;
    private String marca;
    private boolean estado;

    // Construtor vazio
    public Motor() {
    }

    // Liga o motor (versão anterior do método preservada e integrada)
    public void ligar() {
        estado = true;
        ligarMotor();
    }

    // Método legado preservado
    public void ligarMotor() {
        System.out.println("Motor ligado");
    }

    // Desliga o motor
    public void desligar() {
        estado = false;
        System.out.println("Motor desligado");
    }

    // Verifica o estado do motor
    public String verificarEstado() {
        return estado ? "Motor em funcionamento" : "Motor desligado";
    }
}
