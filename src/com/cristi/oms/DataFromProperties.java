package com.cristi.oms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataFromProperties {

private static final String FILE = "media.properties";

public static Properties readPropertiez(){
        Properties properties = new Properties();
        try(FileInputStream fisier = new FileInputStream(FILE)){
            properties.load(fisier);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return properties;
}
}
