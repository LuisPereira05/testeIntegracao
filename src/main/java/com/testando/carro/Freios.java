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

    private String tipo;
    private double tamanho;
    public double nivelDeDesgaste;
    public boolean deMao;

    public Freios(String tipo, double tamanho, double nivelDeDesgaste, String estado, String material, String marca) {
        super(estado, material, marca);
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.nivelDeDesgaste = nivelDeDesgaste;
        this.deMao = true;
    }
    
    public void arrumarFreio() {
        this.estado = "Ótimo";
        System.out.println("Freios arrumados, não quebre denovo");
    }
    
    public void gambiarra() {
        double r = Math.random();
        if((r * 100.0) > 25 && (r * 100.0) < 65.0) {
            this.estado = "Ótimo";
            System.out.println("EBAAA CONSEGUIU ARRUMAR SEM PAGAR MECÂNICO. Parabéns :)");
        } else {
            System.out.println("Não deu... (._.)");
        }
    }

    public double getNivelDeDesgaste() {
        return nivelDeDesgaste;
    }
    
    public void freiar(){
        if (this.nivelDeDesgaste + 1.0 >= 100.0) {
            this.nivelDeDesgaste = 100.0;
            throw new RuntimeException("IIIIIIHHHHH PERDEU O FREIO");
        } else {
            this.nivelDeDesgaste += 5.0;
        }
    }

    @Override
    public void verificarEstado() {
        System.out.println("FREIOS: Nível de desgaste -> " + this.nivelDeDesgaste + "%");
    }

    public void substituirPastilhas() {
        this.nivelDeDesgaste = 0.0;
    }
    
    public void tirarFreioDeMao() {
        if (deMao) {
            this.deMao = false;
        } else if(this.estado == null) {
            System.out.println("Voçe quebrou, não vai poder tirar...");
        } else {
            System.out.println("O Freio de mão ja está desativado");
        }
    }
    
    public void ativarFreioDeMao() {
        if (deMao) {
            System.out.println("Freio de mão ja está ativado... Quebrou, animal...");
        } else {
            this.estado = null;
            this.deMao = true;
        }
    }
}
