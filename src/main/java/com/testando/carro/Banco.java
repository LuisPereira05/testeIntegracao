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

public class Banco extends ComponenteCarro {
    public SistemaEletrico sistemaE;
    private double altura;
    private String cor;
    private String tipo;

    public Banco(SistemaEletrico sistemaE, String estado, String material, String marca, String cor, String tipo) {
        super(estado, material, marca);
        this.sistemaE = sistemaE;
        this.altura = 0.0;
        this.cor = cor;
        this.tipo = tipo;
    }

    public void ajustarEncosto(String posicao) {
        if (sistemaE.verificarBateria()) {
            this.estado = posicao;
            System.out.println("Encosto ajustado para: " + posicao);
        } else {
            System.out.println("ERRO: NAO FOI POSSIVEL AJUSTAR O ENCOSTO");
        }
        
    }

    public void ajustarAltura(double novaAltura) {
        if (sistemaE.verificarBateria()) {
            this.altura = novaAltura;
            System.out.println("Altura ajustada para: " + novaAltura + " cm");
        } else {
            System.out.println("ERRO: NAO FOI POSSIVEL AJUSTAR A ALTURA");
        }
        
    }

    @Override
    public void verificarEstado() {
        System.out.println("Banco (" + tipo + ") está: " + this.estado + ", com altura: " + this.altura);
    }
}


