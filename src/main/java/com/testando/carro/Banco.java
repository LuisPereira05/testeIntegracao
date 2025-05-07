/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */

public class Banco extends ComponenteCarro {
    private String cor;
    private String tipo;

    public Banco(String estado, String material, String marca, String cor, String tipo) {
        super(estado, material, marca);
        this.cor = cor;
        this.tipo = tipo;
    }

    public void ajustarEncosto(String posicao) {
        System.out.println("Encosto ajustado para: " + posicao);
    }

    public void ajustarAltura(double novaAltura) {
        System.out.println("Altura ajustada para: " + novaAltura + " cm");
    }

    @Override
    public void verificarEstado() {
        System.out.println("Banco (" + tipo + ") está: " + estado);
    }
}


