package com.medicalcare.medicalcarebackend.controller;


import com.medicalcare.medicalcarebackend.dto.ExamDTO;
import com.medicalcare.medicalcarebackend.model.Exam;
import com.medicalcare.medicalcarebackend.service.IExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // @Controller + @Responsebody
@RequestMapping("/exams") // emdpoints | sustantivos prural
@RequiredArgsConstructor
public class ExamController {
    //@Autowired
    private final IExamService service;

    @Qualifier ("defaultMapper") //esta anotación permite decir a cual Bean del MapperConfig se refiere para que la libreria Mapper pueda convertir de DTO a entity y viceversa
    /*Para que lo reconozca el proyecto, se debe agregar al proyecto el archivo lomok.config conel detalle de la configuración*/
    private final ModelMapper mapper;

    //Como se usa el RequiredArgsConstructor ya no es encesario hacer la inyecion especificando en ester constructor
    /*public ExamController(IExamService service) {
        this.service = service;
    }*/



    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll(){
        List<ExamDTO> lst = service.findAll().stream().map(this::convertToDto).toList();
        //List<ExamRecord> lst = service.findAll().stream().map(e -> new ExamRecord(e.getIdExam(), e.getFirstName(), e.getLastName(), e.getDni(), e.getAddress(), e.getPhone(), e.getEmail())
            /*{
            ExamDTO dto = new ExamDTO();
            dto.setIdExam(e.getIdExam());
            dto.setPrimaryName(e.getFirstName());
            dto.setSurname(e.getLastName());
            dto.setDni(e.getDni());
            dto.setEmail(e.getEmail());
            dto.setAddress(e.getAddress());
            return dto;
        }*/
        //).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id){
        Exam obj = service.findById(id);
            return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Exam> save(@RequestBody Exam exam){
        Exam obj = service.save(exam);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }*/

    @PostMapping
    //La anotación @Valid sirve para que los constraint indicados en el DTO tengan efecto
    public ResponseEntity<ExamDTO> save(@Valid @RequestBody ExamDTO dto){
        Exam obj = service.save(convertToEntity(dto));
        //localhost:8080/exams/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build(); //.body(obj);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ExamDTO dto){
        //si no encuentra el ID, hace un insert
        Exam obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id){
        EntityModel<ExamDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        //localhost:8080/exams/1

        /*para llegar al nivel 3 de madurez de richardson se crea esta clase e inprimir su propia url y agregar un bloque informativo*/

        /***
         * la url esta en this.getClass.findById, aca no se ejecuta el emtodo, solo trae el path  para traer la URL
         * esto se almacena en un WebMvcLinkBuilder
         */
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("exam-info1"));
        resource.add(link1.withRel("exam-info2"));
        return resource;
    }

    private ExamDTO convertToDto(Exam obj){
        return mapper.map(obj, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO dto){
        return mapper.map(dto, Exam.class);
    }
}
