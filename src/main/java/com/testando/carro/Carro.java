/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.testando.carro;

import com.testando.carro.Sistemas.SistemaEletrico;

/**
 *
 * @author Abner
 */
public class Carro {
    String modelo;
    int ano;
    String cor;
    String placa;
    double quilometragem;
    
    public void ligar(){
        
    }
    
    public void desligar(){
        
    }
    
    public void atualizarQuilometragem(double km){
        
    }
    
    public void acelerar() {
        System.out.println("Carro acelerando");
    }

    public static void main(String[] args) {
        // Criar instâncias das classes envolvidas
        Carro carro = new Carro();
        Motor motor = new Motor();
        Transmissao transmissao = new Transmissao();
        SistemaEletrico sistemaEletrico = new SistemaEletrico();
        Painel painel = new Painel();

        // Realizar as ações de ligar o motor, ativar a transmissão e o sistema elétrico
        motor.ligarMotor();
        transmissao.aumentarMarcha();
        sistemaEletrico.ativarParteEletrica();

        // Exibir o status no painel
        String status = painel.exibirStatus();
        System.out.println("Status do painel:\n" + status);

        // Acelerar o carro
        carro.acelerar();
    }
}
