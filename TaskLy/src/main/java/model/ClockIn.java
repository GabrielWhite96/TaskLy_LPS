/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import utils.DateFunctions;

/**
 *
 * @author wekisley
 */

@Entity
@Data
public class ClockIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;
    private String dateStart;
    private String startHour;
    private String endHour;
    private String dateEnd;

    public ClockIn() {}

    public ClockIn(Person person) {
        this.person = person;
        this.startHour = "XX:XX";
        this.endHour = "XX:XX";
    }
    
    public void start(){
        this.dateStart = DateFunctions.getCurrentDate();
        this.startHour = DateFunctions.getCurrentHour();
    }
    
    public void stop(){
        this.dateEnd = DateFunctions.getCurrentDate();
        this.endHour = DateFunctions.getCurrentHour();
    }
    
    public boolean isOpened(){
        return (
            (endHour == null || endHour == "XX:XX")
            &&
            (startHour != null && startHour != "XX:XX")
        );
    }
}
