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
public class TaskMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Task task;
    private String content;
    private String date;
    
    public TaskMessage(){}

    public TaskMessage(String content, Task task, Person person){
        this.content = content;
        this.task = task;
        this.person = person;
        this.date = DateFunctions.getCurrentDate();
    }

}
