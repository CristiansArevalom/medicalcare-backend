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
@Table(name = "user_data") // Por si se quiere dar un nombre personalizado a la tabla diferentea l nombre de la clase
public class User { // esta tabla esta asociada a que tipo de usuario entra a la aplicacion

    @Id
    //No se incluye @GeneratedVakue ya que se controlara  los roles de manera manual al insertarlos CUANDO SE IMPLEMETE EL SPRING SECURITY
    private Integer idUser;

    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", //se crea la tabla intermedia con claves primarias de id_user e id_role
        joinColumns = @JoinColumn(name = "id_user" , referencedColumnName = "idUser", foreignKey = @ForeignKey(name = "USERROLE_ID_USER_FK")),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName= "idRole", foreignKey = @ForeignKey(name = "USERROLE_ID_ROLE_FK"))
    )
    private List<Role> roles;

    //FORMA 2 DE RELACION ENTRE TABLAS MANY TO MANY SIN  crear manualmente la TABLA INTERMEDIA sino que la crea springboot
    /*Eager permite traer todos los datos relacionados cuando se consulte uusario, es decir que cuando se traiga usuario, tambien se traeran todos sus roles.
     * no se recomienda si son muchos registros ya que sobrecarga la app
     *
     * En JPA, no puedes personalizar directamente el nombre de la clave foránea en una relación @ManyToMany, por lo que ese ForeignKey No funciona
     * */



}
