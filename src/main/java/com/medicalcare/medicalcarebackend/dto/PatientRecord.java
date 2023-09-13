package com.medicalcare.medicalcarebackend.dto;


//Permite establecer una clase den atributos inmutables para definir un objeto
public record PatientRecord(
        int idPatient,
        String primaryName,
        String surname,
        String dni,
        String address,
        String phone,
        String email

) {
}
