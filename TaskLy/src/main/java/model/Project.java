package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import static utils.DateFunctions.getCurrentDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wekisley
 */
@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    private List<ProjectReport> reports;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    private List<Task> tasks;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    private List<ProjectMessage> messages;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    private List<Person> persons;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    
    public Project(){}
    
    public Project(String title, String description){
        this.tasks = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.title = title;
        this.description = "";
        this.status = "Aguardando";
        this.createdAt = getCurrentDate();
    }
}
