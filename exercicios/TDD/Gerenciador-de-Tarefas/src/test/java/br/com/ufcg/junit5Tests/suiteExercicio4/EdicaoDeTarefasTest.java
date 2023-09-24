package br.com.ufcg.junit5Tests.suiteExercicio4;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EdicaoDeTarefasTest {

    private Usuario user;
    private Tarefa tarefa;

    private String tarefaId;
    @BeforeEach
    public void setUp() {
        user = new Usuario("user");
        tarefa = new Tarefa("Tarefa", "Exercicio", "10/12/2023", "Alta");
        tarefaId = tarefa.getCodigo();
        user.addTarefa(tarefa);
    }
    @Test
    public void CT1() {
        try {
            user.editTarefa("", "Exercício", "10/12/2023", "Alta", tarefaId);
            fail("Deveria interromper edicao de atividade sem titulo");
        } catch (IllegalArgumentException message) {
            assertEquals("Titulo Invalido! Atribua um titulo para sua tarefa.", message.getMessage());
        }
    }

    @Test
    public void CT2() {
        user.editTarefa("Tarefa", "", "10/12/2023", "Media", tarefaId);
        assertEquals("", tarefa.getDescricao());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/12/2023", "10//2023", "10/12/"})
    public void CT3_CT4_CT5(String date) {
        try {
            user.editTarefa("Tarefa", "Exercicio" , date, "Baixa", tarefaId);
            fail("Deveria interromper edicao de tarefa ao passar data sem dia");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT6() {
        try {
            user.editTarefa("Tarefa", "Exercicio" , "10/12/2023", "", tarefaId);
            fail("Deveria interromper criacao de tarefa sem prioridade");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }

    @Test
    public void CT7() {
        try {
            user.editTarefa("  ", "Exercício", "10/12/2023", "Alta", tarefaId);
            fail("Deveria interromper criacao de atividade sem nome");
        } catch (IllegalArgumentException message) {
            assertEquals("Titulo Invalido! Atribua um titulo para sua tarefa.", message.getMessage());
        }
    }

    @Test
    public void CT8() {
        user.editTarefa("😊", "Exercício", "10/12/2023", "Alta", tarefaId);
        assertEquals("😊", tarefa.getTitulo());
    }

    @Test
    public void CT9() {
        user.editTarefa("Tarefa", "😊",  "10/12/2023", "Alta", tarefaId);
        assertEquals("😊", tarefa.getDescricao());
    }

    @Test
    public void CT10() {
        try {
            user.editTarefa("Tarefa", "Exercicio" , "😊", "Alta", tarefaId);
            fail("Deveria interromper criacao de tarefa ao passar emoji no campo de data");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida!", message.getMessage());
        }
    }

    @Test
    public void CT11() {
        try {
            user.editTarefa("Tarefa", "Exercicio" , "10/12/2023", "😊", tarefaId);
            fail("Deveria interromper criacao de tarefa com prioridade em forma de emoji");
        } catch (IllegalArgumentException message) {
            assertEquals("Prioridade Invalida! Atribua uma prioridade igual a: alta, media ou baixa.", message.getMessage());
        }
    }

    @Test
    public void CT12() {
        try {
            user.editTarefa("Tarefa", "Exercicio" , "10/12/1964", "Media", tarefaId);
            fail("Deveria interromper criacao de tarefa ao passar data menor que data atual no campo de data");
        } catch (IllegalArgumentException message) {
            assertEquals("Data Invalida! Atribua uma validade igual ou maior ao dia atual.", message.getMessage());
        }
    }
}
