package com.medicalcare.medicalcarebackend.repository;

import com.medicalcare.medicalcarebackend.model.ConsultExam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam,Integer>{

    //esta anotación genera un bloque transaccional para garantizar que ingrese la cantidad total de datos, péro no s epuede dejar aquoi ya que se requiere que abarque mas. asi
    //que por eso se coloca en el servicio
    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES (:idConsult, :idExam)"
        , nativeQuery = true)
    Integer saveExam (@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
    /*se crea este insert para que cuando se cree una consulta en un examen, por debajo se pueda llamar
    este metodo e ir poblando la tabla intermedia

    si se quiere hacer un query nativo, pero este queiry es de inserción, modificación o eliminación,
     es necesario agregar la anotación @Modyfiing ya que si no, estaria esperando a que retornen registros
esto devuelve la cantidad de filas afectadas
    */
}
