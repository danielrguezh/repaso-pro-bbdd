package es.ies.puerto.model.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ies.puerto.model.entities.PersonaEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class JsonService {
    ObjectMapper objectMapper;
    String path = "src/main/resources/persona.json";
    File file;
    List<PersonaEntity> personas;

    /**
     * Constructor por defecto
     */
    public JsonService(){
        objectMapper = new ObjectMapper();
        file = new File(path);
        loadFile(file);
    }

    /**
     * Metodo que carga los datos del fichero json
     * @param file
     */
    private void loadFile(File file) {
        try {
            personas = objectMapper.readValue(file, new TypeReference<List<PersonaEntity>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que sobreescribe el fichero json
     * @param file
     * @param personas
     */
    public void saveFile(File file, List<PersonaEntity> personas) {
        try {
            objectMapper.writeValue(file, personas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean addPersona(PersonaEntity persona) {
        if (persona == null) {
            return false;
        }
        int posicion=personas.indexOf(persona);
        if (posicion >= 0) {
            return false;
        }
        boolean insertar=personas.add(persona);
        if (insertar) {
            saveFile(file, personas);
        }
       return insertar;
    }

    
    public PersonaEntity search(String dni){
        PersonaEntity persona=new PersonaEntity(dni);
        int posicion=personas.indexOf(persona);
        if (posicion<0) {
            return null;
        }
        return personas.get(posicion);

    }

    public boolean deletePersona(PersonaEntity persona) {
        if (persona == null) {
            return false;
        }
        boolean borrar=personas.remove(persona);
        if (borrar) {
            saveFile(file, personas);
        }
        return borrar;
    }

    public List<PersonaEntity> getPersonas() {
        return personas;
    }
}
