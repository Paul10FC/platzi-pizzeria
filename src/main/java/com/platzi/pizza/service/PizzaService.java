package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    //Ponemos un JdbcTemplate Final
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaPagSortRepository = pizzaPagSortRepository;
        this.pizzaRepository = pizzaRepository;
    }

    // Obtenemos la Query Consult
    // Mapeamos el resultado en objetos Java
    public Page<PizzaEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getById(int id){
        return this.pizzaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePizzaPrice(UpdatePizzaPriceDTO updatePizzaPriceDTO){
        this.pizzaRepository.updatePrice(updatePizzaPriceDTO);
    }

    public List<PizzaEntity> getName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameStartsWithIgnoreCase(name);
    }

    public List<PizzaEntity> getByCheap(){
        return this.pizzaRepository.findTop3ByAvailableTrueOrderByPriceAsc();
    }

    public List<PizzaEntity> getByDescription(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getPizzasWithoutIngredient(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public PizzaEntity savePizza(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public Boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
}
