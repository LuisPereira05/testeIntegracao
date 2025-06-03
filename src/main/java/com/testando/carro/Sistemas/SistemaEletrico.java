package com.testando.carro.Sistemas;

public class SistemaEletrico {

    private double voltagem;
    private double capacidade;
    private String tipoDeBateria;
    private boolean estado;
    private String marca;



    public SistemaEletrico(double voltagem, double capacidade, String tipoDeBateria, boolean estado, String marca) {
        this.voltagem = voltagem;
        this.capacidade = capacidade;
        this.tipoDeBateria = tipoDeBateria;
        this.estado = estado;
        this.marca = marca;
    }
    
    public boolean verificarBateria() {
        return estado;
    }

    public void substituirBateria() {
        System.out.println("Bateria substituída.");
        estado = true;
    }

    public void testarSistema() {
        System.out.println("Testando sistema elétrico...");
        // Lógica de teste futura
    }

    public void ativarParteEletrica() {
        estado = true;
        System.out.println("Sistema elétrico ativado.");
    }
}
