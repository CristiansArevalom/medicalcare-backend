package com.medicalcare.medicalcarebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idExam; //se podria suar el UUID pero es mas pesado que el integer..

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;
}


