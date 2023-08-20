package br.com.ufcg.gerenciador;

public class Util {

    public static boolean isEmpty(String texto) {
        return texto.trim().equals("");
    }

    public static void alertaAtributoInvalido(String mensagem) {
        throw new IllegalArgumentException(mensagem);
    }
}
