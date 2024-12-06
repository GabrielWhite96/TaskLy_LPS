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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import static utils.DateFunctions.getCurrentDate;

/**
 *
 * @author wekisley
 */
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy="task", cascade=CascadeType.ALL)
    private List<TaskReport> reports;
    @OneToMany(mappedBy="task", cascade=CascadeType.ALL)
    private List<TaskMessage> messages;
    @ManyToMany(mappedBy="tasks", cascade=CascadeType.ALL)
    private List<Person> users;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    
    public Task(){}
    
    public Task(String title, String description, Project project){
        this.project = project;
        this.reports = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.title = title;
        this.description = "";
        this.status = "Aguardando";
        this.createdAt = getCurrentDate();
    }
    
    public void addReport(Task task, String title, String description){
        TaskReport report = new TaskReport(task, title, description);
        this.reports.add(report);
    }
}
