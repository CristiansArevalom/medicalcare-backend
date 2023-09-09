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
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMedic;

    @Column(nullable = false, length = 70)
    private String firstName;

    @Column(nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, length = 12)
    private String cmp;

    //@Column(nullable = false, length = 70)
    private String photoUrl;
}


