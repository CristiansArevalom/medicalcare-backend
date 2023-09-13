package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.model.Consult;
import com.medicalcare.medicalcarebackend.model.Exam;
import com.medicalcare.medicalcarebackend.repository.IConsultExamRepo;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.repository.IConsultRepo;
import com.medicalcare.medicalcarebackend.service.IConsultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//PRIMERO SE HEREDA, luego se implementa
@Service
@RequiredArgsConstructor //genera automáticamente un constructor que acepta las dependencias marcadas como final en la clase. esto es inyecciopn de dependencias por constructor
public class ConsultServiceImpl extends CRUDImpl<Consult,Integer> implements IConsultService {

    //@Autowired
    private final IConsultRepo consultRepo; // = new ConsultRepo
    private final IConsultExamRepo ceRepo;
    //se inyecta repositorY a traves del constructor con la anotación de @RequiredArgsConstructor, no se usa autowired ya que
    // ..uando usas @Autowired, en el constructor, Spring inyectará automáticamente la instancia correcta  en el constructor al crear una instancia de ServiceImpl.

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }


    /*este metodo es porque desde el controler y dto recibe un consultorio con sus lista de examenes, entonces
    se quiere guardar ambos registrosen la BD y grarantizar la integridad de la transacción
    */
    @Transactional //debe ir en el metodo donde quiere que sea transaccional, esto e spara que se hagan aglunos insert en varias clases, se recomienda colocarlo a nivel
    //de metodo y no a nivel de clase
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        // Aca se guarda el MAESTRO detalle . metiendo en consult y consult detail, esa llave primaria se queda en consulta gracias a la anotación de cascade que tiene el entity
        consultRepo.save(consult);
        //aca por cada examen se llama el serviico de guardarExamen que por debajo tiene un repository que hace un quiery dde insert a consult_exam
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(),ex.getIdExam()));
        return consult;
    }
}
