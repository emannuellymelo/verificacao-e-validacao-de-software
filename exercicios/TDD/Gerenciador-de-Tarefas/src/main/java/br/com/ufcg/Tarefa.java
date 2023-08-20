package br.com.ufcg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private enum Prioridade {
        ALTA("Alta", 0), MEDIA("Media", 1), BAIXA("Baixa", 2);

        private final String stringValue;
        private final Integer intValue;
        Prioridade(String value, Integer intValue) {
            this.stringValue = value;
            this.intValue = intValue;
        }
        public String getValue() {
            return stringValue;
        }

        public Integer getIntValue() {
            return intValue;
        }
    }

    private String titulo;
    private String descricao;
    private LocalDate validade;
    private Prioridade prioridade;
    private String codigo;

    public Tarefa(String titulo, String descricao, String validade, String prioridade, String codigo) {
        this.checkTituloECodigo(titulo, codigo);
        this.checkPrioridade(prioridade);
        this.checkValidade(validade);
        this.descricao = descricao;
    }

    private void checkTituloECodigo(String titulo, String codigo) {
        if(titulo.trim().equals("")) {
            throw new IllegalArgumentException("Titulo Invalido! Atribua um titulo para sua tarefa.");
        } else if(codigo.trim().equals("")) {
            throw new IllegalArgumentException("Codigo Invalido! Atribua um codigo para sua tarefa");
        } else {
            this.titulo = titulo;
            this.codigo = codigo;
        }
    }

    private void checkPrioridade(String prioridade) {
        prioridade = prioridade.toLowerCase().trim();
        if(prioridade.equals("alta") || prioridade.equals("media") || prioridade.equals("baixa")) {
            this.prioridade = getCorrespondentPrioridade(prioridade);
        } else {
            throw new IllegalArgumentException("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.");
        }
    }

    private void checkValidade(String validade) {
        LocalDate dataAtual = LocalDate.now();
        getCorrespondentData(validade);
        int resultado = dataAtual.compareTo(this.validade);

        if(resultado > 0) {
            throw new IllegalArgumentException("Data Invalida! Atribua uma validade igual ou maior ao dia atual.");
        }
    }

    private void getCorrespondentData(String validade) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.validade = LocalDate.parse(validade, dateFormat);
    }

    private Prioridade getCorrespondentPrioridade(String prioridade) {
        Prioridade prioridadeEnum = Prioridade.BAIXA;
        if(prioridade.equals("alta")) {
            prioridadeEnum = Prioridade.ALTA;
        } else if(prioridade.equals("media")) {
            prioridadeEnum = Prioridade.MEDIA;
        } return prioridadeEnum;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        getCorrespondentData(validade);
    }

    public String getPrioridade() {
        return prioridade.getValue();
    }

    public void setPrioridade(String prioridade) {
        this.checkPrioridade(prioridade);
    }

    public Integer getPrioridadePoints() {
        return prioridade.getIntValue();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
