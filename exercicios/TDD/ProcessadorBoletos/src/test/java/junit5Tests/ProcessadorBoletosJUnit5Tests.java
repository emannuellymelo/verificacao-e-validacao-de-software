package junit5Tests;
import br.com.ufcg.model.TipoPagamento;
import org.junit.Test;
import br.com.ufcg.model.Fatura;
import br.com.ufcg.model.ProcessadorBoletos;
import br.com.ufcg.model.Boleto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorBoletosJUnit5Tests {

    LocalDate data;
    
    @BeforeEach
    public void setUp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";
        this.data = LocalDate.parse(dataString, formatter);
    }

    // Testes de partição por equivalência
    @Test
    @DisplayName("Caso de teste 1 da suíte de testes de partição por equivalência")
    public void testCT1Particao(){
        Fatura fatura = new Fatura(data,1000,"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,333,1);
        Boleto boleto2 = new Boleto(data,333,2);
        Boleto boleto3 = new Boleto(data,333,3);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        boletos.add(boleto3);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.getValorPago() == 999);
        assertFalse(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 3);
    }

    @Test
    @DisplayName("Caso de teste 2 da suíte de testes de partição por equivalência")
    public void testCT2Particao(){
        Fatura fatura = new Fatura(data,1000,"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,333,1);
        Boleto boleto2 = new Boleto(data,333,2);
        Boleto boleto3 = new Boleto(data,334,3);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        boletos.add(boleto3);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.getValorPago() == 1000);
        assertTrue(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 3);
    }

    @Test
    @DisplayName("Caso de teste 3 da suíte de testes de partição por equivalência")
    public void testCT3Particao(){
        Fatura fatura = new Fatura(data,1000,"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,333,1);
        Boleto boleto2 = new Boleto(data,334,2);
        Boleto boleto3 = new Boleto(data,334,3);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        boletos.add(boleto3);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.getValorPago() == 1001);
        assertTrue(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 3);
    }

    // Testes de tabela de decisão

    @Test
    @DisplayName("Caso de teste 1 da suíte de testes de tabela de decisão")
    public void testCT1Tabela(){
        Fatura fatura = new Fatura(data,300,"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,100,1);
        Boleto boleto2 = new Boleto(data,100,2);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.getValorPago() == 200);
        assertFalse(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 2);
    }

    @Test
    @DisplayName("Caso de teste 2 da suíte de testes de tabela de decisão")
    public void testCT2Tabela(){
        Fatura fatura = new Fatura(data,500,"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,500,1);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.getValorPago() == 500);
        assertTrue(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 1);
    }

    @Test
    @DisplayName("Caso de teste 1 da suíte de testes de tabela de decisão (extendido)")
    public void testCT1ParticaoExtendido(){
        // Agora testes cobrem 100% de linhas de código
        Fatura fatura = new Fatura(data,1000,"Adrian");
        assertEquals(fatura.getData(),this.data);
        assertEquals(fatura.getNomeCliente(),"Adrian");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,333,1);
        assertEquals(boleto1.getCodigo(),1);
        assertEquals(boleto1.getData(),this.data);
        Boleto boleto2 = new Boleto(data,333,2);
        Boleto boleto3 = new Boleto(data,333,3);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        boletos.add(boleto3);
        processadorBoletos.processarBoletos(boletos);
        assertEquals(boleto1.getTipoPagamento(), TipoPagamento.BOLETO);
        assertTrue(fatura.getValorPago() == 999);
        assertFalse(fatura.isPago());
        assertTrue(processadorBoletos.getPagamentos().size() == 3);
    }

    @Test
    @DisplayName("Verificação de estado inválido do status do boleto antes de processamento")
    public void testInvalidStatusBoleto(){
        Boleto boleto1 = new Boleto(data,333,1);
        assertThrows(NullPointerException.class,
                     ()->{
                        TipoPagamento tipo = boleto1.getTipoPagamento();
                        System.out.println("O tipo de pagamento é " + tipo.toString());
                     });

    }

}
