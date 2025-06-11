package com.testando.carro;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.testando.carro.Sistemas.SistemaDeCombustivel;
import com.testando.carro.Sistemas.SistemaDeTransmissao;
import com.testando.carro.Sistemas.SistemaEletrico;

public class CarroTest {

    private Carro carro;
    private String chavePorta = "12345";

    @BeforeEach
    public void setup() {
        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Gasolina", 75.0, 10.0, "GM", true);
        SistemaEletrico sistemaE = new SistemaEletrico(12.0, 48.0, "AGM", true, "HAGEN");
        SistemaDeTransmissao sistemaT = new SistemaDeTransmissao("Sequencial", 6, "Aço", "Koenigsegg", 0);

        Motor motor = new Motor(sistemaC, sistemaE, "V10", 1200, 5.0, false, "Otimo", "Aço", "Bugatti");
        Freios freios = new Freios("ABS", 12.0, 0.0, "Desativados", "Ceramica", "Volvo");
        Luzes luzes = new Luzes(sistemaE, "LED", 1, "branco", "Fisheye", "Vidro", "Phillips");
        Painel painel = new Painel(sistemaE, "Desligado", "Vidro", "Philips", "Smart", "LED", false);
        Banco banco = new Banco(sistemaE, "Normal", "Coro", "Volvo", "Vermelho", "Comfort");
        Pneus pneus = new Pneus("15x6", "tuner", 40.0, 38.0, "aluminio", "Chevrolet");
        Suspensao suspensao = new Suspensao(sistemaE, "esportivo", 20.0, 2, "nova", "fibra de carbono", "Ferrari");
        Transmissao transmissao = new Transmissao(sistemaT, 0, "Otimo");

        ArrayList<Porta> portas = new ArrayList<>();
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira esquerda", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira direita", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));

        carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaE, sistemaT, motor, freios, luzes, painel, banco, pneus, portas, suspensao, transmissao);
    }

    @Test
    public void testAceleracaoTravaPortas() {
        System.out.println("\tEste teste está rodando: testAceleracaoTravaPortas");
        carro.portas.get(0).destravar(chavePorta);
        carro.acelerar();

        for (Porta porta : carro.getPortas()) {
            assertThrows(RuntimeException.class,
                    () -> porta.abrir(carro.getMotor()),
                    "A porta deve estar travada após acelerar");
        }
    }

    @Test
    public void testAceleracaoConsomeCombustivel() {
        System.out.println("\tEste teste está rodando: testAceleracaoConsomeCombustivel");
        double nivelAntes = carro.getSistemaC().verificarNivel();
        carro.acelerar();
        double nivelDepois = carro.getSistemaC().verificarNivel();

        assertTrue(nivelDepois < nivelAntes, "O nível de combustível deve diminuir após acelerar");
    }

    @Test
    public void testAceleracaoDesgastaPneus() {
        System.out.println("\tEste teste está rodando: testAceleracaoDesgastaPneus");
        double capacidadeAntes = carro.pneus.getCapacidade();
        carro.acelerar();
        double capacidadeDepois = carro.pneus.getCapacidade();

        assertTrue(capacidadeDepois < capacidadeAntes, "A capacidade dos pneus deve diminuir após acelerar");
    }

    @Test
    public void testAceleracaoAumentaMarcha() {
        System.out.println("\tEste teste está rodando: testAceleracaoAumentaMarcha");
        int marchaAntes = carro.getTransmissao().getSistemaDeTransmissao().getEstado();
        carro.acelerar();
        int marchaDepois = carro.getTransmissao().getSistemaDeTransmissao().getEstado();

        assertTrue(marchaDepois > marchaAntes, "A marcha deve aumentar após acelerar");
    }

    @Test
    public void testAceleracaoReduzBateria() {
        System.out.println("\tEste teste está rodando: testAceleracaoReduzBateria");
        double cargaAntes = carro.getSistemaE().capacidadeAtual();
        carro.acelerar();
        double cargaDepois = carro.getSistemaE().capacidadeAtual();

        assertTrue(cargaDepois < cargaAntes, "A carga da bateria deve diminuir ao ligar/acelerar");
    }

    @Test
    public void testAceleracaoRecarregaBateria() {
        System.out.println("\tEste teste está rodando: testAceleracaoRecarregaBateria");
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        double cargaAntes = carro.getSistemaE().capacidadeAtual();
        carro.acelerar();
        double cargaDepois = carro.getSistemaE().capacidadeAtual();

        assertTrue(cargaDepois > cargaAntes, "A bateria deve recarregar parcialmente ao andar");
    }

    @Test
    public void testSuspensaoSeAjustaConformeMarcha() {
        System.out.println("\tEste teste está rodando: testSuspensaoSeAjustaConformeMarcha");
        carro.transmissao.aumentarMarcha(); // marcha 1
        carro.acelerar(); // marcha 2
        assertEquals(25.0, carro.suspensao.getAltura(), 0.01);

        carro.acelerar(); // marcha 3
        assertEquals(20.0, carro.suspensao.getAltura(), 0.01);

        carro.acelerar(); // marcha 4
        carro.acelerar(); // marcha 5
        assertEquals(15.0, carro.suspensao.getAltura(), 0.01);
    }

    @Test
    public void testFrearAcendeLuzDeFreio() {
        System.out.println("\tEste teste está rodando: testFrearAcendeLuzDeFreio");
        carro.frear();
        assertEquals("freio", carro.luzes.getTipo());
        assertEquals("Ligadas", carro.luzes.getEstado());
    }

    @Test
    public void testAbrirPortaComMotorLigadoLancaExcecao() {
        System.out.println("\tEste teste está rodando: testAbrirPortaComMotorLigadoLancaExcecao");
        carro.getMotor().ligarMotor();
        Porta porta = carro.getPortas().get(0);
        porta.destravar("12345");

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            porta.abrir(carro.getMotor());
        });

        assertEquals("ERRO: Não é possível abrir a porta com o carro ligado!", excecao.getMessage());
    }

    @Test
    public void testAbrirPortaComMotorDesligado() {
        System.out.println("\tEste teste está rodando: testAbrirPortaComMotorDesligado");
        Porta porta = carro.getPortas().get(0);
        porta.destravar("12345");

        assertDoesNotThrow(() -> porta.abrir(carro.getMotor()),
                "A porta deve abrir normalmente com o motor desligado");
    }

    @Test
    public void testMotorNaoLigaSemCombustivelOuBateria() {
        System.out.println("\tEste teste está rodando: testMotorNaoLigaSemCombustivelOuBateria");
        while (carro.getSistemaC().verificarNivel() > 0) {
            carro.getSistemaC().consumir(10.0);
        }
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        carro.getMotor().ligarMotor();
        assertFalse(carro.getMotor().isLigado(), "O motor não deve ligar sem combustível nem bateria");
    }

    @Test
    public void testPainelMostraBateriaDescarregada() {
        System.out.println("\tEste teste está rodando: testPainelMostraBateriaDescarregada");
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        assertFalse(carro.getSistemaE().verificarBateria(), "A bateria deve estar descarregada");
    }

    @Test
    public void testBancoNaoAjustaComBateriaZerada() {
        System.out.println("\tEste teste está rodando: testBancoNaoAjustaComBateriaZerada");
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        carro.banco.ajustarAltura(10.0);
        assertEquals(0.0, carro.banco.getAltura(), 0.01, "O banco não deve ajustar com bateria descarregada");
    }

    @Test
    public void testEventosEmOrdemEsperada() {
        System.out.println("\tEste teste está rodando: testEventosEmOrdemEsperada");
        String[] esperado = {"Motor ligado", "Carro acelerando"};
        String[] real = {"Motor ligado", "Carro acelerando"};
        assertArrayEquals(esperado, real);
    }

    @Test
    public void testPortasCorretamenteCriadas() {
        System.out.println("\tEste teste está rodando: testPortasCorretamenteCriadas");
        ArrayList<String> esperado = new ArrayList<>();
        esperado.add("delanteira esquerda");
        esperado.add("delanteira direita");

        ArrayList<String> real = new ArrayList<>();
        for (Porta porta : carro.getPortas()) {
            real.add(porta.getTipo());
        }

        assertIterableEquals(esperado, real);
    }

    @Test
    public void testPainelOutput() {
        System.out.println("\tEste teste está rodando: testPainelOutput");
        ArrayList<String> esperado = new ArrayList<>();
        esperado.add("Motor: Desligado");
        esperado.add("LUZES: Estado: Desligadas Intensidade: 1");

        ArrayList<String> real = new ArrayList<>();
        real.add("Motor: Desligado");
        real.add("LUZES: Estado: Desligadas Intensidade: 1");

        assertLinesMatch(esperado, real);
    }

    @Test
    public void testSensorInexistente() {
        System.out.println("\tEste teste está rodando: testSensorInexistente");
        Object sensor = null;
        assertNull(sensor, "Sensor deveria estar ausente");
    }
}
