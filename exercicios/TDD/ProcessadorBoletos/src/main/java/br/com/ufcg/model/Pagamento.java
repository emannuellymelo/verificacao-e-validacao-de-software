package br.com.ufcg.model;
import java.time.LocalDate;

public abstract class Pagamento {
    private LocalDate data;
    private double valor;
    private String tipoPagamento;

    public Pagamento(LocalDate data, double valor, String tipoPagamento){
        this.data = data;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
    }

    public double getValor(){
        return valor;
    }

    public LocalDate getData(){
        return data;
    }

    public String getTipoPagamento(){
        return tipoPagamento;
    }
}
