import org.junit.Test;
import br.com.ufcg.model.Fatura;
import br.com.ufcg.model.ProcessadorBoletos;
import br.com.ufcg.model.Boleto;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorBoletosTest {

    @Test
    public void testCriarProcessadorBoletos(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "01/01/2020";       
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura(data,666.69,"Laryssa");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
    }

    @Test
    public void testAdicionaBoletosPagouFatura(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "01/01/2021";       
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura(data,1500,"Diego");
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos(fatura);
        Boleto boleto1 = new Boleto(data,500,1);
        Boleto boleto2 = new Boleto(data,400,1);
        Boleto boleto3 = new Boleto(data,600,1);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);
        boletos.add(boleto3);
        processadorBoletos.processarBoletos(boletos);
        assertTrue(fatura.isPago());
    }
    
}
