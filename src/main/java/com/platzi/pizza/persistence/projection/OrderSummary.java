package com.platzi.pizza.persistence.projection;

import java.time.LocalDate;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    Double getTotal();
    LocalDate getOrderDate();
    String getPizzaNames();
    String getMethod();
}
