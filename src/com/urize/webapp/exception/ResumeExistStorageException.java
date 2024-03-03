package com.urize.webapp.exception;

public class ResumeExistStorageException extends StorageException{
    public ResumeExistStorageException(String uuid) {
        super("Resume with " + uuid + " is exist in storage", uuid);
    }
}
