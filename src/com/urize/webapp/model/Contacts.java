package com.urize.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contacts {
    String resume_uuid;
    ContactsType contactsType;
    String value;

}
