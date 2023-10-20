package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    List<PizzaEntity> findAllByAvailableTrueAndNameStartsWithIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredients);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String ingredients);

    List<PizzaEntity> findTop3ByAvailableTrueOrderByPriceAsc();

    int countAllByAvailableTrue();

    @Query(value =
           """
           UPDATE pizza
           SET price = :#{#newPizzaPrice.newPrice}
           WHERE id_pizza = :#{#newPizzaPrice.pizzaId}
           """, nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDTO newPizzaPrice);
}
