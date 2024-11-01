package com.example.mutantes.repositories;

import com.example.mutantes.entities.Mutante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutanteRepository extends JpaRepository <Mutante,Long>{
    /*En esta parte de la estructura del repositorio generamos una consulta
    que nos permitirá contar el número de mutantes
     */
    @Query("SELECT COUNT(m) FROM Mutante m WHERE m.esMutante = ?1")
    long countByEsMutante(boolean esMutante);
}
