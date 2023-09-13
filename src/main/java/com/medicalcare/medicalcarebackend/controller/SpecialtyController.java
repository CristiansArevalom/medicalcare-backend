package com.medicalcare.medicalcarebackend.controller;


import com.medicalcare.medicalcarebackend.dto.SpecialtyDTO;
import com.medicalcare.medicalcarebackend.model.Specialty;
import com.medicalcare.medicalcarebackend.service.ISpecialtyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@AllArgsConstructor
public class SpecialtyController {
    //@Autowired

    //1 se crean las variables finals para que las tome el constructor al igual que el @Qualifier para que lo tenga en cuenta lombook y haga la
    //inyección de dependencias via constructor,
    //2 se crea el metodo para convertir de Dto a entity y viceversa
    //2.1 se debe validar si se debe ajustar el mmaper creando uno especifico en caso de que los nombres no correspondan
    //3 se crean los metodos http

    private final ISpecialtyService service;
    @Qualifier("defaultMapper")//esta anotación permite decir a cual Bean del MapperConfig se refiere para que la libreria Mapper pueda convertir de DTO a entity y viceversa
    /*Para que lo reconozca el proyecto, se debe agregar al proyecto el archivo lomok.config conel detalle de la configuración*/
    private final ModelMapper mapper;
    //es response Entity para poder manejar los metodos http

    //Como se usa el RequiredArgsConstructor ya no es encesario hacer la inyecion especificando en ester constructor
    /*public Specialtyontroller(ISpecialtytService service) {
        this.service = service;
    }*/

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO>lst=service.findAll().stream().map(speciality -> this.convertToDto(speciality)).collect(Collectors.toList());
        //gracias al mapper nos evitamos hacer esto:
        //List<SpecialtyRecord> lst = service.findAll().stream().map(e -> new SpecialtyRecord(e.getIdSpecialty(), e.getFirstName(), e.getLastName(), e.getDni(), e.getAddress(), e.getPhone(), e.getEmail())
            /*{
            SpecialtyDTO dto = new SpecialtyDTO();
            dto.setIdSpecialty(e.getIdSpecialty());
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
    public ResponseEntity findById(@Valid @RequestParam("id") Integer id){
        Specialty obj = service.findById(id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity save (@Valid @RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }*/

    //se crea este metodo para cumplir con el madurez de richardson n2 que retorna la url

    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@Valid @RequestBody SpecialtyDTO specialityDTO){
        Specialty obj = service.save(convertToEntity(specialityDTO));
        //para crear la URL que se debe devolver en el body CON EL id CREADO es
        //localhost:8080/specialitys/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpeciality()).toUri();
        return ResponseEntity.created(location).build();//.body(obj);

    }
    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update (@Valid @RequestParam("id") Integer id, @RequestBody SpecialtyDTO dto){
        //si no encuentra el ID, hace un insert
        Specialty obj = service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> delete (@Valid @PathVariable("id") Integer id, @RequestBody SpecialtyDTO dto){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*para llegar al nivel 3 de madurez de richardson se crea esta clase e inprimir su propia url y agregar un bloque informativo SE TIENE ESTE METODO*/
    @GetMapping("/hateoas/{id}")
    public EntityModel<SpecialtyDTO> findByIdHateoas(@PathVariable("id") Integer id){
        //localhost:8080/specialitys/1
        /***
         * la url esta en this.getClass.findById, aca no se ejecuta el metodo, solo trae el path  para traer la URL
         * esto se almacena en un WebMvcLinkBuilder
         */
        EntityModel<SpecialtyDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("patient-info1"));
        resource.add(link1.withRel("patient-info2"));
        return resource;
    }



    private SpecialtyDTO convertToDto(Specialty obj){
        return mapper.map(obj, SpecialtyDTO.class);
    }

    private Specialty convertToEntity(SpecialtyDTO dto){
        return mapper.map(dto, Specialty.class);
    }
}


