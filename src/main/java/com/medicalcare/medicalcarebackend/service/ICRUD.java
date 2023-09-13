package com.medicalcare.medicalcarebackend.service;

import java.util.List;

/*se usan genericos donde T es cualquier tipo e ID la llave primaria
Se ve que se repite codigo  si llamamos siempre los metodos de JPA en cada service, incumpliendo el principio DRY,
porlo que se puede crear una interfaz que use genericos  para centralizar esas operaciones comunes

//SI TODO ESTA EN CRUD, PARA QUE SE ENCESITAN LAS INTERFACES VACIAS, POR LO QUE SE PODRIAN TENER CONSULTAS  AJENAS A CAD ACONTEXTO DE LA ENTIDAD.

*/

public interface ICRUD <T, ID>{

    T save(T t);
    T update (T t , ID id);
    List<T> findAll();
    T findById(ID id);
    void delete (ID id);
}
