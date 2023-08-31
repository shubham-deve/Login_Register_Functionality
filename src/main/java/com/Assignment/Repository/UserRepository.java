package com.Assignment.Repository;

import com.Assignment.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@EnableJpaRepositories
@Component
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.email=:email ")
    public User getByUsername(@Param("email")String username);

    public boolean existsByEmail(String email);

    public User findByEmail(String username);
}

