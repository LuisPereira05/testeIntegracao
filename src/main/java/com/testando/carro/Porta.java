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

public class Porta extends ComponenteCarro {
    public SistemaEletrico sistemaE;
    private String cor;
    private String tipo;
    private boolean travada;

    public Porta(SistemaEletrico sistemaE, String cor, String tipo, boolean travada, String estado, String material, String marca) {
        super("Fechada", material, marca);
        this.sistemaE = sistemaE;
        this.cor = cor;
        this.tipo = tipo;
        this.travada = travada;
    }

    

    public void abrir() {
        if (!this.travada && this.estado.equals("Fechada")) {
            this.estado = "Aberta";
            System.out.println("Porta aberta.");
        } else if(this.estado.equals("Aberta")){
            System.out.println("A porta já está aberta");
        } else {
            System.out.println("ERRO: PORTA TRAVADA WIIIWOOOWIIIWOOOWIIIWOOO!!!!");
        }
        
    }

    public void fechar() {
        if (this.estado.equals("Aberta")) {
            this.estado = "Fechada";
            System.out.println("Porta fechada.");
        }
        
    }

    @Override
    public void verificarEstado() {
        System.out.println("Porta (" + tipo + ") está: " + estado + " e ");
        if (travada) {
            System.out.print("travada");
        } else {
            System.out.print("destravada");
        }
    }
}
