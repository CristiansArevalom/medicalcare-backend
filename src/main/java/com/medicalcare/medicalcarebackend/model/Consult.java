package com.medicalcare.medicalcarebackend.model;


import java.time.LocalDateTime;
import java.util.List;

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
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;

    @ManyToOne
    @JoinColumn(name="id_patient", nullable = false, foreignKey = @ForeignKey(name = "CONSULT_ID_PATIENT_FK"))
    private Patient patient;
    /*incluir la relación de OneToMany en Paciente No es necesaria aunque depende de los datos que se requieran,
        en este caso no tiene logica buscar desde paciente porque se repetirian sus tados
        y solo cambiaria el ID de consulta..
    */

    @ManyToOne
    @JoinColumn(name="id_medic", nullable = false, foreignKey = @ForeignKey(name="CONSULT_ID_MEDIC_FK"))
    private Medic medic;

    @ManyToOne
    @JoinColumn(name="id_specialty", nullable = false, foreignKey = @ForeignKey(name="CONSULT_ID_SPECIALITY_FK"))
    private Specialty specialty;

    @Column(length = 3, nullable = false)
    private String numConsult;

    @Column(nullable = false)
    private LocalDateTime consultDate;

    @OneToMany(mappedBy = "consult", cascade = CascadeType.ALL, orphanRemoval = true) //fetch = FetchType.EAGER)
    private List<ConsultDetail> details;

    /*una consulta tiene muchos detalle de consulta
    es por eso que el tipo den dato es una lista
    el MappedBy es el nombre del atributo de la otra relación en este caso ConsultDetail es consult
    Cascade : como se debe exigir que un detalle tenga si o so cabecera, que lo que le pase al padre
    le pace al hijo y viceversa, se requiere ese CascadeType.ALL
    orphanRemoval:s, garantiza que los objetos secundarios se eliminen automáticamente si ya no están asociados con la entidad principal.
    fetch :  todas las relaciones por defecto son Lazy, asi cuando se traiga por Join el OneTOmANY NO SE TRAEN POBLADOS LOS DATOS
    EL EAGER es solo necesario cuando se requiere info pero en poca cantidad ya que afecta rendimiento

    */


    //@Column(columnDefinition = "decimal(6,2)")
    //private double total;
}
