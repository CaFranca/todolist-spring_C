package br.edu.ifsp.spo.todolist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index(String nome){
        return String.format("Hello, %s!", nome);
    }
}