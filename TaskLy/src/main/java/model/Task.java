package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;
    @OneToMany(mappedBy="task", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<TaskReport> reports;
    @OneToMany(mappedBy="task", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<TaskMessage> messages;
    @OneToMany(mappedBy="task", cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private List<Person> persons;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    
    public Task(){}
    
    public Task(String title, String description, Project project){
        this.project = project;
        this.reports = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.title = title;
        this.description = description;
        this.status = "Aguardando";
        this.createdAt = getCurrentDate();
    }
    
    public void addPerson(Person person){
        if(!this.persons.contains(person)){
            this.persons.add(person);
            person.setTask(this);
        }
    }
    
    public void addPerson(List<Person> persons){
        for(Person person: persons){
            this.addPerson(person);
        }
    }
    
    public boolean equalsTo(Task task){
        if(task == null){
            return false;
        }
        return this.id == task.getId();
    }
    
    public void clearPersons(){
        this.persons = new ArrayList<>();
    }
}
