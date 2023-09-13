package com.medicalcare.medicalcarebackend.controller;


import com.medicalcare.medicalcarebackend.dto.ConsultDTO;
import com.medicalcare.medicalcarebackend.dto.ConsultListExamDTO;
import com.medicalcare.medicalcarebackend.model.Consult;
import com.medicalcare.medicalcarebackend.model.Exam;
import com.medicalcare.medicalcarebackend.service.IConsultService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    //1 se crean las variables finals para que las tome el constructor al igual que el @Qualifier para que lo tenga en cuenta lombook y haga la
    //inyección de dependencias via constructor,
    //2 se crea el metodo para convertir de Dto a entity y viceversa
    //3 se crean los metodos http
    private final IConsultService service;
    @Qualifier("consultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll(){
        List<ConsultDTO>lst=service.findAll().stream().map(consult->convertToDto(consult)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK); //esto garantiza el nivel 2 de madurez de richardson
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultDTO> findById(@Valid @RequestParam("id") Integer id){
        Consult obj = service.findById(id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }
    @PostMapping
    /*como este metodo va  ainsertar en las tablas maestro-detalle de manera conjunta,
     NO PUEDO USAR CONSULTDTO sino  pido el que tiene todoCombinadi que es ConsultList
     la idea es que en un solo POST se llenen dos tablas directamente
     */
    //La anotación @Valid sirve para que los constraint indicados en el DTO tengan efecto
    public ResponseEntity<ConsultDTO> save(@Valid @RequestBody ConsultListExamDTO dto){
        //se va sacando los datos del consultListExamDTO y s econvierte a cada entidad
        Consult cons = this.convertToEntity(dto.getConsult());
        /*Esta seria la manera tradicional de convertir las listas de dto a entities pero mapper tiene el metodoTypeToken para hacerlo de otra manera*/
        //List<Exam> exams = dto.getLstEXam().stream().map(dtoListExam -> mapper.map(dtoListExam,Exam.class)).collect(Collectors.toList());
        /* para evitar que tener que recorrer el map, s epuede usar typeToken que ofrece mapper para hacerlo de manera directa*/
        List<Exam> exams = mapper.map(dto.getLstEXam(), new TypeToken<List<Exam>>(){}.getType());

        //se crea un servicio en Consult. saveTransaccional que reciba los dos datos  y haga el insert en 3 tablas, consult, consult_detail y consultExam
        Consult obj = service.saveTransactional(cons,exams);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build(); //.body(obj);


    }
    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update (@Valid @RequestParam("id") Integer id, @RequestBody ConsultDTO dto){
        //si no encuentra el ID, hace un insert
        Consult obj = service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }
    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id){
        EntityModel<ConsultDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        //localhost:8080/patients/1

        /*para llegar al nivel 3 de madurez de richardson se crea esta clase e inprimir su propia url y agregar un bloque informativo*/

        /***
         * la url esta en this.getClass.findById, aca no se ejecuta el emtodo, solo trae el path  para traer la URL
         * esto se almacena en un WebMvcLinkBuilder
         */
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("patient-info1"));
        resource.add(link1.withRel("patient-info2"));
        return resource;
    }

    private ConsultDTO convertToDto(Consult obj){
        return mapper.map(obj, ConsultDTO.class);
    }
    private Consult convertToEntity(ConsultDTO dto){
        return mapper.map(dto,Consult.class);
    }

}
