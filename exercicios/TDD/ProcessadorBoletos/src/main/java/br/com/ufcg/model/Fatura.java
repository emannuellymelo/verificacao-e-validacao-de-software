package br.com.ufcg.model;
import java.time.LocalDate;

public class Fatura {
    private LocalDate data;
    private double valor;
    private String nomeCliente;

    public Fatura(LocalDate data, double valor, String nomeCliente){
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
    }
}
