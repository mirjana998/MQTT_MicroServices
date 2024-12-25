package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlocaRepository extends JpaRepository<Ploca, Integer>, JpaSpecificationExecutor<Ploca> {

}