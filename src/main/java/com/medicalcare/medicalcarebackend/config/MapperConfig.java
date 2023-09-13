package com.medicalcare.medicalcarebackend.config;


import com.medicalcare.medicalcarebackend.dto.ConsultDTO;
import com.medicalcare.medicalcarebackend.dto.MedicDTO;
import com.medicalcare.medicalcarebackend.model.Consult;
import com.medicalcare.medicalcarebackend.model.Medic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;

@Configuration //se añade esta nota cada vez que nosotros queramos que springboot quiera generar una instancia de una libreria externa y crear u bean
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean("medicMapper") //se añade para que se pueda diferenciar el tipo de mmaper que usara al convertir a dto o entity
    public ModelMapper medicMapper (){
        ModelMapper mapper = new ModelMapper();// aqui se le da un bean al springboot para que este maneje la instancia

        //Escritura
        /**TypeMap permote definir los atributos del mapper
         * */
        TypeMap<MedicDTO, Medic> typeMap1 = mapper.createTypeMap(MedicDTO.class, Medic.class);
        //pide un sourceGetter y el (dest,v)=Destino y valor asociado al destino
        typeMap1.addMapping(MedicDTO::getPrimaryName, (dest, v)-> dest.setFirstName((String) v));
        typeMap1.addMapping(medic -> medic.getSurname(), (dest, v)-> dest.setFirstName((String) v));
        typeMap1.addMapping(MedicDTO::getPhoto, (dest, v)-> dest.setPhotoUrl((String) v));

        //Lectura
        TypeMap<Medic,MedicDTO> typeMap2 = mapper.createTypeMap(Medic.class, MedicDTO.class);
        typeMap2.addMapping(medic -> medic.getFirstName(),(dest,v) -> dest.setPrimaryName((String) v));
        typeMap2.addMapping(Medic::getLastName,(dest,v) -> dest.setSurname((String) v));

        return mapper;
    }

    @Bean("consultMapper")
    public ModelMapper consultMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Consult, ConsultDTO> typeMap1 = mapper.createTypeMap(Consult.class, ConsultDTO.class);

        typeMap1.addMapping(e -> e.getMedic().getFirstName(), (dest, v) -> dest.getMedic().setPrimaryName((String) v));
        typeMap1.addMapping(e-> e.getMedic().getLastName(), (dest, v) -> dest.getMedic().setSurname((String) v));
        typeMap1.addMapping(e-> e.getMedic().getPhotoUrl(), (dest, v) -> dest.getMedic().setPhoto((String) v));

        return mapper;
    }

}
