package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.model.Medic;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.repository.IMedicRepo;
import com.medicalcare.medicalcarebackend.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//PRIMERO SE HEREDA, luego se implementa
@Service
@RequiredArgsConstructor //genera automáticamente un constructor que acepta las dependencias marcadas como final en la clase.
public class MedicServiceImpl extends CRUDImpl<Medic,Integer> implements IMedicService {
    //@Autowired
    private final IMedicRepo repo; // = new MedicRepo
    //se inyecta repositorY a traves del constructor con la anotación de @RequiredArgsConstructor, no se usa autowired ya que
    // ..uando usas @Autowired, en el constructor, Spring inyectará automáticamente la instancia correcta  en el constructor al crear una instancia de ServiceImpl.

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
