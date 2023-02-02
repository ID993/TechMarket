package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
