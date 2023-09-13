package com.medicalcare.medicalcarebackend.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicalcare.medicalcarebackend.model.ConsultDetail;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDTO {

    private Integer idConsult;
    @NotNull
    private PatientDTO patient;

    @NotNull
    private MedicDTO medic;

    @NotNull
    private SpecialtyDTO specialityDTO;

    @NotNull
    private String numConsult;

    //esta notación indica que es la referencia principal,
    @JsonManagedReference
    @NotNull
    private List<ConsultDetail> details;

}
