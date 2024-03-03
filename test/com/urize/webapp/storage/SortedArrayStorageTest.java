package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

}