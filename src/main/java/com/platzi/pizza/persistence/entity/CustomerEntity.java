package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name = "id_customer", length = 15, nullable = false)
    private String idCustomer;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 100)
    private String address;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
