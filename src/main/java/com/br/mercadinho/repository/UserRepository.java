package com.br.mercadinho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mercadinho.entity.user.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

}
