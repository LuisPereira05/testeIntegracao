package com.testando.carro.Sistemas;

public class SistemaDeCombustivel {

    // Atributos
    private String tipoDeCombustivel;
    private double capacidade;
    private double nivelDeCombustivel;
    private String marca;
    private boolean estado;

    // Construtor
    public SistemaDeCombustivel(String tipoDeCombustivel, double capacidade, double nivelDeCombustivel, String marca, boolean estado) {
        this.tipoDeCombustivel = tipoDeCombustivel;
        this.capacidade = capacidade;
        this.nivelDeCombustivel = nivelDeCombustivel;
        this.marca = marca;
        this.estado = estado;
    }

    // Verifica o nível de combustível
    public double verificarNivel() {
        return this.nivelDeCombustivel;
    }

    // Abastece o tanque com uma quantidade específica
    public boolean abastecer(double quantidade) {
        if (this.nivelDeCombustivel + quantidade <= this.capacidade) {
            this.nivelDeCombustivel += quantidade;
            System.out.println("Abastecido com sucesso. Nível atual: " + this.nivelDeCombustivel);
            return true;
        } else {
            System.out.println("ERRO: QUANTIDADE INVALIDA");
            return false;
        }
    }

    // Substitui o tanque de combustível
    public boolean substituirTanque(double novaCapacidade) {
        if (novaCapacidade > 0) {
            this.capacidade = novaCapacidade;
            System.out.println("Tanque substituído. Nova capacidade: " + novaCapacidade);
            return true;
        } else {
            System.out.println("ERRO: CAPACIDADE INVALIDA");
            return false;
        }
    }

    // Consome combustível
    public void consumir(double quantidade) {
        if (nivelDeCombustivel - quantidade <= 0) {
            nivelDeCombustivel = 0;
            estado = false;
            System.out.println("Tanque vazio!");
        } else {
            nivelDeCombustivel -= quantidade;
            System.out.println("Combustível consumido. Nível atual: " + nivelDeCombustivel);
        }
    }

    // Retorna se o sistema está ativo
    public boolean isAtivo() {
        return estado;
    }
}
