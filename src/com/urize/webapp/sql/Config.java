package com.urize.webapp.sql;

import com.urize.webapp.storage.SqlStorage;
import com.urize.webapp.storage.Storage;
import lombok.Getter;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Getter
public class Config {
    private static final File STORAGE = new File(getHomeDir(), "config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private final Properties properties = new Properties();
    private final File storageDir;

    private final Storage storage;

    public static Config getInstance() {
        return INSTANCE;
    }

    private final Set<String> immutableUuids = new HashSet<String>() {{  // for JDK 9+: Set.of("111", "222");
        add("11111111-1111-1111-1111-111111111111");
        add("22222222-2222-2222-2222-222222222222");
    }};

    private Config() {
        try (InputStream is = new FileInputStream(STORAGE)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "." : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + "is not directory");
        }
        return homeDir;
    }

    public boolean isImmutable(String uuids) {
        return immutableUuids.contains(uuids);
    }
}
