package com.example.mutantes.entities.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter
@Setter
@AllArgsConstructor
@Audited
@Table
public class EstadisticasDTO {
    /*En esta entidad DTO llevamos las estadisticas de nuestro conteo
    tenemos en cuenta la cantidad de humanos, cantidad de mutantes y la razón entre ambos*/
    @Column(name = "Mutantes")
    private long cantidadMutantes;
    @Column(name = "Humanos")
    private long cantidadHumanos;
    @Column(name = "Ratio")
    private double ratio;
}