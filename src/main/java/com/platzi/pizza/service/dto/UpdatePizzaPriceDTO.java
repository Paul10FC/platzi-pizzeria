package com.platzi.pizza.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDTO {
    private int pizzaId;
    private double newPrice;
}
