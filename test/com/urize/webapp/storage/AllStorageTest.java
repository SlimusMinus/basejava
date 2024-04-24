package com.urize.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
@Suite
@SelectClasses({
        AbstractStorageTest.class,
        StorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorage.class,
        PathStorageTest.class,
        FileStorageTest.class,
        XmlPathStorageTest.class,
        JsonPathStorageTest.class
})
public class AllStorageTest {

}
