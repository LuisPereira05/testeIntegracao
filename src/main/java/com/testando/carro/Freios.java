/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */
public class Freios extends ComponenteCarro{

    // Atributos
    private String tipo;
    private double tamanho;
    private double nivelDeDesgaste;

    public Freios(String tipo, double tamanho, double nivelDeDesgaste, String estado, String material, String marca) {
        super(estado, material, marca);
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.nivelDeDesgaste = nivelDeDesgaste;
    }

    public double getNivelDeDesgaste() {
        return nivelDeDesgaste;
    }
    
    

    @Override
    public void verificarEstado() {
        System.out.println("FREIOS: Nível de desgaste -> " + this.nivelDeDesgaste + "%");
    }

    public void substituirPastilhas() {
        this.nivelDeDesgaste = 0.0;
    }
}
