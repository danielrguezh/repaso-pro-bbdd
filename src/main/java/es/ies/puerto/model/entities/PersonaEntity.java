package es.ies.puerto.model.entities;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class PersonaEntity {
    private String dni;
    private String fechaNacimiento;
    private String zonaString;

    /**
     * Constructor vacio
     */
    public PersonaEntity() {
    }

    /**
     * Constructor con identificador
     * @param dni
     */
    public PersonaEntity (String dni) {
        this.dni=dni;
    }

    /**
     * Constructor con propiedades
     * @param dni
     * @param fechaNacimiento
     * @param zonaString
     */
    public PersonaEntity(String dni, String fechaNacimiento, String zonaString) {
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.zonaString = zonaString;
    }

    /**
     * Metodo que optiene la edad de una persona
     * @return edad
     */
    public int obtenerEdad(){
        if (fechaNacimiento==null || fechaNacimiento.isEmpty()) {
            return -1;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);
        return (int) ChronoUnit.YEARS.between(fecha, LocalDate.now());
    }

    /**
     * Metodo que obtiene la fecha y hora en la zona horaria de la persona
     * @return ZonedDateTime con los datos en la localidad de la persona
     */
    public ZonedDateTime obtenerHoraLocalZonedDateTime(){
        return ZonedDateTime.now(ZoneId.of(zonaString));
    }


    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getZonaString() {
        return this.zonaString;
    }

    public void setZonaString(String zonaString) {
        this.zonaString = zonaString;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonaEntity)) {
            return false;
        }
        PersonaEntity persona = (PersonaEntity) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "{" +
            " dni='" + getDni() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", zonaString='" + getZonaString() + "'" +
            "}";
    }
    
}
