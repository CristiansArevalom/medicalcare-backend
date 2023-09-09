package com.medicalcare.medicalcarebackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*RELACION HACIA OTRA TABLA USANDO LLAVE PRIMARIA COMPUESTA , S
Se recomienda que la definición de la lalve primaria este separada en otra tabla
 */

@Data
//(TIENE POR DEBAJO LAS ANOTACIONES GETTER,SETTER, ToString y EqualsAndHasCode, esta ultima por defecto compara TODOS los atributos a ver si son iguales
@NoArgsConstructor
@AllArgsConstructor
@Entity // indica que es una entidad de mapeo jpa
@IdClass(ConsultExamPK.class) //permite que la clase de consultExamPK sea usada dentro de la clase consultExam
public class ConsultExam {


    @Id
    private Consult consult;

    @Id
    private Exam exam;

    /*se hubiera podido evitar esto DE CREAR el consultExamPk y la table con llaves compuestas haciendo una tabla normal con su respectivo ID y relacionandolo
    con las otras tablas
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Consult consult;

    @ManyToOne
    private Exam exam;
    */

}
