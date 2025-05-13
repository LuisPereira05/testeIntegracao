package com.testando.carro.Sistemas;

public class SistemaEletrico {

    // Atributos
    private double voltagem;
    private double capacidade;
    private String tipoDeBateria;
    private boolean estado;
    private String marca;

    // Construtor vazio

    public SistemaEletrico(double voltagem, double capacidade, String tipoDeBateria, boolean estado, String marca) {
        this.voltagem = voltagem;
        this.capacidade = capacidade;
        this.tipoDeBateria = tipoDeBateria;
        this.estado = estado;
        this.marca = marca;
    }
    

    // Métodos

    // Verifica o estado da bateria
    public boolean verificarBateria() {
        return estado;
    }

    // Substitui a bateria
    public void substituirBateria() {
        System.out.println("Bateria substituída.");
        estado = true;
    }

    // Testa o sistema elétrico
    public void testarSistema() {
        System.out.println("Testando sistema elétrico...");
        // Lógica de teste futura
    }

    // Método já existente integrado
    public void ativarParteEletrica() {
        estado = true;
        System.out.println("Sistema elétrico ativado.");
    }
}
