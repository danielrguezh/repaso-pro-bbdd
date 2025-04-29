package es.ies.puerto.model.abstractas;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.ies.puerto.config.AppConfig;
import es.ies.puerto.exception.RepasoException;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class Conexion extends AppConfig{
    private Connection con;
    private String rutaBBDD;

    /**
     * Constructor por defecto
     * Ruta por defecto: src/main/resources/repaso.db
     * @throws RepasoException error controlado
     */
    public Conexion() throws RepasoException{
        super();
        this.rutaBBDD=getUrlBd();
    }

    /**
     * Constructor insertando una nueva ruta de la bbdd
     * @param unaRutaBBDD a seleccionar
     * @throws RepasoException error controlado
     */
    public Conexion(String unaRutaBBDD) throws RepasoException {
        if (unaRutaBBDD==null || unaRutaBBDD.isEmpty()) {
            throw new RepasoException("la ruta es nula o vacia", new SQLException());
        }
        File fichero= new File(unaRutaBBDD);
        if (!fichero.exists() || !fichero.isFile()) {
            throw new RepasoException("no existe la bbdd: "+unaRutaBBDD, new SQLException());
        }
        this.rutaBBDD= unaRutaBBDD;
    }


    

    public String getRutaBBDD() {
        return this.rutaBBDD;
    }

    public Connection getCon()  {
        try {
            if (con==null) {
                con = DriverManager.getConnection("jdbc:sqlite" + rutaBBDD);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return this.con;
    }

    /**
     * Metodo que controla con la bbdd
     * @return Connection
     * @throws RepasoException
     */
    public Connection conectar() throws RepasoException{
        if (con==null) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite" + rutaBBDD);
            } catch (SQLException e) {
                throw new RepasoException(e.getMessage(), e);
            }
        }
        return con;
    }

    /**
     * Metodo que cierra la conexion a la bbdd
     * @throws RepasoException error controlado
     */
    public void cerrar() throws RepasoException{
        try {
            if (con!=null || !con.isClosed()) {
                con.close();
                con =null;
            }
        } catch (SQLException e) {
            throw new RepasoException(e.getMessage(), e);
        }
    }

}
