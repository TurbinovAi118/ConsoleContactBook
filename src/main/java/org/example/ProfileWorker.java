package org.example;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileWorker {

    private final ContactsBookInitializer contactsBookInitializer;

    public ProfileWorker(ContactsBookInitializer contactsBookInitializer) {
        this.contactsBookInitializer = contactsBookInitializer;
    }

    public List<Contact> initContactsBooks(){
        return contactsBookInitializer.initContactsBook();
    }

    public void printContactsBook(){
        contactsBookInitializer.printContactsBook();
    }

    public void addContact(String contactString, Boolean append){
        contactsBookInitializer.addContact(contactString, append);
    }

    public void deleteContactByEmail(String email) {
        contactsBookInitializer.deleteContactByEmail(email);
    }
}