package br.com.ufcg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    private String nome;
    private ArrayList<Tarefa> tarefas;

    public Usuario(String nome) {
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public void addTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public void editTarefa(String titulo, String descricao, String validade, String prioridade, String codigo) {
        for(Tarefa t : tarefas) {
            if(t.getCodigo().equals(codigo)) {
                t.setTitulo(titulo);
                t.setDescricao(descricao);
                t.setPrioridade(prioridade);
                t.setValidade(validade);
            }
        }
    }

    public void removeTarefa(String codigo) {
        for(Tarefa t : tarefas) {
            if(t.getCodigo().equals(codigo)) {
                tarefas.remove(t);
                break;
            }
        }
    }

    public ArrayList<Tarefa> getTarefas() {
        Collections.sort(this.tarefas, new DataEPrioridadeComparator());
        return tarefas;
    }
}
