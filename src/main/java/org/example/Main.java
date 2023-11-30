package org.example;

import org.example.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        context.getBean(ProfileWorker.class).initContactsBooks();

        Scanner console = new Scanner(System.in);

        while (true){
            switch (console.nextLine()) {
                case "ADD" -> {
                    System.out.println("Enter a new contact in format 'Иванов Иван Иванович; +89099999999; someEmail@example.example'");
                    context.getBean(ProfileWorker.class).addContact(console.nextLine(), true);
                }
                case "PRINT" -> context.getBean(ProfileWorker.class).printContactsBook();
                case "HELP" -> {
                    System.out.println("Command list: ");
                    System.out.println("HELP ---> Displays a list of commands.");
                    System.out.println("PRINT ---> Displays a list of contacts.");
                    System.out.println("ADD ---> Makes a new contact.");
                    System.out.println("REMOVE ---> Removes a contact by e-mail.");
                    System.out.println("STOP ---> Closes the program.");
                }
                case "REMOVE" -> {
                    System.out.println("Enter the e-mail address of the contact you want to delete in format 'someEmail@example.example'");
                    context.getBean(ProfileWorker.class).deleteContactByEmail(console.nextLine());
                }
                case "STOP" -> {
                    return;
                }
                default -> System.out.println("Enter 'HELP' to see a list of commands.");
            }
        }
    }
}
