package com.bruceycode.My_Rest_Api.Repository;
import com.bruceycode.My_Rest_Api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
