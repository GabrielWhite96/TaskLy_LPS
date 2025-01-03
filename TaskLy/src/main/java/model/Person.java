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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
        name = "person_task", // Nome da tabela intermediária
        joinColumns = @JoinColumn(name = "person_id"), // FK para 'Person'
        inverseJoinColumns = @JoinColumn(name = "task_id") // FK para 'Task'
    )
    private List<Task> tasks;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<ProjectMessage> projectMessages;
    private String name;
    private String address;
    private String phoneNumber;
    private String jobTitle;
    private boolean gender;
    
    public Person(){}
    
    public Person(String name, Login login, String address, String phoneNumber, String jobTitle, String gender){
        this.name = name;
        this.login = login;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.gender = gender.toLowerCase().startsWith("m");
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
