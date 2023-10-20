package com.platzi.pizza.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
// Clase del identificador
// se utiliza para definir la clave primaria compuesta de la entidad
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {

    // Esta propiedad representa la parte "idOrder" de la clave primaria.
    private Integer idOrder;

    //  Esta propiedad representa la parte "idItem" de la clave primaria.
    private Integer idItem;

    // Compara la clave primaria de los objetos OrderItemId y determina si son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idItem);
    }
}