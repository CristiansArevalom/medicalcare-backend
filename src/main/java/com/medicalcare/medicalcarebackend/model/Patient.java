package com.medicalcare.medicalcarebackend.model;


import jakarta.persistence.*;
import lombok.*; //auida a evitar el codigo verboso
import lombok.EqualsAndHashCode;
/*@Getter
@Setter
@ToString
@EqualsAndHashCode*/
@Data //(TIENE POR DEBAJO LAS ANOTACIONES GETTER,SETTER, ToString y EqualsAndHasCode, esta ultima por defecto compara TODOS los atributos a ver si son iguales
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Para que no compare todos los argumentos, solo los que se especifiquen
@NoArgsConstructor
@AllArgsConstructor

@Entity // indica que es una entidad de mapeo jpa
//@Table(name = "tbl_patient") Por si se quiere dar un nombre personalizado a la tabla
public class Patient {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include //es para que cuando se compare el objeto con Equals, tenga en cuenta solo la id
    private Integer idPatient;

    @Column(length = 70, nullable=false)
    private String firstName;

    @Column(length = 70, nullable=false)
    private String lastName;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 55, nullable = false)
    private String email;



}
