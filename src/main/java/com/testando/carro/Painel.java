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

public class Painel extends ComponenteCarro {
    private SistemaEletrico sistemaE;
    private String tipo;
    private String display;
    private boolean controle;
    private boolean ligado;

    public Painel(SistemaEletrico sistemaE, String estado, String material, String marca, String tipo, String display, boolean controle) {
        super(estado, material, marca);
        this.sistemaE = sistemaE;
        this.tipo = tipo;
        this.display = display;
        this.controle = controle;
    }
    
    public Painel() {
        super(null, null, null);
        this.tipo = null;
        this.display = null;
        this.controle = true;
    }

    public void ligarDisplay() {
        if (sistemaE.verificarBateria()) {
            this.ligado = true;
            System.out.println("Display ligado.");
        } else {
            this.ligado = false;
            System.out.println("ERRO: NAO FOI POSSIVEL LIGAR O DISPLAY");
        }
    }

    public void desligarDisplay() {
        if (this.ligado) {
            this.ligado = false;
        }
        System.out.println("Display desligado.");
    }

    public void atualizarInformacoes(String info) {
        
        System.out.println("Informações atualizadas: " + info);
    }
    
    public void atualizarLigado(){
        if (this.ligado && !sistemaE.verificarBateria()) {
            this.ligado = false;
        }
    }
    

    @Override
    public void verificarEstado() {
        System.out.println("Painel (" + tipo + ") está: " + estado);
    }

    // ✅ Método solicitado
    public String exibirStatus() {
        return "Painel: " + tipo + "\n" +
               "Display: " + display + "\n" +
               "Controle: " + (controle ? "Ativo" : "Inativo") + "\n" +
               "Estado: " + estado + "\n" +
               "Marca: " + marca + "\n" +
               "Material: " + material;
    }
}


