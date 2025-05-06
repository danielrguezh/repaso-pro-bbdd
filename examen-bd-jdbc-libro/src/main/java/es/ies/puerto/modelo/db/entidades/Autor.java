package es.ies.puerto.modelo.db.entidades;


import java.util.Date;

import es.ies.puerto.modelo.db.entidades.comun.AbstractEntity;

import java.text.ParseException;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class Autor extends AbstractEntity{
    private String dni;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    

    public Autor() {}

    public Autor(String dni, String nombre, String nacionalidad, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }
 

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getFechaNacimiento() { 
        return getFecha(fechaNacimiento);
    }
    public void setFechaNacimiento(Date fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento; 
    }    
    
    

}