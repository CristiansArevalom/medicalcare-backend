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
@Data
//(TIENE POR DEBAJO LAS ANOTACIONES GETTER,SETTER, ToString y EqualsAndHasCode, esta ultima por defecto compara TODOS los atributos a ver si son iguales
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Para que no compare todos los argumentos, solo los que se especifiquen
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ConsultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //the JPA implementation expects the database to automatically assign unique primary key values to newly inserted records
    @EqualsAndHashCode.Include
    private Integer idDetail;


    /*FORMA 1 de relación entre tablas este seria eñ lado muchos a uno
     Muchos detalle de consulta estan asociados a una consulta, aqui se
     aplica el principio de "maestro-detalle" de BD
    */

    @ManyToOne
    @JoinColumn(name="id_consult",nullable = false, foreignKey= @ForeignKey(name = "CONSULTDETAIL_ID_CONSULT_FK"))
    /*el nombre de la llave foranea no es obligatorio que sea igual al de la tabla primaria PERO ES BUENA PRACTICA
    //ES OBLIGATORIO QUE SEA DEL MISMO TIPO*/

    @Column(nullable = false, length = 70)
    private Consult consult;

    @Column(nullable = false, length = 70)
    private String diagnosis;

    @Column(nullable = false, length = 70)
    private String treatment;


}
