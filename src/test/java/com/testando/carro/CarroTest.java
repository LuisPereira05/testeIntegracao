package com.testando.carro;

import com.testando.carro.Sistemas.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        Banco banco = new Banco(sistemaE, "Normal", "Coro", "Volvo", "Vermelho", "Comfort");
        Pneus pneus = new Pneus("15x6", "tuner", 40.0, 38.0, "aluminio", "Chevrolet");
        Suspensao suspensao = new Suspensao(sistemaE, "esportivo", 20.0, 2, "nova", "fibra de carbono", "Ferrari");
        Transmissao transmissao = new Transmissao(sistemaT, 0, "Otimo");

        ArrayList<Porta> portas = new ArrayList<>();
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira esquerda", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));
        portas.add(new Porta(sistemaE, "Vermelho", "delanteira direita", true, "Fechada", "fibra de carbono", "Volvo", chavePorta));

        carro = new Carro("ML", 1999, "Vermelho", "C4RR1NH0", 0, sistemaC, sistemaE, sistemaT, motor, freios, luzes, painel, banco, pneus, portas, suspensao, transmissao);
    }

    // Porta

    @Test
    public void testPortaDestravarComChaveCorreta() {
        
        carro.portas.get(0).destravar("12345");
        assertDoesNotThrow(() -> carro.portas.get(0).abrir());
    }

    @Test
    public void testPortaInicialmenteTravada() {
        Porta porta = carro.getPortas().get(1);

        RuntimeException exception = assertThrows(RuntimeException.class, porta::abrir);

        assertEquals("Porta está travada e não pode ser aberta", exception.getMessage());
    }




    // Motor

    @Test
    public void testMotorLigarAcelerar() {
        carro.getMotor().ligarMotor();
        assertFalse(carro.getMotor().ligado);

        carro.acelerar();

        assertTrue(carro.getSistemaC().verificarNivel() < 75.0);
    }

    @Test
    public void testMotorTimeoutOperacao() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            carro.acelerar();
        });
    }

    // Painel

    @Test
    public void testPainelLigarDisplayNaoLancaExcecao() {
        assertDoesNotThrow(() -> carro.getPainel().ligarDisplay());
    }

    @Test
    public void testPainelDisplayIniciaDesligado() {
        assertFalse(carro.getPainel().getLigado());
    }


    // Transmissão

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
        List<Integer> marchasEsperadas = List.of(0, 1, 2);
        List<Integer> marchasAtuais = new ArrayList<>();
        marchasAtuais.add(0);
        carro.getTransmissao().aumentarMarcha();
        marchasAtuais.add(carro.getTransmissao().getSistemaDeTransmissao().getEstado());
        carro.getTransmissao().aumentarMarcha();
        marchasAtuais.add(carro.getTransmissao().getSistemaDeTransmissao().getEstado());

        assertIterableEquals(marchasEsperadas, marchasAtuais);
    }
    
    // Luzes
    
    @Test
    public void testLuzesEstadoInicial() {
        assertEquals("Desligadas", carro.luzes.estado);
    }

    @Test
    public void testLuzesTipoNaoNula() {
        assertNotNull(carro.luzes.getTipo());
    }
    
    // Pneus
    
    @Test
    public void testVerificarPressaoComAssertEquals() {
        
        assertEquals(38.0, carro.pneus.verificarPressao(), 0.01, "A pressão inicial deve ser 38.0");
    }

    @Test
    public void testAjustarPressaoExcedeCapacidadeComAssertNotEquals() {
        double pressaoOriginal = carro.pneus.verificarPressao(); // deve ser 38.0
        carro.pneus.ajustarPressao(45.0); // tenta passar do limite de 40.0
        double pressaoAposAjuste = carro.pneus.verificarPressao();

        // Verifica que a pressão não mudou, já que excede a capacidade
        assertNotEquals(45.0, pressaoAposAjuste, 
            "A pressão não deve ser ajustada para um valor maior que a capacidade");
    }

    
    // Banco

    @Test
    public void testAjustarEncostoComAssertEquals() {
        

        carro.banco.ajustarEncosto("Reclinado");

        assertEquals("Reclinado", carro.banco.estado);
    }

    @Test
    public void testAjustarAlturaComAssertTimeout() {

        assertTimeout(Duration.ofMillis(100), () -> {
            carro.banco.ajustarAltura(10.0);
        });
    }

    // Suspensão
    
    @Test
    public void testSuspensaoAjustarAlturaValida() {
        carro.suspensao.ajustarAltura(25.0);
        assertDoesNotThrow(() -> carro.suspensao.ajustarAltura(25.0));
    }

    @Test
    public void testSuspensaoTipoEsperado() {
        assertEquals("esportivo", carro.suspensao.getTipo());
    }

    // Freios
    
    @Test
    public void testSubstituirPastilhasZeraDesgaste() {
        Freios freios = new Freios("ABS", 12.0, 45.5, "Desgastado", "Cerâmica", "Brembo");
        freios.substituirPastilhas();
        assertEquals(0.0, freios.getNivelDeDesgaste(), 0.0001, "O nível de desgaste deve ser zerado após substituir as pastilhas.");
    }

    @Test
    public void testVerificarEstadoImpressao() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        carro.freios.verificarEstado();

        String saidaEsperada = "FREIOS: Nível de desgaste -> 12.0%";
        assertTrue(outContent.toString().trim().contains(saidaEsperada));

        System.setOut(System.out);
    }

    
}


