package br.com.ufcg;
import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GerenciadorDeTarefasTest {

    @Test
    public void testTarefaSemNome() {
        try {
            Tarefa tarefa = new Tarefa("    ", "Atividade criada para testar a falta de titulo", "21/08/2023", "Alta", "1");
            fail("Deveria interromper criacao de atividade sem nome");
        } catch (IllegalArgumentException message) {
            assertEquals("Titulo Invalido! Atribua um titulo para sua tarefa.", message.getMessage());
        }
    }

    @Test
    public void testValidadeMenorQueDataAtual() {
        try {
            Tarefa tarefa = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD" , "15/08/2023", "Alta", "2");
            fail("Deveria interromper criacao de tarefa com validade menor que data atual");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida! Atribua uma validade igual ou maior ao dia atual.", message.getMessage());
        }
    }

    @Test
    public void testPrioridades() {
        try {
            Tarefa tarefa = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD", "25/10/2023", "Importante", "10");
            fail("Deveria ter interrompido a criacao de uma atividade com prioridade diferente de alta, media ou baixa");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }

    @Test
    public void testTarefaAdicionada() {
        Usuario user = new Usuario("User1");
        Tarefa tarefa = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD", "25/09/2023", "Alta", "3");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void testTarefaExcluida() {
        Usuario user = new Usuario("User2");
        Tarefa tarefa = new Tarefa("Fazer exercicio de VeV hoje", "Exercicio TDD", "25/09/2023", "Alta", "4");
        user.addTarefa(tarefa);
        user.removeTarefa("4");
        assertEquals(0, user.getTarefas().size());
    }

    @Test
    public void testTarefaEditada() {
        Usuario user = new Usuario("User3");
        Tarefa tarefa = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD", "25/09/2023", "Media", "5");
        user.addTarefa(tarefa);
        user.editTarefa("Fazer exercicio de VeV hoje", "Exercicio TDD", "25/09/2023", "Alta", "5");
        assertEquals("Alta", tarefa.getPrioridade());
        assertEquals("Fazer exercicio de VeV hoje", tarefa.getTitulo());
    }

    @Test
    public void testListagemMesmaDataPrioridadesDiferentes() {
        Usuario user = new Usuario("User4");
        Tarefa tarefa1 = new Tarefa("Fazer exercicio de BD", "Exercicio Diagrama", "25/10/2023", "Media", "6");
        Tarefa tarefa2 = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD", "25/10/2023", "Alta", "7");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Fazer exercicio de VeV", user.getTarefas().get(0).getTitulo());
    }

    @Test
    public void testListagemMesmaPrioridadeDatasDiferentes() {
        Usuario user = new Usuario("User5");
        Tarefa tarefa1 = new Tarefa("Fazer exercicio de BD", "Exercicio Diagrama", "25/10/2023", "Media", "8");
        Tarefa tarefa2 = new Tarefa("Fazer exercicio de VeV", "Exercicio TDD", "08/10/2023", "Media", "9");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Fazer exercicio de VeV", user.getTarefas().get(0).getTitulo());
    }

}
