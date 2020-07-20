package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entitys.Product;
public interface ProductsDAO extends JpaRepository<Product, Long>{

}
