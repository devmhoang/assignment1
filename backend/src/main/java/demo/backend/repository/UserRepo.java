package demo.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.backend.model.User;

public interface UserRepo extends JpaRepository<User, UUID> {

     Optional<User> findByUsername(String username);

}
