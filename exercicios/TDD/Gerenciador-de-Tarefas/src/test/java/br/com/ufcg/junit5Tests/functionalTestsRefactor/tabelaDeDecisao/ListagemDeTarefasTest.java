package br.com.ufcg.junit5Tests.functionalTestsRefactor.tabelaDeDecisao;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListagemDeTarefasTest {
    private Usuario user;
    @BeforeEach
    public void setUp() {
        user = new Usuario("user");
    }

    @Test
    public void CT1() {
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Exercício de BD", "10/12/2023", "Media");
        Tarefa tarefa2 = new Tarefa("Tarefa 2", "Exercício de V&V", "12/12/2023", "Media");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 1", user.getTarefas().get(0).getTitulo());
    }

    @Test
    public void CT2() {
        Tarefa tarefa1 = new Tarefa("Tarefa 1","Exercício de BD",  "15/12/2023", "Alta");
        Tarefa tarefa2 = new Tarefa("Tarefa 2","Exercício de V&V", "05/12/2023", "Baixa");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 2", user.getTarefas().get(0).getTitulo());
    }

    @Test
    public void CT3() {
        Tarefa tarefa1 = new Tarefa("Tarefa 1","Exercício de BD",  "10/12/2023", "Baixa");
        Tarefa tarefa2 = new Tarefa("Tarefa 2","Exercício de V&V",  "10/12/2023", "Media");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 2", user.getTarefas().get(0).getTitulo());
    }
}
