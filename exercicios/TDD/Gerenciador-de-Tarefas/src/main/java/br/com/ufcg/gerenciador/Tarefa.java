package br.com.ufcg.gerenciador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static br.com.ufcg.gerenciador.Util.alertaAtributoInvalido;
import static br.com.ufcg.gerenciador.Util.isEmpty;

public class Tarefa {

    private String titulo;
    private String descricao;
    private LocalDate vencimento;
    private Prioridade prioridade;
    private String codigo;

    public Tarefa(String titulo, String descricao, String vencimento, String prioridade, String codigo) {
        this.checkTituloECodigo(titulo, codigo);
        this.checkPrioridade(prioridade);
        this.checkVencimento(vencimento);
        this.descricao = descricao;
    }

    private void checkTituloECodigo(String titulo, String codigo) {
        String mensagemTituloInvalido = "Titulo Invalido! Atribua um titulo para sua tarefa.";
        String mensagemCodigoInvalido = "Codigo Invalido! Atribua um codigo para sua tarefa";
        if(isEmpty(titulo)) {
            alertaAtributoInvalido(mensagemTituloInvalido);
        } else if(isEmpty(codigo)) {
            alertaAtributoInvalido(mensagemCodigoInvalido);
        } else {
            this.titulo = titulo;
            this.codigo = codigo;
        }
    }

    private void checkPrioridade(String prioridade) {
        String mensagemPrioridadeInvalida = "Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.";
        prioridade = prioridade.toLowerCase().trim();
        if(prioridade.equals(Prioridade.ALTA.getStringValue().toLowerCase()) ||
                prioridade.equals(Prioridade.MEDIA.getStringValue().toLowerCase())
                || prioridade.equals(Prioridade.BAIXA.getStringValue().toLowerCase())) {
            this.prioridade = getCorrespondentPrioridade(prioridade);
        } else {
            alertaAtributoInvalido(mensagemPrioridadeInvalida);
        }
    }

    private void checkVencimento(String vencimento) {
        String mensagemDataInvalida = "Data Invalida! Atribua uma validade igual ou maior ao dia atual.";
        LocalDate dataAtual = LocalDate.now();
        getCorrespondentData(vencimento);
        int resultado = dataAtual.compareTo(this.vencimento);
        if(resultado > 0) {
            alertaAtributoInvalido(mensagemDataInvalida);
        }
    }

    private void getCorrespondentData(String vencimento) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.vencimento = LocalDate.parse(vencimento, dateFormat);
    }

    private Prioridade getCorrespondentPrioridade(String prioridade) {
        Prioridade prioridadeEnum = Prioridade.BAIXA;
        if(prioridade.equals(Prioridade.ALTA.getStringValue().toLowerCase())) {
            prioridadeEnum = Prioridade.ALTA;
        } else if(prioridade.equals(Prioridade.MEDIA.getStringValue().toLowerCase())) {
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

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        getCorrespondentData(vencimento);
    }

    public String getPrioridade() {
        return prioridade.getStringValue();
    }

    public void setPrioridade(String prioridade) {
        this.checkPrioridade(prioridade);
    }

    public Integer getPontosPrioridade() {
        return prioridade.getIntValue();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
