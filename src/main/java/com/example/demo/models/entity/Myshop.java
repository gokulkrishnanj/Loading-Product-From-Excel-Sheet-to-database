package com.example.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "myshop")
public class Myshop {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PART_ID")
    private String partId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Double price;

}
