package br.com.ufcg.junit5Tests.suiteExercicio4;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RemocaoDeTarefasTest {

    private Usuario user;
    private Tarefa tarefa;
    @BeforeEach
    public void setUp() {
        user = new Usuario("user");
        tarefa = new Tarefa("Tarefa", "Exercicio", "10/12/2023", "Alta", "1");
        user.addTarefa(tarefa);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "1234567"})
    public void CT1_CT2_CT3(String date) {
        try {
            user.removeTarefa("");
            fail("Deveria impedir a remocao de atividade ao passar codigo inválido");
        } catch(IllegalArgumentException message) {
            assertEquals("Erro! Use um código válido para excluir uma atividade.", message.getMessage());
        }
    }

    @Test
    public void CT4() {
        user.removeTarefa("1");
        assertEquals(0, user.getTarefas().size());
    }
}
