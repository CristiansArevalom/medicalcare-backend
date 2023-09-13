package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.model.Patient;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.repository.IPatientRepo;
import com.medicalcare.medicalcarebackend.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//PRIMERO SE HEREDA, luego se implementa
@Service
@RequiredArgsConstructor //genera automáticamente un constructor que acepta las dependencias marcadas como final en la clase.
public class PatientServiceImpl extends CRUDImpl<Patient,Integer> implements IPatientService {
    //@Autowired
    //se inyecta repositorY a traves del constructor con la anotación de @RequiredArgsConstructor, no se usa autowired ya que
    // ..uando usas @Autowired, en el constructor, Spring inyectará automáticamente la instancia correcta de IPatientRepo en el constructor al crear una instancia de PatientServiceImpl.
    private final IPatientRepo repo; // = new PatientRepo
    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }
}
