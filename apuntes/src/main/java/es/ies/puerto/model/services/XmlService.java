package es.ies.puerto.model.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.ies.puerto.model.entities.PersonaEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class XmlService {
    private  static final String PATH="src/main/resources/persona.xml";
    private List<PersonaEntity> personas;

    public XmlService(){
        personas=new ArrayList<>();
        File archivo=new File(PATH);
        if (!archivo.exists()) {
            System.err.println("Archivo no encontrado: "+PATH);
            return;
        } 
        try {
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document document=builder.parse(archivo);

            NodeList lista=document.getElementsByTagName(PATH);
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo=lista.item(i);
                if (nodo.getNodeType()==Node.ELEMENT_NODE) {
                    Element elemento= (Element) nodo;
                    String dni=elemento.getElementsByTagName("dni").item(0).getTextContent();
                    String fechaNacimiento=elemento.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                    String zonaString=elemento.getElementsByTagName("zonaString").item(0).getTextContent();

                    personas.add(new PersonaEntity(dni,fechaNacimiento,zonaString));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
        }
    }

    /**
     * Metodo que obtiene la lista de personas del fichero
     * @return
     * @throws Exception
     */
    public List<PersonaEntity> obtenerPersonas() throws Exception {
        return new ArrayList<>(personas);
    }

    /**
     * Metodo que busca un persona
     * @param persona
     * @return persona a buscar
     * @throws Exception
     */
    public PersonaEntity obtenerPokemon(PersonaEntity persona) throws Exception {
        if (persona == null) {
            return null;
        }
        if (!personas.contains(persona)) {
            return null;
        }
        for (PersonaEntity personaBuscar : personas) {
            if (personaBuscar.equals(persona)) {
                return personaBuscar;
            }
        }
        return null;
    }

    /**
     * Metodo que agrega una persona a la lista
     * @param persona
     * @throws Exception
     */
    public void addPersona(PersonaEntity persona) throws Exception {
        if (persona == null || personas.contains(persona)) {
            return;
        }
        personas.add(persona);
        volcarFichero(personas);
    }

    /**
     * Metodo que elimina una persona de la lista
     * @param persona
     * @throws Exception
     */
    public void deletePersona(PersonaEntity persona) throws Exception {
        if (persona != null && personas.contains(persona)) {
            personas.remove(persona);
            volcarFichero(personas);
        }
    }

    /**
     * Metodo que actualiza la informacion de una persona
     * @param persona
     * @throws Exception
     */
    public void updatePersona(PersonaEntity persona) throws Exception {
        int posicion = personas.indexOf(persona);
        if (persona != null && posicion >= 0) {
            personas.set(posicion, persona);
            volcarFichero(personas);
        }
    }

    /**
     * Metodo que sobreescribe los datos del fichero xml
     * @param personaEntities
     */
    public void volcarFichero(List<PersonaEntity> personaEntities){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("personas");
            document.appendChild(root);
            
            for (PersonaEntity persona : personaEntities) {
                Element personaXml= document.createElement("persona");
                root.appendChild(personaXml);

                Element dni=document.createElement("dni");
                dni.appendChild(document.createTextNode(persona.getDni()));
                personaXml.appendChild(dni);

                Element fechaNacimiento=document.createElement("fechaNacimiento");
                fechaNacimiento.appendChild(document.createTextNode(persona.getFechaNacimiento()));
                personaXml.appendChild(fechaNacimiento);

                Element zonaString=document.createElement("zonaString");
                zonaString.appendChild(document.createTextNode(persona.getZonaString()));
                personaXml.appendChild(zonaString);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(PATH));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.err.println("Error al escribir en el archivo XML: " + e.getMessage());
        }
    }
}