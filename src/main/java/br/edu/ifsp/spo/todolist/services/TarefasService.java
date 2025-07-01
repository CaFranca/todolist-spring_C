package br.edu.ifsp.spo.todolist.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.todolist.models.Tarefa;
import br.edu.ifsp.spo.todolist.repositories.TarefasRepository;

@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    
    public TarefasService(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    public List<Tarefa> listar(String filtro){
        return switch(filtro.toLowerCase()) {
            case "pendentes" -> 
                this.tarefasRepository.listarTodas().stream()
                    .filter(tarefa -> !tarefa.concluida())
                    .toList();
            case "concluidas" -> this.tarefasRepository.listarTodas().stream()
                    .filter(tarefa -> tarefa.concluida())
                    .toList();
            default -> this.tarefasRepository.listarTodas();
        };
    }

    public Iterable<Tarefa> listarTodas() {
        return this.tarefasRepository.listarTodas();
    }

    public void adicionarNovaTarefa(String texto) {
        this.tarefasRepository.adicionar(texto, false);
    }    

    public void marcarComoConcluida(int id) {
        var tarefa = this.tarefasRepository.listarTodas().stream()
            .filter(t -> t.id() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        
        this.tarefasRepository.atualizar(id, tarefa.texto(), true);
    }
    

    public void marcarComoNaoConcluida(int id) {
        var tarefa = this.tarefasRepository.listarTodas().stream()
            .filter(t -> t.id() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        
        this.tarefasRepository.atualizar(id, tarefa.texto(), false);
    }
}
