package com.example.mutantes.entities;

import com.example.mutantes.search.Arbol;
import com.example.mutantes.services.ArbolService;
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
public class Persona{

        @Id
        @GeneratedValue
        private Long id;
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "apellido")
        private String apellido;

        private char[][] matriz;

        private boolean esMutante;

        public Persona(char[][] matriz) {
            this.matriz = matriz;
            this.esMutante = false; // Inicialmente, se asume que no contiene patrones
        }

    public boolean getEsMutante() {
            return esMutante;
        }

    // Método para verificar si la matriz contiene algún patrón
        public void checkForPatterns(ArbolService arbolService) {
            // Aquí se puede usar la lógica que ya tienes en PatternSearchService
            // para buscar patrones en la matriz y actualizar containsPattern.

            // Ejemplo de cómo podrías hacerlo (asumiendo que tienes una lista de patrones):
            String[] patterns = {"aaaa", "PATTERN2", "PATTERN3"}; // Reemplaza con tus patrones
            for (String pattern : patterns) {
                if (arbolService.searchWord(pattern)) {
                    this.esMutante = true;
                    break; // Si se encuentra un patrón, no es necesario seguir buscando
                }
            }
        }

}
