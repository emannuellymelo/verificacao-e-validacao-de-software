package br.com.ufcg.model;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorBoletos {
    private Fatura fatura;
    private List<Pagamento> pagamentos;

    public ProcessadorBoletos(Fatura fatura){
        this.fatura = fatura;
        this.pagamentos = new ArrayList<>();
    }
    
}
