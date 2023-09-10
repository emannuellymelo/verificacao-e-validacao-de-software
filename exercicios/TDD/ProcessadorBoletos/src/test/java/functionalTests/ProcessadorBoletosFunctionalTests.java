package functionalTests;
import org.junit.Test;
import br.com.ufcg.model.Fatura;
import br.com.ufcg.model.ProcessadorBoletos;
import br.com.ufcg.model.Boleto;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorBoletosFunctionalTests {
    
    // Testes de partição por equivalência
    @Test
    public void testCT1Particao(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";       
        LocalDate data = LocalDate.parse(dataString, formatter);
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
    public void testCT2Particao(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";       
        LocalDate data = LocalDate.parse(dataString, formatter);
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
    public void testCT3Particao(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";       
        LocalDate data = LocalDate.parse(dataString, formatter);
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
    public void testCT1Tabela(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";       
        LocalDate data = LocalDate.parse(dataString, formatter);
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
    public void testCT2Tabela(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "10/09/2023";       
        LocalDate data = LocalDate.parse(dataString, formatter);
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

}
