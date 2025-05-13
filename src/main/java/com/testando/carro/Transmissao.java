/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;
import com.testando.carro.Sistemas.*;

/**
 *
 * @author Abner
 */
public class Transmissao extends ComponenteCarro{
    private SistemaDeTransmissao sistema;
    private int marchaAtual;
    

    public Transmissao(SistemaDeTransmissao sistema, int marchaAtual, String estado, String material, String marca) {
        super(estado, material, marca);
        this.sistema = sistema;
        this.marchaAtual = marchaAtual;
    }

    @Override
    public void verificarEstado() {
        System.out.println("Ótimo");
    }
    
    
    
    
    public void aumentarMarcha(){
        if(marchaAtual < 6){
            marchaAtual++;
            System.out.println("Marcha aumentada para " + marchaAtual);
        } else {
            System.out.println("Não é possível aumentar mais!");
        }
    }
}
