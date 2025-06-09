/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;
import com.testando.carro.Sistemas.*;

/**
 *
 * @author mandr
 */
public class Transmissao extends ComponenteCarro{
    private SistemaDeTransmissao sistema;
    private int marchaAtual;
    

    public Transmissao(SistemaDeTransmissao sistema, int marchaAtual, String estado) {
        super(estado, null, null);
        this.sistema = sistema;
        this.marchaAtual = marchaAtual;
        this.material = this.sistema.material;
        this.marca = this.sistema.marca;
    }

    @Override
    public void verificarEstado() {
        System.out.println("Marcha: " + marchaAtual);
    }
    
    public SistemaDeTransmissao getSistemaDeTransmissao() {
        return sistema;
    }
    
    
    public void aumentarMarcha(){
        sistema.trocarMarcha(sistema.getEstado() + 1);
        marchaAtual = sistema.getEstado();
    }
    
    public void diminuirMarcha(){
        sistema.trocarMarcha(sistema.getEstado() - 1);
        marchaAtual = sistema.getEstado();
    }
}
