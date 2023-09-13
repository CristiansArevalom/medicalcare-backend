package com.medicalcare.medicalcarebackend.dto;


//se crea este DTO para hyacer lo de la relación maestro detalle
//asi s epuede insertar una consulta con varias lista de examenes de una vez, asegurando la integridad de la transaccion

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

/*ESta clase es para poder insertar el maestro detalle que tenga la info de la consulta y un arreglo de los examenes, para que luegp
se pueda enviar a la clase consult y la clase listExam
* */
public class ConsultListExamDTO {

    @NotNull
    private ConsultDTO consult;
    @NotNull
    private List<ExamDTO> lstEXam;



}
