package org.example;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmptyContactsBook implements ContactsBookInitializer {

    @Value("${contacts.book}")
    private String contactBook;

    private final List<Contact> contactList = new ArrayList<>();
    private final Pattern nameFormat = Pattern.compile("[A-Za-zА-Яа-я\s]+");
    private final Pattern phoneNumberPattern = Pattern.compile("\\+\\d{11}");
    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public List<Contact> initContactsBook() {
        try (Scanner scanner = new Scanner(new File(contactBook))) {
            while (scanner.hasNextLine()) {
                addContact(scanner.nextLine(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }

    @Override
    public void printContactsBook() {
        System.out.println("Contacts book:" + (contactList.isEmpty() ? "\nEmpty" : ""));
        contactList.forEach(Contact::printContact);
    }

    @Override
    public void addContact(String contactString, Boolean append) {
        List<String> stringContact = Arrays.asList(contactString.split(";"));
        if (stringContact.size() > 3) {
            System.out.println("Wrong input format!");
            return;
        }
        if (!nameFormat.matcher(stringContact.get(0).trim()).matches()) {
            System.out.println("Wrong name format!");
            return;
        }
        if (!phoneNumberPattern.matcher(stringContact.get(1).trim()).matches()) {
            System.out.println("Wrong phone number format!");
            return;
        }
        if (!emailPattern.matcher(stringContact.get(2).trim()).matches()) {
            System.out.println("Wrong e-mail format!");
            return;
        }


        Contact contact = new Contact(stringContact.get(0).trim(), stringContact.get(1).trim(), stringContact.get(2).trim());
        contactList.add(contact);
        if (append)
            writeContact(contact.toString(), true);
    }

    @Override
    public void deleteContactByEmail(String email) {
        if (!emailPattern.matcher(email).matches()) {
            System.out.println("Wrong e-mail format!");
            return;
        }
        contactList.removeIf(contact -> {
            if (contact.email.equals(email)) {
                writeContact("", false);
                System.out.println(MessageFormat.format("Contact with e-mail {0} was removed.", email));
                return true;
            }
            return false;
        });
        contactList.forEach(contact -> writeContact(contact.toString(), true));
    }

    private void writeContact(String contact, Boolean append) {
        try (FileWriter writer = new FileWriter(contactBook, append)) {
            writer.write(contact);
            if (append)
                writer.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
