package com.marcinplonski.conferenceapp.reservations;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmailNotification {

    public void sendNotification(String email, String login) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataWyslania = dateFormat.format(new Date());

            FileWriter writer = new FileWriter("powiadomienia.txt", true);
            writer.write("Data wysłania: " + dataWyslania + "\n");
            writer.write("Do: " + login + " na email: " + email + "\n");
            writer.write("Treść: Rezerwacja przebiegła prawidłowo" + "\n");
            writer.write("-------------------------------------\n");
            writer.close();

            System.out.println("Powiadomienie zapisane do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania powiadomienia do pliku.");
            e.printStackTrace();
        }
    }
}
