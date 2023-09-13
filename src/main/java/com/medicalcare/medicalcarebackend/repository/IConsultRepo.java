package com.medicalcare.medicalcarebackend.repository;

import com.medicalcare.medicalcarebackend.model.Consult;


/***
 * EL UNICO OBJETIVO DE ESTA FUE APLICAR LO DE HERENCIA PARA QUE EN CASO DE CAMBIAR DE JPAREPOSITORY
 *
 * ADEMAS CUANDO NECESITEMOS UNA INYECCIÓN DE DEPENDENCIAS SERA MAS FACIL
 */
public interface IConsultRepo extends IGenericRepo<Consult, Integer> {

}
