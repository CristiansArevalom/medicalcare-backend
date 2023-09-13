package com.medicalcare.medicalcarebackend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medicalcare.medicalcarebackend.model.Consult;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDetailDTO {
    @EqualsAndHashCode.Include
    private Integer idDetail;

    //este toma la referencia anterior del managedReference,
    //esta anotación oermite indicar a que consulta corresponde el detalle
    @JsonBackReference
    private Consult consult;

    @NotNull
    private String diagnosis;

    @NotNull
    private String treatment;
}
