package es.ies.puerto.model.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.model.entities.PersonaEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class CsvService {
    private static final String PATH = "src/main/resources/persona.csv";
    private List<PersonaEntity> personas;

    /**
     * Constructor por defecto
     */
    public CsvService(){
        personas=new ArrayList<>();
        obtenerData();
    }

    /**
     * Metodo que carga los datos del fichero csv
     */
    public void obtenerData(){
        File archivo = new File(PATH);
        if (!archivo.exists()) {
            System.err.println("Archivo CSV no encontrado: " + PATH);
            return;
        }
        try (BufferedReader br= new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    personas.add(new PersonaEntity(datos[0],datos[1],datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Metodo que sobreescribe los datos del fichero csv
     */
    public void volcarFichero(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH, false))){
            for (PersonaEntity persona : personas) {
                bw.write(persona.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Metodo que obtiene las personas del fichero
     * @return lista de personas
     */
    public List<PersonaEntity> obtenerPersonas() {
        return personas;
    }

    /**
     * Metodo que obtiene una persona del fichero
     * @param persona
     * @return persona obtenida
     */
    public PersonaEntity obtenerPersona(PersonaEntity persona) {
        if (persona == null) {
            return null;
        }
        int posicion = personas.indexOf(persona);
        if (posicion < 0) {
            return null;
        }

        return personas.get(posicion);
    }

    /**
     * Metodo que agrega una persona
     * @param persona
     */
    public void addArma(PersonaEntity persona) {
        if (persona == null || personas.contains(persona)) {
            return;
        }
        personas.add(persona);
        volcarFichero();   
    }

    /**
     * Metodo que elimina una persona
     * @param persona
     */
    public void deletePersona(PersonaEntity persona) {
        if (persona == null || !personas.contains(persona)) {
            return;
        }
        personas.remove(persona);
        volcarFichero();
    }

    /**
     * Metodo que actualiza la informacion de una persona
     * @param persona
     */
    public void updatePersona(PersonaEntity persona) {
        if (persona == null || !personas.contains(persona)) {
            return;
        }
        int index = personas.indexOf(persona);
        if (index >= 0) {
            personas.set(index, persona);
            volcarFichero();
        }
    }
}
