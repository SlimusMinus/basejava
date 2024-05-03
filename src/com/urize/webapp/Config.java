package com.urize.webapp;

import lombok.Getter;

import java.io.*;
import java.util.Properties;

@Getter
public class Config {
    private static final File STORAGE = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private final Properties properties = new Properties();
    private final File storageDir;

    public static Config getInstance(){
        return INSTANCE;
    }
    private Config(){
        try(InputStream is = new FileInputStream(STORAGE)){
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
