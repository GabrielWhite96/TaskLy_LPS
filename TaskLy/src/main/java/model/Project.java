package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import static utils.DateFunctions.getCurrentDate;
import utils.Status;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<ProjectReport> reports;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Task> tasks;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<ProjectMessage> messages;
    @OneToMany(mappedBy="project", cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Person> persons;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    
    public Project(){
        this.tasks = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.title = "";
        this.description = "";
        this.status = Status.WAITING;
        this.createdAt = getCurrentDate();
    }
    
    public Project(String title, String description){
        this.tasks = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.title = title;
        this.description = description;
        this.status = Status.WAITING;
        this.createdAt = getCurrentDate();
    }
    
    public boolean equalsTo(Project project){
        if(project == null){
            return false;
        }
        return this.id == project.getId();
    }
    
    public boolean hasPerson(Person person){
        for(Person ps: this.persons){
            if(ps.getId() == person.getId()){
                return true;
            }
        }
        return false;
    }
    
    public void addPerson(Person person){
        if(!this.persons.contains(person)){
            this.persons.add(person);
            person.setProject(this);
        }
    }
    
    public void addMessage(ProjectMessage message){
        if(!this.messages.contains(message)){
            this.messages.add(message);
            message.setProject(this);
        }
    }
    
    public void addReport(ProjectReport report){
        if(!this.reports.contains(report)){
            this.reports.add(report);
        }
    }
    
    public void addPersons(List<Person> persons){
        for(Person person: persons){
            this.addPerson(person);
        } 
    }
    
    public void clearPersons(){
        this.persons = new ArrayList<>();
    }
}
