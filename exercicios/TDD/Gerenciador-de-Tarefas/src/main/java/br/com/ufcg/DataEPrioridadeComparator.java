package br.com.ufcg;

import java.util.Comparator;

public class DataEPrioridadeComparator implements Comparator<Tarefa> {
    @Override
    public int compare(Tarefa tarefa1, Tarefa tarefa2) {
        int resultado = tarefa1.getValidade().compareTo(tarefa2.getValidade());
        if (resultado == 0) {
            return Integer.compare(tarefa1.getPrioridadePoints(), tarefa2.getPrioridadePoints());
        }
        return resultado;
    }
}
