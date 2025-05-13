/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.testando.carro;

import com.testando.carro.Sistemas.*;
import java.util.ArrayList;

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

    
    
    public Carro(String modelo, int ano, String cor, String placa, double quilometragem, ArrayList<Object> componentes) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.placa = placa;
        this.quilometragem = quilometragem;
    }
    
    public static void main(String[] args) {
        // Criar instâncias das classes envolvidas
        
        
        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Gasolina", 75.0, 0.0, "GM", true);
        Motor motor = new Motor(sistemaC, "V10", 1200, 5.0, false, "Otimo", "Aço", "Bugatti");
        
        SistemaDeTransmissao sistemaT = new SistemaDeTransmissao("Sequencial", 6, "Aço", "Koenigsegg", true);
        Transmissao transmissao = new Transmissao(sistemaT);
        
        SistemaEletrico sistemaE = new SistemaEletrico(12.0, 48.0, "AGM", true, "HAGEN");
        Painel painel = new Painel(sistemaE, "Desligado", "Vidro", "Philips", "Smart", "LED", false);
        Banco banco = new Banco(sistemaE, "Normal", "Coro", "Volvo", "Vermelho", "Comfort");
        
        
        
        
        Carro carro = new Carro();
        // Realizar as ações de ligar o motor, ativar a transmissão e o sistema elétrico
        sistemaE.ativarParteEletrica();
        motor.ligarMotor();
        transmissao.aumentarMarcha();
        
        
        

        // Exibir o status no painel
        String status = painel.exibirStatus();
        System.out.println("Status do painel:\n" + status);

        // Acelerar o carro
        carro.acelerar();
    }
}
