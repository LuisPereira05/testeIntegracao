/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */
public class Pneus extends ComponenteCarro{

    // Atributos
    private String tamanho;
    private String tipo;
    private double capacidade;
    private double pressao;



    // Construtor vazio
    public Pneus(String tamanho, String tipo, double capacidade, double pressao, String material, String marca) {    
        super("Otimo", material, marca);
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.pressao = pressao;
    }

    // Métodos
    // Verifica a pressão dos pneus
    public double verificarPressao() {
        // Implementação futura
        return this.pressao;
    }

    public double getCapacidade() {
        return capacidade;
    }
    
    

    // Ajusta a pressão dos pneus
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

    // Substitui o pneu
    @Override
    public void substituir() {
        // Implementação futura
    }
}
