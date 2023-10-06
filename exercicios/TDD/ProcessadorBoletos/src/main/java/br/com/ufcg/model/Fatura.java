package br.com.ufcg.model;
import java.time.LocalDate;

public class Fatura {
    private LocalDate data;
    private double valor;
    private double valorPago;
    private String nomeCliente;
    private String status;

    public Fatura(LocalDate data, double valor, String nomeCliente){
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.valorPago = 0;
        this.status = "NAO_PAGA";
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

    public void setValorPago(double valorPago){
        this.valorPago = valorPago;
    }

    public boolean isPago(){
        return this.getStatus().equals("PAGA");
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
