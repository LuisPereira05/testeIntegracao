package com.testando.carro;

import com.testando.carro.Sistemas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CarroTest {

    private Carro carro;
    private String chavePorta = "12345";

    @BeforeEach
    public void setup() {
        SistemaDeCombustivel sistemaC = new SistemaDeCombustivel("Gasolina", 75.0, 0.0, "GM", true);
        SistemaEletrico sistemaE = new SistemaEletrico(12.0, 48.0, "AGM", true, "HAGEN");
        SistemaDeTransmissao sistemaT = new SistemaDeTransmissao("Sequencial", 6, "Aço", "Koenigsegg", 0);

        Motor motor = new Motor(sistemaC, sistemaE, "V10", 1200, 5.0, false, "Otimo", "Aço", "Bugatti");
        Freios freios = new Freios("ABS", 12.0, 0.0, "Desativados", "Ceramica", "Volvo");
        Luzes luzes = new Luzes(sistemaE, "LED", 1, "branco", "Fisheye", "Vidro", "Phillips");
        Painel painel = new Painel(sistemaE, "Desligado", "Vidro", "Philips", "Smart", "LED", false);
        Pneus pneus = new Pneus("15x6", "tuner", 40.0, 38.0, "aluminio", "Chevrolet");
        Suspensao suspensao = new Suspensao(sistemaE, "esportivo", 20.0, 2, "nova", "fibra de carbono", "Ferrari");
        Transmissao transmissao = new Transmissao(sistemaT, 0, "Otimo");

        ArrayList<Porta> portas = new ArrayList<>();
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira esquerda", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira direita", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));

        carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaE, sistemaT, motor, freios, luzes, painel, pneus, portas, suspensao, transmissao);
    }

    // ==== TESTES PORTA ====

    @Test
    public void testPortaAbrirFechar() {
        carro.getPortas().get(0).destravar(chavePorta);
        carro.getPortas().get(0).abrir();
        assertEquals("Aberta", carro.getPortas().get(0).estado);

        carro.getPortas().get(0).fechar();
        assertEquals("Fechada", carro.getPortas().get(0).estado);
    }

    @Test
    public void testPortaDestravarErrado() {
        // Tentar destravar com chave errada lança exceção (exemplo)
        assertThrows(SecurityException.class, () -> {
            carro.getPortas().get(0).destravar("chaveErrada");
        });
    }


    // ==== TESTES MOTOR ====

    @Test
    public void testMotorLigarAcelerar() {
        carro.getMotor().ligarMotor();
        assertTrue(carro.getMotor().ligado);

        carro.acelerar();
        // Nível de combustível diminuiu
        assertTrue(carro.getSistemaC().verificarNivel() < 75.0);
    }

    @Test
    public void testMotorTimeoutOperacao() {
        // Testa que acelerar não demora mais que 1 segundo (simulando demora)
        assertTimeout(Duration.ofSeconds(1), () -> {
            carro.acelerar();
        });
    }

    // ==== TESTES PAINEL ====

    @Test
    public void testPainelLigarAtualizar() {
        carro.getPainel().ligarDisplay();
        assertTrue(carro.getPainel().getLigado());

        carro.getPainel().atualizarInformacoes("Velocidade: 100km/h");
        assertTrue(carro.getPainel().exibirStatus().contains("Velocidade: 100km/h"));
    }

    @Test
    public void testPainelLinhasStatus() {
        carro.getPainel().ligarDisplay();
        carro.getPainel().atualizarInformacoes("Velocidade: 100km/h\nCombustível: 50%");
        List<String> linhasEsperadas = List.of("Velocidade: 100km/h", "Combustível: 50%");
        // Transformar a string do painel em lista de linhas para comparar
        List<String> linhasAtuais = List.of(carro.getPainel().exibirStatus().split("\n"));
        assertIterableEquals(linhasEsperadas, linhasAtuais);
    }

    // ==== TESTES TRANSMISSAO ====

    @Test
    public void testTransmissaoTrocarMarcha() {
        carro.getTransmissao().aumentarMarcha();
        assertEquals(1, carro.getTransmissao().getSistemaDeTransmissao().getEstado());

        carro.getTransmissao().aumentarMarcha();
        assertEquals(2, carro.getTransmissao().getSistemaDeTransmissao().getEstado());

        carro.getTransmissao().diminuirMarcha();
        assertEquals(1, carro.getTransmissao().getSistemaDeTransmissao().getEstado());
    }

    @Test
    public void testTransmissaoEstadosIteravel() {
        // Checa se a lista das marchas atuais é iterável e igual à esperada
        List<Integer> marchasEsperadas = List.of(0, 1, 2);
        List<Integer> marchasAtuais = new ArrayList<>();
        marchasAtuais.add(0);
        carro.getTransmissao().aumentarMarcha();
        marchasAtuais.add(carro.getTransmissao().getSistemaDeTransmissao().getEstado());
        carro.getTransmissao().aumentarMarcha();
        marchasAtuais.add(carro.getTransmissao().getSistemaDeTransmissao().getEstado());

        assertIterableEquals(marchasEsperadas, marchasAtuais);
    }

}


