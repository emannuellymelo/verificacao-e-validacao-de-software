package br.com.ufcg.junit5Tests.functionalTestsRefactor.particaoPorEquivalencia;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PrioridadeDaTarefaTest {
    private Usuario user;

    @BeforeEach
    public void setUp() {
        user = new Usuario("User");
    }

    @Test
    public void CT1() {
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "10/12/2023", "Alta");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT2() {
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "12/12/2023", "Media");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT3() {
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
