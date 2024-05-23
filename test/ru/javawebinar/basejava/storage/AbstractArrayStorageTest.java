package ru.javawebinar.basejava.storage;

import com.urize.webapp.exception.StorageException;
import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.Storage;
import org.junit.jupiter.api.Test;


public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }


}