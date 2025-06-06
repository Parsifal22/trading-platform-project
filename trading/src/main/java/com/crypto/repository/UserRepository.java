package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
