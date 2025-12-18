package demo.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.backend.model.Product;

public interface ProductRepo extends JpaRepository<Product, UUID> {




}
