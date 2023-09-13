package com.medicalcare.medicalcarebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/*como JPA pide de la definición del Objeto, entonces se debe usar un Gnerico para representar el tipo de dato, este puede ser el T

Igual esto genera un error ya que por la anotación repository por debajo intenta crear un BEan.
COMO ACA NO SABE COMO CREAR UNA INSTANCIA DEL T, ENTONCES SE DEBE ANULAR ESE COMPORTAMIENTO Y PARA ELLO SE INDICA QUE NO GENERE BEANS CON @NOREPOSITORYBEAN
No es obluigaroio  usar la anotación @Repository YA QUE COMO SE EXTIENDE DE jparepository POR DEBAJO YA SE EST AUSANDO ESA NOTACIÓN

 * */
@NoRepositoryBean
public interface IGenericRepo <T, ID> extends JpaRepository<T, ID> {
    /*
    Se crea esta clase de Igeneric repo para abstraer la logica basica del crud y desacoplar el uso de
    jpa repository para que en caso de cambiar de implementación de JPA, solo sea modificar esta interfaz
     */
}
