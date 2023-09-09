package com.medicalcare.medicalcarebackend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)

@Entity
public class Role {
    @Id
    //No se incluye @GeneratedVakue ya que se controlara  los roles de manera manual al insertarlos CUANDO SE IMPLEMETE EL SPRING SECURITY
    @EqualsAndHashCode.Include
    private Integer idRole;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 1000)
    private String descripcion;

}
