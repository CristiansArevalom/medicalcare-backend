package com.medicalcare.medicalcarebackend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable //sirve para indicar que es una clase embebida y funcione cuando se use el IdClas de la clase consultExam
@EqualsAndHashCode


public class ConsultExamPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_consult", nullable = false, foreignKey = @ForeignKey(name = "CONSULTEXAM_ID_CONSULT_FK"))
    private Consult consult;

    @ManyToOne
    @JoinColumn(name = "id_exam", nullable = false, foreignKey = @ForeignKey(name = "CONSULTEXAM_ID_EXAM_FK"))
    private Exam exam;
    /*ConsultExam (C1, EX2) // como el equals hashcode comparara el objeto garantizara la integridad de las llaves compuestas,
    aqui no se especifico el explicty ya que por debajo la clase consult y exam tmabien tienen su respectivo hashcode
    */
    //ConsultExam (C2, EX3)


}

