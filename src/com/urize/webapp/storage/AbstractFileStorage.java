package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AbstractFileStorage {
    private static final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);

    public static void saveResumeInFile(Resume resume, String path){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(resume.toString());
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
            log.warn("IOException got {}", exception.getMessage());
        }
    }
}
