/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */
public class Pneus extends ComponenteCarro {

    private String tamanho;
    private String tipo;
    private double capacidade;
    private double pressao;

    public Pneus(String tamanho, String tipo, double capacidade, double pressao, String material, String marca) {
        super("Otimo", material, marca);
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.pressao = pressao;
    }

    public double verificarPressao() {
        return this.pressao;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void desgastar(double quantidade) {
        if (capacidade - quantidade < 0) {
            capacidade = 0;
            System.out.println("Pneu desgastado completamente.");
        } else {
            capacidade -= quantidade;
            System.out.println("Desgaste aplicado. Capacidade restante: " + capacidade);
        }
    }

    public void ajustarPressao(double novaPressao) {
        if (novaPressao <= this.capacidade) {
            this.pressao = novaPressao;
        } else {
            System.out.println("ERRO: CAPACIDADE INVALIDA (Maior que a capacidade)");
        }
    }

    @Override
    public void verificarEstado() {

    }

    @Override
    public void substituir() {

    }
}
