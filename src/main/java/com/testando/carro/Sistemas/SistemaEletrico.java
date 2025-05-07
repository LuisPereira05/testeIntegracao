package com.testando.carro.Sistemas;

public class SistemaEletrico {

    // Atributos
    private double voltagem;
    private double capacidade;
    private String tipoDeBateria;
    private boolean estado;
    private String marca;

    // Construtor vazio
    public SistemaEletrico() {
    }

    // Métodos

    // Verifica o estado da bateria
    public String verificarBateria() {
        return estado ? "Bateria em bom estado" : "Bateria descarregada ou com falha";
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
