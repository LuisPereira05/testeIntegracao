/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro.Sistemas;

/**
 *
 * @author mandr
 */
public class SistemaDeCombustivel {

    // Atributos
    private String tipoDeCombustivel;
    private double capacidade;
    private double nivelDeCombustivel;
    private String marca;
    private boolean estado;


    public SistemaDeCombustivel(String tipoDeCombustivel, double capacidade, double nivelDeCombustivel, String marca, boolean estado) {
        this.tipoDeCombustivel = tipoDeCombustivel;
        this.capacidade = capacidade;
        this.nivelDeCombustivel = nivelDeCombustivel;
        this.marca = marca;
        this.estado = estado;
    }
    

    // Métodos

    // Verifica o nível de combustível
    public double verificarNivel() {
        return this.nivelDeCombustivel;
    }

    // Abastece o tanque com uma quantidade específica
    public boolean abastecer(double quantidade) {
        if (this.nivelDeCombustivel + quantidade <= this.capacidade) {
            this.capacidade += quantidade;
            return true;
        } else {
            System.out.println("ERRO: QUANTIDADE INVALIDA");
            return false;
        }
    }

    // Substitui o tanque de combustível
    public boolean substituirTanque(double novaCapacidade) {
        if (novaCapacidade > 0) {
            this.capacidade = novaCapacidade;
            return true;
        } else{
            System.out.println("ERRO: CAPACIDADE INVALIDA");
            return false;
        }
        
    }
}

