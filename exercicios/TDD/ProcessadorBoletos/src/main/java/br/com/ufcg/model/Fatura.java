package br.com.ufcg.model;
import java.time.LocalDate;

public class Fatura {
    private LocalDate data;
    private double valor;
    private double valorPago;
    private String nomeCliente;

    public Fatura(LocalDate data, double valor, String nomeCliente){
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.valorPago = 0;
    }
 
    public LocalDate getData(){
        return this.data;
    }

    public double getValor(){
        return this.valor;
    }

    public String getNomeCliente(){
        return this.nomeCliente;
    }

    public double getValorPago(){
        return this.valorPago;
    }

    public void setPago(double valorPago){
        this.valorPago = valorPago;
    }

    public boolean isPago(){
        return valorPago >= valor;
    }
}
