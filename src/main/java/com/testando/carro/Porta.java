/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */

public class Porta extends ComponenteCarro {
    private String cor;
    private String tipo;

    public Porta(String estado, String material, String marca, String cor, String tipo) {
        super(estado, material, marca);
        this.cor = cor;
        this.tipo = tipo;
    }

    public void abrir() {
        System.out.println("Porta aberta.");
    }

    public void fechar() {
        System.out.println("Porta fechada.");
    }

    @Override
    public void verificarEstado() {
        System.out.println("Porta (" + tipo + ") está: " + estado);
    }
}
