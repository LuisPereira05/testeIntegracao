package com.testando.carro;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.testando.carro.Sistemas.*;

public class CarroTest {

    // --- Testes para Banco ---
    @Test
    public void testAjustarEncostoComBateriaAtiva() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Chumbo-ácido", true, "MarcaA");
        Banco banco = new Banco(sistemaE, "Reclinável", "Couro", "MarcaBanco", "Preto", "Esportivo");

        banco.ajustarEncosto("Vertical");
        assertEquals("Vertical", banco.getEstado());
    }

    @Test
    public void testAjustarAlturaSemBateria() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Chumbo-ácido", false, "MarcaA");
        Banco banco = new Banco(sistemaE, "Reclinável", "Couro", "MarcaBanco", "Preto", "Esportivo");

        banco.ajustarAltura(20.0);
        assertEquals(0.0, banco.altura, 0.0001);
    }

    // --- Testes para Motor ---
    @Test
    public void testMotorLigarEDesligar() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Lithium", true, "MarcaE");
        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Gasolina", 50, 10, "MarcaC", true);

        Motor motor = new Motor(sistemaC, sistemaE, "V8", 300, 5.0, false, "Desligado", "Aço", "MarcaM");
        motor.ligarMotor();
        assertTrue(motor.ligado);

        motor.desligar();
        assertFalse(motor.ligado);
    }

    @Test(timeout=100)
    public void testMotorAcelerarGastaCombustivel() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Lithium", true, "MarcaE");
        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Diesel", 100, 50, "MarcaC", true);

        Motor motor = new Motor(sistemaC, sistemaE, "Diesel", 150, 3.0, true, "Ligado", "Aço", "MarcaM");
        double nivelAntes = sistemaC.verificarNivel();
        motor.acelerar();
        double nivelDepois = sistemaC.verificarNivel();

        assertTrue(nivelDepois < nivelAntes);
    }

    // --- Testes para Luzes ---
    @Test
    public void testLuzesLigarDesligar() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Chumbo-ácido", true, "MarcaL");
        Luzes luzes = new Luzes(sistemaE, "LED", 1, "Branco", "ModeloX", "Plástico", "MarcaL");

        luzes.ligar();
        assertEquals("Ligadas", luzes.estado);

        luzes.desligar();
        assertEquals("Desligadas", luzes.estado);
    }

    @Test
    public void testAjustarIntensidadeInvalida() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "Chumbo-ácido", true, "MarcaL");
        Luzes luzes = new Luzes(sistemaE, "Halogena", 2, "Amarelo", "ModeloY", "Metal", "MarcaL");

        luzes.ajustarIntensidade(5); // inválido
        assertEquals(2, luzes.intensidade);
    }

    // --- Testes para Porta ---
    @Test
    public void testAbrirPortaTravada() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "NiMH", true, "MarcaP");
        Porta porta = new Porta(sistemaE, "Vermelho", "Dianteira", true, "Fechada", "Metal", "MarcaP", "1234");

        porta.abrir();
        assertEquals("Fechada", porta.getEstado());
    }

    @Test(expected=RuntimeException.class)
    public void testDestravarPortaComChaveIncorreta() {
        SistemaEletrico sistemaE = new SistemaEletrico(12, 100, "NiMH", true, "MarcaP");
        Porta porta = new Porta(sistemaE, "Preto", "Traseira", true, "Fechada", "Metal", "MarcaP", "4321");

        porta.destravar("1234"); // deve lançar exceção
    }

    // --- Testes para Transmissao ---
    @Test
    public void testAumentarEDiminuirMarcha() {
        SistemaDeTransmissao sistema = new SistemaDeTransmissao("Manual", 6, "Aço", "MarcaT", 1);
        Transmissao transmissao = new Transmissao(sistema, 1, "Funcionando");

        transmissao.aumentarMarcha();
        assertEquals(2, transmissao.marchaAtual);

        transmissao.diminuirMarcha();
        assertEquals(1, transmissao.marchaAtual);
    }

    @Test(expected=RuntimeException.class)
    public void testTrocarMarchaInvalida() {
        SistemaDeTransmissao sistema = new SistemaDeTransmissao("Manual", 5, "Aço", "MarcaT", 3);
        sistema.trocarMarcha(10); // deve lançar exceção
    }

    // --- Tests para arrays e listas (substituindo assertIterableEquals e assertLinesMatch) ---

    @Test
    public void testAssertArrayEquals() {
        String[] esperado = {"Vermelho", "Preto", "Azul"};
        String[] atual = {"Vermelho", "Preto", "Azul"};
        assertArrayEquals(esperado, atual);
    }

    @Test
    public void testAssertEqualsListas() {
        List<String> esperado = Arrays.asList("LED", "Halogena", "Xenon");
        List<String> atual = Arrays.asList("LED", "Halogena", "Xenon");
        assertEquals(esperado, atual);
    }

    @Test
    public void testAssertEqualsLinhas() {
        List<String> esperado = Arrays.asList("Motor: Ligado", "Banco ajustado para: Vertical");
        List<String> atual = Arrays.asList("Motor: Ligado", "Banco ajustado para: Vertical");
        assertEquals(esperado, atual);
    }

    @Test
    public void testAssertNull() {
        Banco banco = null;
        assertNull(banco);
    }
}
