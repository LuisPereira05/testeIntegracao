/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro.Sistemas;

/**
 *
 * @author mandr
 */
public class SistemaDeTransmissao {

    // Atributos
    private String tipo;
    private int numeroDeMarchas;
    public String material;
    public String marca;
    private int estado;

    // Construtor vazio

    public SistemaDeTransmissao(String tipo, int numeroDeMarchas, String material, String marca, int estado) {
        this.tipo = tipo;
        this.numeroDeMarchas = numeroDeMarchas;
        this.material = material;
        this.marca = marca;
        this.estado = estado;
    }
    

    // Métodos

    // Troca a marcha da transmissão
    public void trocarMarcha(int marcha) {
        if (marcha >= -1 && marcha <= this.numeroDeMarchas) {
            this.estado = marcha;
        } else {
            System.out.println("ERRO: Marcha Inválida");
        }
    }

    public int getEstado() {
        return estado;
    }
}
