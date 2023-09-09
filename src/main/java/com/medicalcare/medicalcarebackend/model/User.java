package com.medicalcare.medicalcarebackend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*@Getter
@Setter
@ToString
@EqualsAndHashCode*/
@Data //(TIENE POR DEBAJO LAS ANOTACIONES GETTER,SETTER, ToString y EqualsAndHasCode, esta ultima por defecto compara TODOS los atributos a ver si son iguales
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Para que no compare todos los argumentos, solo los que se especifiquen
@NoArgsConstructor
@AllArgsConstructor

@Entity // indica que es una entidad de mapeo jpa
@Table(name = "user_data") // Por si se quiere dar un nombre personalizado a la tabla
public class User { // esta tabla esta asociada a que tipo de usuario entra a la aplicacion

    @Id
    //No se incluye @GeneratedVakue ya que se controlara  los roles de manera manual al insertarlos CUANDO SE IMPLEMETE EL SPRING SECURITY
    private Integer idUer;

    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    //FORMA 2 DE RELACION ENTRE TABLAS MANY TO MANY SIN TABLA INTERMEDIA


    private List<Role> roles;




}
