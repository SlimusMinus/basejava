package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractFileStorageTest {
    private final ResumeTestData resumeTestData = new ResumeTestData();
    private final Resume fillResume = resumeTestData.fillResume("Grigoriy Kislin");

    @Test
    void saveResumeInFile() {

    }
}