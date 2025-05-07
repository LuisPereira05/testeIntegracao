/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testando.carro;

/**
 *
 * @author Abner
 */

public class Painel extends ComponenteCarro {
    private String tipo;
    private String display;
    private boolean controle;

    public Painel(String estado, String material, String marca, String tipo, String display, boolean controle) {
        super(estado, material, marca);
        this.tipo = tipo;
        this.display = display;
        this.controle = controle;
    }

    public void ligarDisplay() {
        System.out.println("Display ligado.");
    }

    public void desligarDisplay() {
        System.out.println("Display desligado.");
    }

    public void atualizarInformacoes(String info) {
        System.out.println("Informações atualizadas: " + info);
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


