package br.com.tech4me.tech4pizzaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.tech4pizzaria.model.Pizza;
import br.com.tech4me.tech4pizzaria.repository.PizzaRepository;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repositorio;
    
    @GetMapping
    private ResponseEntity<List<Pizza>> obterPizzas() {
        return new ResponseEntity<>(repositorio.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Pizza> obterPizzaPorId(@PathVariable String id) {
        return new ResponseEntity<>(repositorio.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Pizza> cadastrarPizza(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(repositorio.save(pizza), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirPizzaPorId(@PathVariable String id) {
        repositorio.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Pizza> atualizarPizza(@PathVariable String id, @RequestBody Pizza pizza) {
        Pizza pizzaAtualizar = repositorio.findById(id).get();

        if (pizzaAtualizar != null) {
            pizza.setId(id);
            return new ResponseEntity<>(repositorio.save(pizza), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
