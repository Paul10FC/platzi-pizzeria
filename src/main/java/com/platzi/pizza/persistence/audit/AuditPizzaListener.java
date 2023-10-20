package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity pizza;

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.pizza);
        System.out.println("NEW VALUE: " + pizzaEntity);
    }

    // El pre imprime lo que lleva antes de hacer la operacion
    @PreRemove
    public void onPreDelete(PizzaEntity pizza){
        System.out.println(pizza);
    }
}
