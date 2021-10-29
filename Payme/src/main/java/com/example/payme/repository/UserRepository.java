package com.example.payme.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payme.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
