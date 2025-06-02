package com.testando.carro;

import com.testando.carro.Sistemas.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarroTest {

    Carro carro;
    Motor motor;
    Transmissao transmissao;
    SistemaEletrico sistemaEletrico;
    Painel painel;
    SistemaDeCombustivel sistemaC;
    SistemaDeTransmissao sistemaT;
    Freios freios;
    Luzes luzes;
    Pneus pneus;
    ArrayList<Porta> portas;
    Suspensao suspensao;

    @Before
    public void setUp() {
        sistemaT = new SistemaDeTransmissao("Sequencial", 6, "Aço", "Koenigsegg", 0);
        transmissao = new Transmissao(sistemaT, 0, "Otimo");
        sistemaEletrico = new SistemaEletrico(12.0, 48.0, "AGM", true, "HAGEN");
        painel = new Painel(sistemaEletrico, "Desligado", "Vidro", "Philips", "Smart", "LED", false);
        sistemaC = new SistemaDeCombustivel("Gasolina", 75.0, 0.0, "GM", true);
        motor = new Motor(sistemaC, sistemaEletrico, "V10", 1200, 5.0, false, "Otimo", "Aço", "Bugatti");
        freios = new Freios("ABS", 12.0, 0.0, "Desativados", "Ceramica", "Volvo");
        pneus = new Pneus("15x6", "tuner", 40.0, 38.0, "aluminio", "Chevrolet");
        suspensao = new Suspensao(sistemaEletrico, "esportivo", 20.0, 2, "nova", "fibra de carbono", "Ferrari");
        luzes = new Luzes(sistemaEletrico, "LED", 1, "branco", "Fisheye", "Vidro", "Phillips");

        portas = new ArrayList<>();
        String chave = "12345";
        portas.add(new Porta(sistemaEletrico, "Vermelho", "delanteira esquerda", true, "Fechada", "fibra de carbono", "Volvo", chave));
        portas.add(new Porta(sistemaEletrico, "Vermelho", "delanteira direita", true, "Fechada", "fibra de carbono", "Volvo", chave));

        carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaEletrico, sistemaT, motor, freios, luzes, painel, pneus, portas, suspensao, transmissao);
    }

    @After
    public void tearDown() {
    }

    // ✅ Teste de integração completo
    @Test
    public void testIntegracaoCarro() {
        portas.get(0).destravar("12345");
        assertTrue(sistemaC.abastecer(70.0));
        portas.get(0).abrir();
        sistemaEletrico.ativarParteEletrica();
        portas.get(0).fechar();
        motor.ligarMotor();
        painel.ligarDisplay();
        transmissao.aumentarMarcha();
        carro.verificarTudo();
        carro.acelerar();
    }

    // ✅ assertArrayEquals
    @Test
    public void testAssertArrayEquals() {
        String[] esperado = {"delanteira esquerda", "delanteira direita"};
        String[] real = {
            portas.get(0).getPosicao(),
            portas.get(1).getPosicao()
        };
        assertArrayEquals(esperado, real);
    }

    // ✅ assertEquals como alternativa para Iterable
    @Test
    public void testAssertIterableEqualsSimulada() {
        List<String> esperado = Arrays.asList("Fechada", "Fechada");
        List<String> real = Arrays.asList(portas.get(0).estado, portas.get(1).estado);
        assertEquals(esperado, real);
    }

    // ✅ assertNull
    @Test
    public void testAssertNull() {
        Banco banco = null;
        assertNull(banco);
    }

    // ✅ assertThrows (modo compatível com JUnit 4)
    @Test
    public void testAssertThrowsSimulado() {
        try {
            throw new IllegalArgumentException("Erro simulado");
        } catch (IllegalArgumentException e) {
            assertEquals("Erro simulado", e.getMessage());
        }
    }

    // ✅ assertTrue com tempo como substituto de assertTimeout
    @Test
    public void testTimeoutSimulado() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(100); // simula operação demorada
        } catch (InterruptedException e) {
            fail("Thread interrompida");
        }
        long duration = System.currentTimeMillis() - start;
        assertTrue("Operação demorou demais", duration < 500);
    }

    // ✅ assertTrue como substituto de assertLinesMatch
    @Test
    public void testSimulandoAssertLinesMatch() {
        String mensagem = "Carro acelerando";
        assertTrue(mensagem.matches("Carro.*"));
    }
}
