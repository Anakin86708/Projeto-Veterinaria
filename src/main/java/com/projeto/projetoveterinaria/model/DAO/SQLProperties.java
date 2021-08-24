package com.projeto.projetoveterinaria.model.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel
 */
public class SQLProperties  {
    public static final String PATH = ".sqlStatements.properties";
    private static Properties properties;
    
    public static Properties getProperties() {
        if (properties == null) {
            SQLProperties.properties = new Properties();
            try {
                properties.load(new FileInputStream(PATH));
            } catch (FileNotFoundException  ex) {
                System.err.println("EXCEPTION: " + ex.getMessage());
            } catch (IOException ex) {
                System.err.println("EXCEPTION: " + ex.getMessage());
            }
        }
        return properties;
    }
    
}
