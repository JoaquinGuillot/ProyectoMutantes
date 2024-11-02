package com.example.mutantes.services;

import com.example.mutantes.entities.Mutante;
import com.example.mutantes.entities.dtos.EstadisticasDTO;
import com.example.mutantes.repositories.MutanteRepository;
import com.example.mutantes.search.Arbol;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MutanteService implements BaseService<Mutante> {

    @Autowired
    private MutanteRepository mutanteRepository;

    //Genero mi árbol y la longitud de la secuencia a buscar
    private Arbol arbol;
    private static final int LONGITUD_SECUENCIA = 4;

    public MutanteService() {
        this.arbol = new Arbol();
        inicializarArbol();
    }

    private void inicializarArbol() {
        // Secuencias de ADN que se consideran mutantes
        arbol.insertar("AAAA");
        arbol.insertar("TTTT");
        arbol.insertar("CCCC");
        arbol.insertar("GGGG");
    }
    @Transactional
    public boolean analizarADN(Mutante persona) {
        if (persona.getAdn() == null || persona.getAdn().length == 0) {
            throw new IllegalArgumentException("Secuencia de ADN inválida");
        }

        boolean resultado = isMutant(persona.getAdn());
        persona.setEsMutante(resultado);
        mutanteRepository.save(persona);
        return resultado;
    }
    @Override
    @Transactional
    public List<Mutante> findAll() throws Exception{
        try{
            List<Mutante> entities = mutanteRepository.findAll();
            return entities;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Mutante findById(Long id) throws Exception{
        try{
            Optional<Mutante> entityOptional = mutanteRepository.findById(id);
            return entityOptional.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

/*En una primera instancia generé el metodo save de forma independiente
 para guardar personas sin verificar si era mutante mutante,
pero a fines prácticos decidí implementar unicamente su lógica en el método "isMutant"
 */
    @Transactional
    public Mutante save(Mutante entity) throws Exception {
        try{
            entity = mutanteRepository.save(entity);
            return entity;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Mutante update(Long id, Mutante entity) throws Exception {
        try{
            Optional<Mutante> entityOptional = mutanteRepository.findById(id);
            Mutante mutante = entityOptional.get();
            mutante = mutanteRepository.save(mutante);
            return mutante;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }    }
    @Override
    @Transactional
    public boolean delete(Long id) throws Exception{
        try{
            if(mutanteRepository.existsById(id)){
                mutanteRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    private boolean isMutant(String[] adn) {
    try{


        int n = adn.length;
        int secuenciasEncontradas = 0;

        // Matriz para rastrear posiciones visitadas
        boolean[][] visitado = new boolean[n][n];

        // Verificar horizontal y vertical en un solo bucle
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Si la posición ya ha sido visitada, continuar
                if (visitado[i][j]) continue;

                // Verificar horizontal
                if (j <= n - LONGITUD_SECUENCIA) {
                    String secuenciaHorizontal = adn[i].substring(j, j + LONGITUD_SECUENCIA);
                    if (arbol.buscar(secuenciaHorizontal)) {
                        secuenciasEncontradas++;
                        marcarVisitados(visitado, i, j, LONGITUD_SECUENCIA, true);
                    }
                }

                // Verificar vertical
                if (i <= n - LONGITUD_SECUENCIA) {
                    StringBuilder secuenciaVertical = new StringBuilder();
                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
                        secuenciaVertical.append(adn[i + k].charAt(j));
                    }
                    if (arbol.buscar(secuenciaVertical.toString())) {
                        secuenciasEncontradas++;
                        marcarVisitados(visitado, i, j, LONGITUD_SECUENCIA, false);
                    }
                }

                // Verificar diagonal principal
                if (i <= n - LONGITUD_SECUENCIA && j <= n - LONGITUD_SECUENCIA) {
                    StringBuilder secuenciaDiagonal = new StringBuilder();
                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
                        secuenciaDiagonal.append(adn[i + k].charAt(j + k));
                    }
                    if (arbol.buscar(secuenciaDiagonal.toString())) {
                        secuenciasEncontradas++;
                        marcarVisitadosDiagonal(visitado, i, j, LONGITUD_SECUENCIA, true);
                    }
                }

                // Verificar diagonal inversa
                if (i <= n - LONGITUD_SECUENCIA && j >= LONGITUD_SECUENCIA - 1) {
                    StringBuilder secuenciaDiagonalInversa = new StringBuilder();
                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
                        secuenciaDiagonalInversa.append(adn[i + k].charAt(j - k));
                    }
                    if (arbol.buscar(secuenciaDiagonalInversa.toString())) {
                        secuenciasEncontradas++;
                        marcarVisitadosDiagonal(visitado, i, j, LONGITUD_SECUENCIA, false);
                    }
                }

                // Verificar si se encontraron más de 1 secuencia
                if (secuenciasEncontradas > 1) return true;
            }
        }

        return false;
    }catch (Exception e) {
        // Manejo de excepciones
        // Puedes registrar el error o lanzar una excepción personalizada
        throw new RuntimeException("Error al analizar el ADN: " + e.getMessage());
    }
    }

    // Método para marcar las posiciones visitadas en horizontal y vertical
    private void marcarVisitados(boolean[][] visitado, int i, int j, int longitud, boolean esHorizontal) {
        for (int k = 0; k < longitud; k++) {
            if (esHorizontal) {
                visitado[i][j + k] = true; // Marca horizontalmente
            } else {
                visitado[i + k][j] = true; // Marca verticalmente
            }
        }
    }

    // Método para marcar las posiciones visitadas en diagonales
    private void marcarVisitadosDiagonal(boolean[][] visitado, int i, int j, int longitud, boolean esDiagonal) {
        for (int k = 0; k < longitud; k++) {
            if (esDiagonal) {
                visitado[i + k][j + k] = true; // Marca diagonalmente
            } else {
                visitado[i + k][j - k] = true; // Marca diagonal inversamente
            }
        }
    }

//    @Transactional
//    private boolean isMutant(String[] adn) {
//
//        try {
//
//            int n = adn.length;
//            int secuenciasEncontradas = 0;
//
//            // Verificar horizontales
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j <= n - LONGITUD_SECUENCIA; j++) {
//                    String secuencia = adn[i].substring(j, j + LONGITUD_SECUENCIA);
//                    if (arbol.buscar(secuencia)) {
//                        secuenciasEncontradas++;
//                        if (secuenciasEncontradas > 1) return true;
//                    }
//                }
//            }
//
//            // Verificar verticales
//            for (int j = 0; j < n; j++) {
//                for (int i = 0; i <= n - LONGITUD_SECUENCIA; i++) {
//                    StringBuilder secuencia = new StringBuilder();
//                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
//                        secuencia.append(adn[i + k].charAt(j));
//                    }
//                    if (arbol.buscar(secuencia.toString())) {
//                        secuenciasEncontradas++;
//                        if (secuenciasEncontradas > 1) return true;
//                    }
//                }
//            }
//
//            // Verificar diagonales principales
//            for (int i = 0; i <= n - LONGITUD_SECUENCIA; i++) {
//                for (int j = 0; j <= n - LONGITUD_SECUENCIA; j++) {
//                    StringBuilder secuencia = new StringBuilder();
//                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
//                        secuencia.append(adn[i + k].charAt(j + k));
//                    }
//                    if (arbol.buscar(secuencia.toString())) {
//                        secuenciasEncontradas++;
//                        if (secuenciasEncontradas > 1) return true;
//                    }
//                }
//            }
//
//            // Verificar diagonales inversas
//            for (int i = 0; i <= n - LONGITUD_SECUENCIA; i++) {
//                for (int j = LONGITUD_SECUENCIA - 1; j < n; j++) {
//                    StringBuilder secuencia = new StringBuilder();
//                    for (int k = 0; k < LONGITUD_SECUENCIA; k++) {
//                        secuencia.append(adn[i + k].charAt(j - k));
//                    }
//                    if (arbol.buscar(secuencia.toString())) {
//                        secuenciasEncontradas++;
//                        if (secuenciasEncontradas > 1) return true;
//                    }
//                }
//            }
//
//            return false;
//        }catch(Exception e){
//            throw new RuntimeException("Error al analizar el ADN: " + e.getMessage());
//        }
//        }

        // Método para obtener estadísticas
        public EstadisticasDTO obtenerEstadisticas () {
            long totalMutantes = mutanteRepository.countByEsMutante(true);
            long totalHumanos = mutanteRepository.countByEsMutante(false);
            double ratio = totalHumanos > 0 ? (double) totalMutantes / totalHumanos : 0.0;

            return new EstadisticasDTO(totalMutantes, totalHumanos, ratio);
        }

}





