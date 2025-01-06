/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author wekisley
 */

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="login_id", referencedColumnName = "id")
    private Login login;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="project_id", referencedColumnName = "id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    @OneToMany(mappedBy = "person", cascade ={CascadeType.MERGE, CascadeType.REFRESH})
    private List<ProjectReport> projectReports;
    @OneToMany(mappedBy = "person", cascade ={CascadeType.MERGE, CascadeType.REFRESH})
    private List<TaskReport> taskReports;
    @OneToMany(mappedBy = "person", cascade ={CascadeType.MERGE, CascadeType.REFRESH})
    private List<ProjectMessage> projectMessages;
    private String name;
    private String address;
    private String phoneNumber;
    private String jobTitle;
    private boolean gender;
    
    public Person(){}
    
    public Person(String name, Login login, String address, String phoneNumber, String jobTitle, String gender){
        this.taskReports = new ArrayList<>();
        this.projectReports = new ArrayList<>();
        this.projectMessages = new ArrayList<>();
        this.name = name;
        this.login = login;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.gender = gender.toLowerCase().startsWith("m");
    }
    
    public void addProjectReport(ProjectReport report){
        if(!this.projectReports.contains(report)){
            this.projectReports.add(report);
        }
    }
    
    public void addTaskReport(TaskReport report){
        if(!this.taskReports.contains(report)){
            this.taskReports.add(report);
        }
    }
    
    public boolean equalsTo(Person person){
        if(person == null){
            return false;
        }
        return this.id == person.getId();
    }
    
    public boolean isInList(List<Person> persons){
        for(Person person: persons){
            if(this.equalsTo(person)){
                return true;
            }
        }
        return false;
    }
}
