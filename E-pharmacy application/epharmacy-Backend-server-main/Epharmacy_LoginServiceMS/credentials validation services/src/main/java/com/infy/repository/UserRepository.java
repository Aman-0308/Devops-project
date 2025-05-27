package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByUsername(String username);
}

