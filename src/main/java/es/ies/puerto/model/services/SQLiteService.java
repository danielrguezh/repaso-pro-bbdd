package es.ies.puerto.model.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.exception.RepasoException;
import es.ies.puerto.model.abstractas.Conexion;
import es.ies.puerto.model.entities.PersonaEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class SQLiteService extends Conexion{
    /**
     * Constructor por defecto
     */
    public SQLiteService() throws RepasoException {
        super();
    }

    /**
     * Contructor con la ruta del archivo de la bbdd
     * @param unaRutaArchivoBD path de la bbdd
     * @throws SQLException
     */
    public SQLiteService(String unaRutaArchivoBD) throws RepasoException {
        super(unaRutaArchivoBD);
    }


    /**
     * Metodo que busca una persona segun su dni
     * @param dni
     * @return persona
     * @throws RepasoException error controlado
     */
    public List<PersonaEntity> obtenerPersonaPorDni(String dni) throws RepasoException{
        String sql="SELECT * FROM personas WHERE dni=?";
        return obtenerPersona(sql, dni);
    }

    /**
     * Metodo que busca una persona segun cualquiera de sus atributos
     * @param input atributo
     * @return persona
     * @throws RepasoException error controlado
     */
    public List<PersonaEntity> obtenerPersonasPorInput(String input) throws RepasoException{
        String sql="SELECT * FROM personas WHERE dni=? OR fechaNacimiento=? OR zonaString=?";
        return obtenerPersona(sql,input);
    }


    /**
     * Metodo que obtiene todas las personas de la bbdd
     * @return lista de personas
     * @throws RepasoException error controlado
     */
    public List<PersonaEntity> obtenerPersonas() throws RepasoException{
        String sql="SELECT * FROM personas";
        return obtenerPersona(sql);
    }

    /**
     * Metodo que ejecuta una consulta sql para obtener usuarios
     * @param sql consulta
     * @param parametros de la consulta
     * @return lista de personas
     * @throws RepasoException error controlado
     */
    private List<PersonaEntity> obtenerPersona(String sql, String... parametros) throws RepasoException{
        List<PersonaEntity> personas= new ArrayList<>();

        try {
            PreparedStatement sentencia=getCon().prepareStatement(sql);
            for (int i = 0; i < parametros.length; i++) {
                sentencia.setString(i+1, parametros[i]);
            }
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {
                String dni=resultado.getString("dni"); 
                String fechaNacimiento=resultado.getString("fechaNacimiento");
                String zonaString=resultado.getString("zonaString");
                PersonaEntity personaEntity=new PersonaEntity(dni, fechaNacimiento, zonaString);
                personas.add(personaEntity);
            }

        } catch (SQLException e) {
            throw new RepasoException(e.getMessage(), e);
        }
        return personas;
    }



    /**
     * Metodo que inserta una persona en la bbdd
     * @param personaEntity a agregar
     * @return true/false
     * @throws RepasoException error controlado
     */
    public boolean insertarPersona(PersonaEntity personaEntity) throws RepasoException{
        String sql="INSERT INTO personas (dni, fechaNacimiento, zonaString) VALUES (?, ?, ?)";
        try (PreparedStatement sentencia=getCon().prepareStatement(sql)){
            sentencia.setString(1, personaEntity.getDni());
            sentencia.setString(2, personaEntity.getFechaNacimiento());
            sentencia.setString(3, personaEntity.getZonaString());
            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
        } finally{
            cerrar();
        }
        return false;
    }

    /**
     * Metodo para actualizar una persona de la bbdd
     * @param personaEntity a actualizar
     * @return true/false
     * @throws RepasoException error controlado
     */
    public boolean actualizarPersona(PersonaEntity personaEntity) throws RepasoException{
        String sql="UPDATE personas SET fechaNacimiento = ?, zonaString = ? WHERE dni = ?";
        try (PreparedStatement sentencia=getCon().prepareStatement(sql)){
            sentencia.setString(1, personaEntity.getFechaNacimiento());
            sentencia.setString(2, personaEntity.getZonaString());
            sentencia.setString(3, personaEntity.getDni());
            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
        } finally{
            cerrar();
        }
        return false;
    }

    /**
     * Metodo para borrar una persona de la bbdd
     * @param personaEntity a eliminar
     * @return true/false
     * @throws RepasoException error controlado
     */
    public boolean borrarPersona(PersonaEntity personaEntity) throws RepasoException{
        String sql="DELETE FROM personas WHERE dni = ?";
        try (PreparedStatement sentencia=getCon().prepareStatement(sql)){
            sentencia.setString(1, personaEntity.getDni());
            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
        } finally{
            cerrar();
        }
        return false;
    }
}
