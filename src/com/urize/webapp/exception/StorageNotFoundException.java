package com.urize.webapp.exception;

public class StorageNotFoundException extends StorageException{
    public StorageNotFoundException(String uuid) {
        super("Resume with " + uuid + " not found in storage", uuid);
    }
}
