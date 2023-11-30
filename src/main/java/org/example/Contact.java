package org.example;

import java.text.MessageFormat;

public class Contact {

    String name;
    String phoneNumber;
    String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void printContact(){
        System.out.println(MessageFormat.format("{0} | {1} | {2}", name, phoneNumber, email));
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0};{1};{2}", name, phoneNumber, email);
    }

}
