package br.edu.ifsp.spo.todolist.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifsp.spo.todolist.models.Tarefa;

@Repository
public class TarefasRepository {

    private List<Tarefa> tarefas;
    private int lastId = 0;

    public TarefasRepository() {

        this.tarefas = new ArrayList<>();
        this.tarefas.add(new Tarefa(1, "Comprar pão!!!!!", false));
        this.tarefas.add(new Tarefa(2, "Comprar batata!!!!!", true));
        this.tarefas.add(new Tarefa(3, "Estudar para a prova de SPOLBP2!!!!!!!!", false));
        this.tarefas.add(new Tarefa(4, "Estudar para a prova de SPOLBP1!!!!!!!!", false));

        this.lastId = this.tarefas.size();
    }

    public List<Tarefa> listarTodas() {
        return this.tarefas;
    }

    public Tarefa adicionar(String texto, boolean concluida) {

        this.lastId++;

        Tarefa novaTarefa = new Tarefa(this.lastId, texto, concluida);
        this.tarefas.add(novaTarefa);

        return novaTarefa;
    }

    public void atualizar(int id, boolean concluida) {
        this.tarefas = this.tarefas
                .stream()
                .map(tarefa -> tarefa.id() == id ? new Tarefa(tarefa.id(), tarefa.texto(), concluida) : tarefa)
                .toList();
    }

    public void atualizar(int id, String texto, boolean concluida) {
        this.tarefas = this.tarefas
                .stream()
                .map(tarefa -> tarefa.id() == id ? new Tarefa(tarefa.id(), texto, concluida) : tarefa)
                .toList();
    }
}
