package demo.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.backend.model.Product;
import demo.backend.model.User;

public interface ProductRepo extends JpaRepository<Product, UUID> {

     Optional<User> findByUsername (String username);



}
