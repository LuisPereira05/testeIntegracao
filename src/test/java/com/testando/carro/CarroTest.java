/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.testando.carro;

import com.testando.carro.Sistemas.SistemaEletrico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abner
 */
public class CarroTest {

    Carro carro;
    Motor motor;
    Transmissao transmissao;
    SistemaEletrico sistemaEletrico;
    Painel painel;

    public CarroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        carro = new Carro();
        motor = new Motor();
        transmissao = new Transmissao();
        sistemaEletrico = new SistemaEletrico();
        painel = new Painel();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of acelerar method, of class Carro.
     */
    @Test
    public void testAcelerar() {
        motor.ligarMotor();
        transmissao.aumentarMarcha();
        sistemaEletrico.ativarParteEletrica();

        // Exibir o status no painel
        String status = painel.exibirStatus();

        // Verificar se o status exibido é o esperado
        String statusEsperado = "Motor ligado\nTransmissão ativa\nSistema elétrico funcionando\nPronto para acelerar o carro";
        assertEquals(statusEsperado, status);
    }


}
