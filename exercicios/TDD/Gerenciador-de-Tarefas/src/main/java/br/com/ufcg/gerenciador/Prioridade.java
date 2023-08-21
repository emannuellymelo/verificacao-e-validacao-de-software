package br.com.ufcg.gerenciador;

public enum Prioridade {
    ALTA("Alta", 0),
    MEDIA("Media", 1),
    BAIXA("Baixa", 2);

    private final String stringValue;
    private final Integer intValue;
    Prioridade(String value, Integer intValue) {
        this.stringValue = value;
        this.intValue = intValue;
    }
    public String getStringValue() {
        return stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }
}
