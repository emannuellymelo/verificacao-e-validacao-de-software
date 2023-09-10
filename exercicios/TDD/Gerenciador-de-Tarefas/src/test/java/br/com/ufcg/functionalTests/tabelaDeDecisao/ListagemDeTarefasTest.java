package br.com.ufcg.functionalTests.tabelaDeDecisao;

import br.com.ufcg.gerenciador.Tarefa;
import br.com.ufcg.gerenciador.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListagemDeTarefasTest {

    @Test
    public void CT1() {
        Usuario user = new Usuario("User5");
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Exercício de BD", "10/12/2023", "Media");
        Tarefa tarefa2 = new Tarefa("Tarefa 2", "Exercício de V&V", "12/12/2023", "Media");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 1", user.getTarefas().get(0).getTitulo());
    }

    @Test
    public void CT2() {
        Usuario user = new Usuario("User2");
        Tarefa tarefa1 = new Tarefa("Tarefa 1","Exercício de BD",  "15/12/2023", "Alta");
        Tarefa tarefa2 = new Tarefa("Tarefa 2","Exercício de V&V", "05/12/2023", "Baixa");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 2", user.getTarefas().get(0).getTitulo());
    }

    @Test
    public void CT3() {
        Usuario user = new Usuario("User4");
        Tarefa tarefa1 = new Tarefa("Tarefa 1","Exercício de BD",  "10/12/2023", "Baixa");
        Tarefa tarefa2 = new Tarefa("Tarefa 2","Exercício de V&V",  "10/12/2023", "Media");
        user.addTarefa(tarefa1);
        user.addTarefa(tarefa2);
        assertEquals( "Tarefa 2", user.getTarefas().get(0).getTitulo());
    }
}
