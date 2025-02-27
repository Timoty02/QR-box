package com.qr.repository;

import com.qr.dao.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Integer> {
    Optional<Box> findByName(String name);
}
