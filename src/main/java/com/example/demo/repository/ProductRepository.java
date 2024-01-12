package com.example.demo.repository;

import com.example.demo.models.entity.Myshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Myshop,Integer> {
}
