package com.urize.webapp.sql;

import com.urize.webapp.storage.SQLStorage;
import com.urize.webapp.storage.Storage;
import lombok.Getter;

import java.io.*;
import java.util.Properties;

@Getter
public class Config {
    private static final File STORAGE = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private final Properties properties = new Properties();
    private final File storageDir;

    private final Storage storage;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(STORAGE)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SQLStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}