package com.example.mutantes.services;

import java.util.List;
//Servicio base genérico para mutantes

/*En esta aplicación creí pertinente no solo relizar el servicio para detectar a un mutante unicamente por el arreglo
proporcionado, sino que tambien me di a la tarea de
 */
public interface BaseService<E> {
    public List<E> findAll() throws Exception;
    public E findById(Long id) throws Exception;
    public E update(Long id, E entity) throws Exception;
    public boolean delete(Long id) throws Exception;
}
