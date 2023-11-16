package com.car.carvinu.repository;

import com.car.carvinu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserManagmentRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where mobileNumber =:mobileNumber")
    Optional<User> findUserByMobileNumber(String mobileNumber);
}
