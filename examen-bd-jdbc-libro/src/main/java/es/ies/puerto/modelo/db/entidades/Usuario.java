package es.ies.puerto.modelo.db.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.ies.puerto.modelo.db.entidades.comun.AbstractEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class Usuario extends AbstractEntity{
    private String idUsuario;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;

    public Usuario() {}

    public Usuario(String idUsuario, String nombre, String email, String telefono, Date fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getFechaRegistro() { return getFecha(fechaRegistro); }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

}
