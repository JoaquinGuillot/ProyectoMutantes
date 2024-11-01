package com.example.mutantes.controllers;

import com.example.mutantes.entities.Mutante;
import com.example.mutantes.entities.dtos.EstadisticasDTO;
import com.example.mutantes.services.MutanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant/")
public class MutanteController {
    private MutanteService mutanteService;

    public MutanteController(MutanteService mutanteService) {
        this.mutanteService = mutanteService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Mutante entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mutanteService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @PostMapping("/analizar")
    public ResponseEntity<?> analizarADN(@RequestBody Mutante persona) {
        try {
            boolean esMutante = mutanteService.analizarADN(persona);
            return esMutante ?
                    //En caso de que la persona ingresada sea mutante  devuelve codigo 200
                    ResponseEntity.ok("La persona es un mutante") :
                    //En caso de que no sea mutante arroja codigo 403
                    ResponseEntity.status(403).body("La persona ingresada no es un mutante");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Error. Los datos ingresados no son válidos.\"}");
        }
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> obtenerEstadisticas() {
        return ResponseEntity.ok(mutanteService.obtenerEstadisticas());
    }
}