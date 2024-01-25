package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyShopDTO {
    private String partId;
//    @Min(value =10,message = "minimum quantity must be 10")
    private Integer quantity;
    private Double price;
}
