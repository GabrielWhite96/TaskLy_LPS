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
public class ProjectReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Project project;
    private String title;
    private String description;
    private String date;
    
    public ProjectReport(Project project, Person person, String title, String description){
        this.person = person;
        this.project = project;
        this.title = title;
        this.description = description;
        this.date = DateFunctions.getCurrentDate();
    }
}
