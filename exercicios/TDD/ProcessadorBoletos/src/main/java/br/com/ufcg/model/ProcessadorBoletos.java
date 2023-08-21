package br.com.ufcg.model;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorBoletos {
    private Fatura fatura;
    private List<Boleto> pagamentos;

    public ProcessadorBoletos(Fatura fatura){
        this.fatura = fatura;
        this.pagamentos = new ArrayList<>();
    }

    public void processarBoletos(List<Boleto> boletos){
        double valorPago = this.fatura.getValorPago();
        double valorBoletos = 0;
        for(Boleto boleto: boletos){
            boleto.setTipoPagamento(TipoPagamento.BOLETO);
            this.pagamentos.add(boleto);
            valorBoletos += boleto.getValor();
        }
        this.fatura.setValorPago(valorPago+valorBoletos);
        if(fatura.getValorPago() >= fatura.getValor()){
            fatura.setStatus("PAGA");
        }
    }

    public List<Boleto> getPagamentos(){
        return this.pagamentos;
    }

}
