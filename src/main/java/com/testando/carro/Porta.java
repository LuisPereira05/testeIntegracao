package com.testando.carro;

import com.testando.carro.Sistemas.SistemaEletrico;

public class Porta extends ComponenteCarro {

    public SistemaEletrico sistemaE;
    private String cor;
    private String tipo;
    private boolean travada;
    private String chave;

    public Porta(SistemaEletrico sistemaE, String cor, String tipo, boolean travada, String estado, String material, String marca, String chave) {
        super("Fechada", material, marca);
        this.sistemaE = sistemaE;
        this.cor = cor;
        this.tipo = tipo;
        this.travada = travada;
        this.chave = chave;
    }

    public void abrir(Motor motor) {
        if (motor != null && motor.isLigado()) {
            throw new RuntimeException("ERRO: Não é possível abrir a porta com o carro ligado!");
        }

        if (!this.travada && this.estado.equals("Fechada")) {
            this.estado = "Aberta";
            System.out.println("Porta aberta.");
        } else if (this.estado.equals("Aberta")) {
            System.out.println("A porta já está aberta");
        } else {
            System.out.println("ERRO: PORTA TRAVADA WIIIWOOOWIIIWOOOWIIIWOOO!!!!");
            throw new RuntimeException("Porta está travada e não pode ser aberta");
        }
    }

    public void fechar() {
        if (this.estado.equals("Aberta")) {
            this.estado = "Fechada";
            System.out.println("Porta fechada.");
        }
    }

    public void travar() {
        if (!this.travada) {
            this.travada = true;
            System.out.println("Porta travada.");
        } else {
            System.out.println("A porta já está travada.");
        }
    }

    @Override
    public void verificarEstado() {
        System.out.println("Porta (" + tipo + ") está: " + estado + " e ");
        if (travada) {
            System.out.print("travada");
        } else {
            System.out.print("destravada");
        }
    }

    public void destravar(String c) {
        if (travada) {
            if (this.chave.equals(c)) {
                this.travada = false;
                System.out.println("Porta destravada");
            } else {
                System.out.println("INTENTO DE FURTO WIIWOOWIIWOOWIIWOOWIIWOO!!!");
            }
        } else {
            System.out.println("A porta está destravada");
        }
    }

    public String getTipo() {
        return this.tipo;
    }

}
