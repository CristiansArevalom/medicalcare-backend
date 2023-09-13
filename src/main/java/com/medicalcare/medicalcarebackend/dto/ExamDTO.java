package com.medicalcare.medicalcarebackend.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExamDTO {


    @EqualsAndHashCode.Include
    private Integer idExam;

    @NotNull //son constraints desde el vVALIDATION QUE VALIDAN EL JSON RECIBIDO Y VEN SI ESTA NULL O VACIO
    @NotEmpty
    private String nameExam;

    @NotNull
    @NotEmpty
    private String descriptionExam;
}

