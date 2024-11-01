package com.example.mutantes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited
public class Mutante {
/*
   A fines prácticos para mantener una mejor auditoria decidi agregar campos adicionales
   a mi clase mutante
 */
        @Id
        @GeneratedValue
        private Long id;
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "apellido")
        private String apellido;
        @Column(name = "esMutante")
        private boolean esMutante;
        private String[] adn;
}
