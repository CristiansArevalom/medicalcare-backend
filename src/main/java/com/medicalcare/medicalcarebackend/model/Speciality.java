package com.medicalcare.medicalcarebackend.model;

import jakarta.persistence.*;
import lombok.*;

/*@Getter
@Setter
@ToString
@EqualsAndHashCode*/
@Data //(TIENE POR DEBAJO LAS ANOTACIONES GETTER,SETTER, ToString y EqualsAndHasCode, esta ultima por defecto compara TODOS los atributos a ver si son iguales
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Para que no compare todos los argumentos, solo los que se especifiquen
@NoArgsConstructor
@AllArgsConstructor

@Entity // indica que es una entidad de mapeo jpa
//@Table(name = "ss") Por si se quiere dar un nombre personalizado a la tabla

public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSpeciality;

    @Column(length=50,nullable = false)
    private String name;

    @Column(length=50, nullable=false)
    private String description;
}
