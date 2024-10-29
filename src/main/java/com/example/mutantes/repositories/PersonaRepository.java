package com.example.mutantes.repositories;

import com.example.mutantes.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona,Long>{
}
