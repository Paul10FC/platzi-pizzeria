package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
     List<OrderEntity> findAllByDateAfter(LocalDateTime date);
     List<OrderEntity> findAllByMethodIn(List<String> methods);

     @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
     List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);


     @Query(value = 
     "SELECT po.id_order AS idOrder, " +
             " po.total AS Total, " +
             " cu.name AS customerName, " +
             " po.date AS orderDate,  " +
             " po.method AS method, " +
             "    GROUP_CONCAT(\" \", pi.name) AS pizzaNames " +
             "FROM pizza_order po " +
             "    INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
             "    INNER JOIN order_item oi ON po.id_order = oi.id_order " +
             "    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
             "WHERE po.id_order = :idOrder " +
             "GROUP BY(po.id_order) " +
             ";",
             nativeQuery = true
     )
     OrderSummary findSummary(@Param("idOrder") int orderId);


     /**
      * procedure value = nombre del procedure
      * procedure outPutParameterName = nombre del parametro de salida
      * @param idCustomer = paremetro de entrada del customer
      * @param method = parametro de entrada del metodo de pago
      * @return
      */
     @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
     boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}
