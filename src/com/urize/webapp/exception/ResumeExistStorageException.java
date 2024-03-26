package com.urize.webapp.exception;

import com.urize.webapp.storage.AbstractArrayStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResumeExistStorageException extends StorageException{
    private static final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);

    public ResumeExistStorageException(String uuid) {
        super("Resume with " + uuid + " is exist in storage", uuid);
        log.warn("Resume with {} is exist in storage", uuid);
    }
}
