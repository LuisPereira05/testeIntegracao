/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author Abner
 */
public class Transmissao {
    private int marchaAtual;
    
    public void aumentarMarcha(){
        if(marchaAtual < 6){
            marchaAtual++;
            System.out.println("Marcha aumentada para " + marchaAtual);
        } else {
            System.out.println("Não é possível aumentar mais!");
        }
    }
}
