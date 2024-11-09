/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author wekisley
 */
public class DateFunctions {
    public static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(format);
    }
}
