package br.com.ufcg.model;
import java.time.LocalDate;

public abstract class Pagamento {
    private LocalDate data;
    private double valor;
    private TipoPagamento tipoPagamento;

    public Pagamento(LocalDate data, double valor){
        this.data = data;
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }

    public LocalDate getData(){
        return this.data;
    }

    public TipoPagamento getTipoPagamento(){
        return this.tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }
}
