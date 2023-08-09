package com.teste.projetoAPI.repository;

import com.teste.projetoAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
