package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.model.Specialty;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.repository.ISpecialtyRepo;
import com.medicalcare.medicalcarebackend.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//PRIMERO SE HEREDA, luego se implementa
@Service
@RequiredArgsConstructor //genera automáticamente un constructor que acepta las dependencias marcadas como final en la clase.
public class SpecialtyServiceImpl extends CRUDImpl<Specialty,Integer> implements ISpecialtyService {
    //@Autowired
    private final ISpecialtyRepo repo; // = new SpecialityRepo
    //se inyecta repositorY a traves del constructor con la anotación de @RequiredArgsConstructor, no se usa autowired ya que
    // ..uando usas @Autowired, en el constructor, Spring inyectará automáticamente la instancia correcta  en el constructor al crear una instancia de ServiceImpl.

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }
}
