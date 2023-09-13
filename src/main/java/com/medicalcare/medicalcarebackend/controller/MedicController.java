package com.medicalcare.medicalcarebackend.controller;


import com.medicalcare.medicalcarebackend.dto.MedicDTO;
import com.medicalcare.medicalcarebackend.model.Medic;
import com.medicalcare.medicalcarebackend.service.IMedicService;
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
public class MedicController {
    //@Autowired

    //1 se crean las variables finals para que las tome el constructor al igual que el @Qualifier para que lo tenga en cuenta lombook y haga la
    //inyección de dependencias via constructor,
    //2 se crea el metodo para convertir de Dto a entity y viceversa
    //2.1 se debe validar si se debe ajustar el mmaper creando uno especifico en caso de que los nombres no correspondan
    //3 se crean los metodos http

    private final IMedicService service;
    @Qualifier("medicMapper")//esta anotación permite decir a cual Bean del MapperConfig se refiere para que la libreria Mapper pueda convertir de DTO a entity y viceversa
    /*Para que lo reconozca el proyecto, se debe agregar al proyecto el archivo lomok.config conel detalle de la configuración*/
    private final ModelMapper mapper;
    //es response Entity para poder manejar los metodos http

    //Como se usa el RequiredArgsConstructor ya no es encesario hacer la inyecion especificando en ester constructor
    /*public Medicontroller(IMedictService service) {
        this.service = service;
    }*/

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO>lst=service.findAll().stream().map(medic -> this.convertToDto(medic)).collect(Collectors.toList());
        //gracias al mapper nos evitamos hacer esto:
        //List<MedicRecord> lst = service.findAll().stream().map(e -> new MedicRecord(e.getIdMedic(), e.getFirstName(), e.getLastName(), e.getDni(), e.getAddress(), e.getPhone(), e.getEmail())
            /*{
            MedicDTO dto = new MedicDTO();
            dto.setIdMedic(e.getIdMedic());
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
        Medic obj = service.findById(id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity save (@Valid @RequestBody MedicDTO dto){
        Medic obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }*/

    //se crea este metodo para cumplir con el madurez de richardson n2 que retorna la url

    @PostMapping
    public ResponseEntity<MedicDTO> save(@Valid @RequestBody MedicDTO medicDTO){
        Medic obj = service.save(convertToEntity(medicDTO));
        //para crear la URL que se debe devolver en el body CON EL id CREADO es
        //localhost:8080/medics/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();//.body(obj);

    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update (@Valid @RequestParam("id") Integer id, @RequestBody MedicDTO dto){
        //si no encuentra el ID, hace un insert
        Medic obj = service.update(convertToEntity(dto),id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MedicDTO> delete (@Valid @PathVariable("id") Integer id, @RequestBody MedicDTO dto){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*para llegar al nivel 3 de madurez de richardson se crea esta clase e inprimir su propia url y agregar un bloque informativo SE TIENE ESTE METODO*/
    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicDTO> findByIdHateoas(@PathVariable("id") Integer id){
        //localhost:8080/medics/1
        /***
         * la url esta en this.getClass.findById, aca no se ejecuta el metodo, solo trae el path  para traer la URL
         * esto se almacena en un WebMvcLinkBuilder
         */
        EntityModel<MedicDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("patient-info1"));
        resource.add(link1.withRel("patient-info2"));
        return resource;
    }


    private MedicDTO convertToDto(Medic obj){
        return mapper.map(obj,MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO dto){
        return mapper.map(dto, Medic.class);
    }

}
