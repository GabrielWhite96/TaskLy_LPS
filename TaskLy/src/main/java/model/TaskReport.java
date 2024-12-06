/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import utils.DateFunctions;

/**
 *
 * @author wekisley
 */
@Entity
public class TaskReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Task task;
    private String title;
    private String description;
    private String date; 
    
    public TaskReport(Task task, String title, String description){
        this.task = task;
        this.title = title;
        this.description = description;
        this.date = DateFunctions.getCurrentDate();
    }
}
