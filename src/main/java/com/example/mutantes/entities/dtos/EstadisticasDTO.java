package com.example.mutantes.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstadisticasDTO {
    /*En esta entidad DTO llevamos las estadisticas de nuestro conteo
    tenemos en cuenta la cantidad de humanos, cantidad de mutantes y la razón entre ambos*/
    private long cantidadMutantes;
    private long cantidadHumanos;
    private double ratio;
}