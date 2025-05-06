package es.ies.puerto;

import es.ies.puerto.model.entities.PersonaEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class Demo {
    public static void main(String[] args) {
        PersonaEntity persona=new PersonaEntity("12345678J", "01/06/2005", "America/New_York");
        System.out.println(persona.obtenerEdad());
        System.out.println(persona.obtenerHoraLocalZonedDateTime());
    }
}