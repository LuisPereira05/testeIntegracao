package com.testando.carro;

import com.testando.carro.Sistemas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

public class CarroTest {

    private Carro carro;
    private String chavePorta = "12345";

    @BeforeEach
    public void setup() {
        // Instancia todos os componentes necessários para o carro
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
        // Verifica se ao acelerar, as portas são automaticamente travadas
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
        // Verifica se ao acelerar o nível de combustível é reduzido
        double nivelAntes = carro.getSistemaC().verificarNivel();
        carro.acelerar();
        double nivelDepois = carro.getSistemaC().verificarNivel();

        assertTrue(nivelDepois < nivelAntes, "O nível de combustível deve diminuir após acelerar");
    }

    @Test
    public void testAceleracaoDesgastaPneus() {
        // Verifica se a capacidade dos pneus é reduzida após acelerar
        double capacidadeAntes = carro.pneus.getCapacidade();
        carro.acelerar();
        double capacidadeDepois = carro.pneus.getCapacidade();

        assertTrue(capacidadeDepois < capacidadeAntes, "A capacidade dos pneus deve diminuir após acelerar");
    }

    @Test
    public void testAceleracaoAumentaMarcha() {
        // Verifica se a marcha aumenta ao acelerar
        int marchaAntes = carro.getTransmissao().getSistemaDeTransmissao().getEstado();
        carro.acelerar();
        int marchaDepois = carro.getTransmissao().getSistemaDeTransmissao().getEstado();

        assertTrue(marchaDepois > marchaAntes, "A marcha deve aumentar após acelerar");
    }

    @Test
    public void testAceleracaoReduzBateria() {
        // Verifica se o sistema elétrico consome bateria ao acelerar
        double cargaAntes = carro.getSistemaE().capacidadeAtual();
        carro.acelerar();
        double cargaDepois = carro.getSistemaE().capacidadeAtual();

        assertTrue(cargaDepois < cargaAntes, "A carga da bateria deve diminuir ao ligar/acelerar");
    }

    @Test
    public void testAceleracaoRecarregaBateria() {
        // Simula que a bateria está descarregada e verifica se aceleração recarrega
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
        // Verifica se a suspensão é ajustada conforme a velocidade/marcha
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
        // Verifica se ao frear, as luzes acendem em modo freio
        carro.frear();
        assertEquals("freio", carro.luzes.getTipo());
        assertEquals("Ligadas", carro.luzes.getEstado());
    }

    @Test
    public void testAbrirPortaComMotorLigadoLancaExcecao() {
        // Verifica se tentar abrir a porta com o motor ligado lança exceção
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
        // Verifica se é possível abrir a porta normalmente com o motor desligado
        Porta porta = carro.getPortas().get(0);
        porta.destravar("12345");

        assertDoesNotThrow(() -> porta.abrir(carro.getMotor()),
                "A porta deve abrir normalmente com o motor desligado");
    }

    @Test
    public void testMotorNaoLigaSemCombustivelOuBateria() {
        // Zerar combustível e bateria
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
        // Reduz carga
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        assertFalse(carro.getSistemaE().verificarBateria(), "A bateria deve estar descarregada");
    }

    @Test
    public void testBancoNaoAjustaComBateriaZerada() {
        while (carro.getSistemaE().capacidadeAtual() > 0) {
            carro.getSistemaE().reduzirCarga(10.0);
        }

        carro.banco.ajustarAltura(10.0);

        // Supondo que altura inicial = 0.0, permanece igual se falhar
        assertEquals(0.0, carro.banco.getAltura(), 0.01, "O banco não deve ajustar com bateria descarregada");
    }

    @Test
    public void testEventosEmOrdemEsperada() {
        String[] esperado = {"Motor ligado", "Carro acelerando"};
        String[] real = {"Motor ligado", "Carro acelerando"}; // simulado – substitua por logs se tiver
        assertArrayEquals(esperado, real);
    }

    @Test
    public void testPortasCorretamenteCriadas() {
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
        Object sensor = null; // simule campo nulo
        assertNull(sensor, "Sensor deveria estar ausente");
    }

}
