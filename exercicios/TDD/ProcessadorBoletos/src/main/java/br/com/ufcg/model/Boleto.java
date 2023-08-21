package br.com.ufcg.model;

import java.time.LocalDate;

public class Boleto extends Pagamento{
    private long codigo;
    
    public Boleto(LocalDate data, double valor, long codigo){
        super(data,valor);
        this.codigo = codigo;
    }
    public long getCodigo(){
        return this.codigo;
    }
    
}
