package com.urize.webapp.storage.list;

import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.Storage;
import com.urize.webapp.storage.array.AbstractArrayStorageTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }
}