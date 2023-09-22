package br.com.ufcg.junit5Tests.suiteExercicio4;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CriacaoDeTarefasTest {

    private Usuario user;
    @BeforeEach
    public void setUp() {
        user = new Usuario("user");
    }
    @Test
    public void CT1() {
        try {
            Tarefa tarefa = new Tarefa("", "Exercício", "10/12/23", "Alta");
            fail("Deveria interromper criacao de atividade sem nome");
        } catch (IllegalArgumentException message) {
            assertEquals("Titulo Invalido! Atribua um titulo para sua tarefa.", message.getMessage());
        }
    }

    @Test
    public void CT2() {
        Tarefa tarefa = new Tarefa("Tarefa", "", "10/12/2023", "Media");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/12/2023", "10//2023", "10/12/"})
    public void CT3_CT4_CT5(String date) {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , date, "Baixa");
            fail("Deveria interromper criacao de tarefa ao passar data sem dia");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT6() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "10/12/2023", "");
            fail("Deveria interromper criacao de tarefa sem prioridade");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }

    @Test
    public void CT7() {
        try {
            Tarefa tarefa = new Tarefa("  ", "Exercício", "10/12/2023", "Alta");
            fail("Deveria interromper criacao de atividade sem nome");
        } catch (IllegalArgumentException message) {
            assertEquals("Titulo Invalido! Atribua um titulo para sua tarefa.", message.getMessage());
        }
    }

    @Test
    public void CT8() {
        Tarefa tarefa = new Tarefa("😊", "Exercício", "10/12/2023", "Alta");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT9() {
        Tarefa tarefa = new Tarefa("Tarefa", "😊",  "10/12/2023", "Alta");
        user.addTarefa(tarefa);
        assertEquals(1, user.getTarefas().size());
    }

    @Test
    public void CT10() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "😊", "Alta");
            fail("Deveria interromper criacao de tarefa ao passar emoji no campo de data");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT11() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "10/12/2023", "😊");
            fail("Deveria interromper criacao de tarefa com prioridade em forma de emoji");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }

    @Test
    public void CT12() {
        try {
            Tarefa tarefa = new Tarefa("Tarefa", "Exercicio" , "10/12/1964", "Media");
            fail("Deveria interromper criacao de tarefa ao passar data menor que data atual no campo de data");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida! Atribua uma validade igual ou maior ao dia atual.", message.getMessage());
        }
    }

}
