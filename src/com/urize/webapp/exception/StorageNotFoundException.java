package com.urize.webapp.exception;

import com.urize.webapp.storage.AbstractArrayStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageNotFoundException extends StorageException{
    private static final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);
    public StorageNotFoundException(String uuid) {
        super("Resume with " + uuid + " not found in storage", uuid);
        log.warn("Resume with {} not found in storage", uuid);
    }
}
