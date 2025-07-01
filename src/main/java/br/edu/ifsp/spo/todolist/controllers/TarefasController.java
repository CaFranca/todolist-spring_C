package br.edu.ifsp.spo.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.spo.todolist.services.TarefasService;
import br.edu.ifsp.spo.todolist.viewmodels.forms.TarefaForm;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService service;

    public TarefasController(TarefasService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false, defaultValue = "") String filtro) {

        var tarefas = this.service.listar(filtro);

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("tarefaForm", new TarefaForm());

        return "tarefas/index.html";
    }

    @PostMapping("")
    public String create(Model model, TarefaForm tarefaForm) {
        // public String create(Model model, @ModelAttribute("tarefaForm") TarefaForm
        // tarefaForm){

        if (tarefaForm.getTexto() != null && !tarefaForm.getTexto().isBlank())
            this.service.adicionarNovaTarefa(tarefaForm.getTexto());

        return "redirect:/tarefas";
    }

    @GetMapping("/{id}/marcar-como-concluida")
    public String marcarComoConcluida(@PathVariable int id) {
        this.service.marcarComoConcluida(id);
        return "redirect:/tarefas";
    }

    @GetMapping("/{id}/marcar-como-nao-concluida")
    public String marcarComoNaoConcluida(@PathVariable int id) {
        this.service.marcarComoNaoConcluida(id);
        return "redirect:/tarefas";
    }
}
