package br.com.ufcg.model;

import java.time.LocalDate;

public class Boleto extends Pagamento{
    private long codigo;
    
    public Boleto(LocalDate data, double valor, String tipoPagamento, long codigo){
        super(data,valor,tipoPagamento);
        this.codigo = codigo;
    }
    public long getCodigo(){
        return codigo;
    }
    
}
