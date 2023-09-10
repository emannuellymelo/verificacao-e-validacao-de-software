package br.com.ufcg.functionalTests.tabelaDeDecisao;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VencimentoDaTarefaTest {

    @Test
    public void CT1() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "01/13/2023", "Alta");
            fail("Deveria interromper criacao de tarefa com data de mes invalido");
        } catch (DateTimeParseException message) {
        }
    }

    @Test
    public void CT2_CT5_CT8_CT11_CT12_CT15_CT18() {
        Usuario user = new Usuario("User1");
        String[] monthsWith31Days = {"01", "03", "05", "07", "08", "10", "12"};

        for (String month : monthsWith31Days) {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "31/" + month + "/2024", "Alta");
            user.addTarefa(tarefa);
        }
        assertEquals(7, user.getTarefas().size());
    }

    @Test
    public void CT6_CT9_CT13_CT16() {
        Usuario user = new Usuario("User1");
        String[] monthsWith30Days = {"04", "06", "09", "11"};

        for (String month : monthsWith30Days) {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "30/" + month + "/2024", "Alta");
            user.addTarefa(tarefa);
        }
        assertEquals(4, user.getTarefas().size());
    }

    @Test
    public void CT3() {
        Usuario user = new Usuario("User1");
        Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "29/02/2024", "Media");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT4() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "30/02/2024", "Media");
            fail("Deveria interromper criacao de tarefa ao refenrecia o dia 30 de fevereiro");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT7_CT10_CT14_CT17() {
        String[] monthsWith30Days = {"04", "06", "09", "11"};
        try {
            for (String month : monthsWith30Days) {
                Tarefa tarefa = new Tarefa("Tarefa", "Exercicio", "31/" + month + "/2024", "Alta");
            }
            fail("Deveria interromper criacao de tarefa ao referenciar o dia 31 de um mês com 30 dias");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT19() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "29/02/2024", "Media");
            fail("Deveria interromper criacao de tarefa em 29/02 fora de um ano bissexto");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }


}
