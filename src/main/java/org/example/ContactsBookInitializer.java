package org.example;

import java.util.List;

public interface ContactsBookInitializer {

    List<Contact> initContactsBook();

    void printContactsBook();

    void addContact(String contact, Boolean append);

    void deleteContactByEmail(String email);

}
