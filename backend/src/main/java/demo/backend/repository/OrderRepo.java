package demo.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.backend.model.Order;

public interface OrderRepo extends JpaRepository<Order, UUID>{


}
