/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 *
 * @author kensl
 */
public class BerlinClock {

    public String[] convert(String timeString) {
        LocalTime time;
        try {
            
            time = LocalTime.parse(timeString);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Formato de hora inválido. Use 'HH:mm:ss'.");
        }

        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        return new String[]{
            getSecondsLamp(seconds),
            getFiveHoursRow(hours),
            getSingleHoursRow(hours),
            getFiveMinutesRow(minutes),
            getSingleMinutesRow(minutes)
        };
    }

   
    private String getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? "Y" : "O";
    }

    
    private String getFiveHoursRow(int hours) {
        int numLampsOn = hours / 5;
        return "R".repeat(numLampsOn) + "O".repeat(4 - numLampsOn);
    }

    
    private String getSingleHoursRow(int hours) {
        int numLampsOn = hours % 5;
        return "R".repeat(numLampsOn) + "O".repeat(4 - numLampsOn);
    }

    
    private String getFiveMinutesRow(int minutes) {
        int numLampsOn = minutes / 5;
        StringBuilder row = new StringBuilder();
        for (int i = 1; i <= 11; i++) {
            if (i <= numLampsOn) {
                // Lâmpadas 3, 6, 9 são vermelhas (15, 30, 45 min)
                row.append((i % 3 == 0) ? "R" : "Y");
            } else {
                row.append("O");
            }
        }
        return row.toString();
    }

   
    private String getSingleMinutesRow(int minutes) {
        int numLampsOn = minutes % 5;
        return "Y".repeat(numLampsOn) + "O".repeat(4 - numLampsOn);
    }
}
