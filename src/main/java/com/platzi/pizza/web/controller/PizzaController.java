package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                    @RequestParam(defaultValue = "price") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String sortDirection)
    {
        return ResponseEntity.ok(this.pizzaService.getAll(pageNumber, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PizzaEntity>getById(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaService.getById(id));
    }

    @GetMapping("/availablePizzas")
    public ResponseEntity<List<PizzaEntity>> getAvailablePizzas(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/name/{pizzaName}")
    public ResponseEntity<List<PizzaEntity>> getByName(@PathVariable String pizzaName){
        return ResponseEntity.ok(this.pizzaService.getName(pizzaName));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getByDescription(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithoutIngredient(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getPizzasWithoutIngredient(ingredient));
    }

    @GetMapping("/cheaper")
    public ResponseEntity<List<PizzaEntity>> getCheapPizzas(){
        return ResponseEntity.ok(this.pizzaService.getByCheap());
    }

    @PostMapping("/save")
    public ResponseEntity<?>savePizza(@RequestBody PizzaEntity pizza){
        try {
            if (pizza.getIdPizza() == null ||!this.pizzaService.exist(pizza.getIdPizza())){
                return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La pizza ya existe!");
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>updatePizza(@RequestBody PizzaEntity pizza){
        try {
            return pizzaService.exist(pizza.getIdPizza())
                    ? ResponseEntity.ok(pizzaService.savePizza(pizza))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pízza no existe!");
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/price")
    public ResponseEntity<?>updatePizza(@RequestBody UpdatePizzaPriceDTO pizza){
        try {
             if (pizzaService.exist(pizza.getPizzaId())) {
                    pizzaService.updatePizzaPrice(pizza);
                    return ResponseEntity.ok().build();
             } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pízza no existe!");
             }
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{idDelete}")
    public ResponseEntity<?> deletePizza(@PathVariable int idDelete){
        try {
            if (this.pizzaService.exist(idDelete)){
                this.pizzaService.delete(idDelete);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e){
                return ResponseEntity.internalServerError().build();
        }
    }
}
