package com.medicalcare.medicalcarebackend.model;


//esta clase es para interactuar con angular y poder escoger los menus a mostrar

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Menu {
    @Id
    @EqualsAndHashCode.Include
    private Integer idMenu;

    @Column(nullable = false, length=20)
    private String icon;

    @Column(nullable = false, length=20)
    private String name;

    @Column(nullable = false, length=20)
    private String url;

    //relación MANY TO MANY creandop taba intermedia, esto no se recomienda ya que para escalabilidad de añadir nuevos atributos se debe modificar aqui.
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinTable(name= "menu_role",
        joinColumns=@JoinColumn(name="id_menu",referencedColumnName = "idMenu"),
        inverseJoinColumns = @JoinColumn(name= "id_role", referencedColumnName = "idRole")
    )
    private List<Role> roles;
}
