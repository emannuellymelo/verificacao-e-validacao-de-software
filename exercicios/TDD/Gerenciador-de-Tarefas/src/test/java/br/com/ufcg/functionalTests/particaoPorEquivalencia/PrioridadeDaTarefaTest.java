package br.com.ufcg.functionalTests.particaoPorEquivalencia;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrioridadeDaTarefaTest {

    @Test
    public void CT1() {
        Usuario user = new Usuario("User1");
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "10/12/2023", "Alta");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT2() {
        Usuario user = new Usuario("User2");
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "12/12/2023", "Media");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT3() {
        Usuario user = new Usuario("User3");
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "15/12/2023", "Baixa");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT4() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "05/12/2023", "Importante");
            fail("Deveria ter interrompido a criacao de uma atividade com prioridade diferente de alta, media ou baixa");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }
}
