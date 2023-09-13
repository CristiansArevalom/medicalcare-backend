package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.exception.ModelNotFoundException;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T, ID> {

    /*SE CREA UN REPODINAMICO QUE DEPENDEINDO DE QUIEN LO INVOQUE SE COMPORTE DE DIFERENTES FORMAS,
    ASI CREANDO UN METODO ABTACTO CAMBIARIA DEPENDIENDO DE  QUIEN LO IMPLEMENTE
     */
    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save (T t){
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
       return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND: "+ id));
            //ElseThrow recibe un supplier que es una interfaz funcional , Recibe un generico pero ningun parametro y devuelve cualquier tipo de elememtp
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }


}
