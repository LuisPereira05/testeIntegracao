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
public class Luzes extends ComponenteCarro{


    public SistemaEletrico sistemaE;
    private String tipo;
    private int intensidade;
    private String cor;
    private String modelo;

    public String getCor() {
        return cor;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public String getTipo() {
        return tipo;
    }
   
    



    public Luzes(SistemaEletrico sistemaE, String tipo, int intensidade, String cor, String modelo, String material, String marca) {
        super("Desligadas", material, marca);
        this.sistemaE = sistemaE;
        this.tipo = tipo;
        this.intensidade = intensidade;
        this.cor = cor;
        this.modelo = modelo;
    }
    

    public void ligar() {
        if (this.estado.equals("Desligadas")) {
            this.estado = "Ligadas";
        } else {
            System.out.println("As Luzes já estao ligadas");
        }
    }


    public void desligar() {
        if (this.estado.equals("Ligadas")) {
           this.estado = "Desligadas";
        } else {
            System.out.println("As Luzes já estao desligadas");
        }
    }


    public void ajustarIntensidade(int novaIntensidade) {
        switch (novaIntensidade) {
            case 1:
                this.intensidade = novaIntensidade;
                System.out.println("Intensidade ajustada para: Posição");
                break;
            case 2:
                this.intensidade = novaIntensidade;
                System.out.println("Intensidade ajustada para: baixa");
                break;
            case 3:
                this.intensidade = novaIntensidade;
                System.out.println("Intensidade ajustada para: Alta");
                break;
            default:
                System.out.println("A Intensidade deve ser 1, 2 ou 3");
                break;
        }
    }

    @Override
    public void verificarEstado() {
        System.out.println("LUZES: Estado: " + this.estado + " Intensidade: " + this.intensidade);
    }
    
    
    
}
