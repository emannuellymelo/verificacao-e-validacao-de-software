package br.com.ufcg.gerenciador;

import java.util.Comparator;

public class DataEPrioridadeComparator implements Comparator<Tarefa> {
    @Override
    public int compare(Tarefa tarefa1, Tarefa tarefa2) {
        int resultado = tarefa1.getVencimento().compareTo(tarefa2.getVencimento());
        int COMPARA_MESMA_DATA = 0;
        if (resultado == COMPARA_MESMA_DATA) {
            return Integer.compare(tarefa1.getPontosPrioridade(), tarefa2.getPontosPrioridade());
        }
        return resultado;
    }
}
