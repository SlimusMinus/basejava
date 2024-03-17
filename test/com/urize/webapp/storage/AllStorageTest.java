package com.urize.webapp.storage;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        AbstractStorageTest.class,
        StorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
})
public class AllStorageTest {
}
