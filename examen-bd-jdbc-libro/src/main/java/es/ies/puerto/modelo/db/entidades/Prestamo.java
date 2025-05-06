package es.ies.puerto.modelo.db.entidades;



import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;

import es.ies.puerto.modelo.db.entidades.comun.AbstractEntity;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class Prestamo extends AbstractEntity{
    private String idPrestamo;
    private String libroId;
    private String usuarioId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo() {
    }

    public Prestamo(String idPrestamo, String libroId, String usuarioId, Date fechaPrestamo, Date fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaPrestamo() {
        return getFecha(fechaPrestamo);
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return getFecha(fechaDevolucion);
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
