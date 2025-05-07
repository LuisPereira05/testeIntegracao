/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author mandr
 */

public abstract class ComponenteCarro {
    protected String estado;
    protected String material;
    protected String marca;

    public ComponenteCarro(String estado, String material, String marca) {
        this.estado = estado;
        this.material = material;
        this.marca = marca;
    }

    public abstract void verificarEstado();

    public void substituir() {
        System.out.println("Substituindo componente...");
        this.estado = "novo";
    }

    // Getters e setters
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMaterial() { return material; }
    public String getMarca() { return marca; }
}

