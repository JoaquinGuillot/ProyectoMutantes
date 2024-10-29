package com.example.mutantes.services;

import com.example.mutantes.entities.Persona;
import com.example.mutantes.repositories.PersonaRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements BaseService<Persona> {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional
    public List<Persona> findAll() throws Exception{
        try{
            List<Persona> entities = personaRepository.findAll();
            return entities;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Persona findById(Long id) throws Exception{
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            return entityOptional.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try{
            entity = personaRepository.save(entity);
            return entity;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            Persona persona = entityOptional.get();
            persona = personaRepository.save(persona);
            return persona;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }    }
    @Override
    @Transactional
    public boolean delete(Long id) throws Exception{
        try{
            if(personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
