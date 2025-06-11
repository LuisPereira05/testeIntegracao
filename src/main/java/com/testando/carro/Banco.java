package com.testando.carro;

import com.testando.carro.Sistemas.SistemaEletrico;

/**
 *
 * @author mandr
 */
public class Banco extends ComponenteCarro {
    private SistemaEletrico sistemaE;
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
            System.out.println("ERRO: NÃO FOI POSSÍVEL AJUSTAR O ENCOSTO");
        }
    }

    public void ajustarAltura(double novaAltura) {
        if (sistemaE.verificarBateria()) {
            this.altura = novaAltura;
            System.out.println("Altura ajustada para: " + novaAltura + " cm");
        } else {
            System.out.println("ERRO: NÃO FOI POSSÍVEL AJUSTAR A ALTURA");
        }
    }

    public double getAltura() {
        return this.altura;
    }

    @Override
    public void verificarEstado() {
        System.out.println("Banco (" + tipo + ") está: " + this.estado + ", com altura: " + this.altura);
    }
}
