package es.ies.puerto.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import es.ies.puerto.exception.RepasoException;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class AppConfig {
    String urlBd;

    /**
     * Constructor por defecto
     * @throws RepasoException error controlado
     */
    public AppConfig() throws RepasoException{
        Properties properties= new Properties();
        try (FileInputStream fis= new FileInputStream("src/main/resources/app.properties")){
            properties.load(fis);
            urlBd= (String) properties.get("urlBd");
        } catch (IOException e) {
            throw new RepasoException(e.getMessage(),e);
        }
    }

    public String getUrlBd() {
        return this.urlBd;
    }
}
