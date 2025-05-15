/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

import com.testando.carro.Sistemas.SistemaEletrico;

/**
 *
 * @author mandr
 */
public class Suspensao extends ComponenteCarro{

    // Atributos
    public SistemaEletrico sistemaE;
    private String tipo;
    private double altura;
    private int rigidez;

    // Construtor vazio

    public Suspensao(SistemaEletrico sistemaE, String tipo, double altura, int rigidez, String estado, String material, String marca) {
        super(estado, material, marca);
        this.sistemaE = sistemaE;
        this.tipo = tipo;
        this.altura = altura;
        this.rigidez = rigidez;
    }

    
    

    // Métodos

    // Ajusta a altura da suspensão
    public void ajustarAltura(double novaAltura) {
        // Implementação futura
    }

    // Retorna o estado atual da suspensão

    @Override
    public void verificarEstado() {
        
    }
    

    // Substitui a suspensão por uma nova
    public void substituir() {
        // Implementação futura
    }
}

