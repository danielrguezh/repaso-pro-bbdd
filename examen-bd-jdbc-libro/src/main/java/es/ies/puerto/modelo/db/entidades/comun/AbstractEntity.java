package es.ies.puerto.modelo.db.entidades.comun;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author danielrguezh
 * @version 1.0.0
 */

public class AbstractEntity {

    /**
     * Metodo get que devuelve una fecha formateada y de tipo String
     * @param fecha a formatear
     * @return fecha formateada y de tipo String
     */
    public String getFecha(Date fecha) { 
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(fecha);
    }
}
