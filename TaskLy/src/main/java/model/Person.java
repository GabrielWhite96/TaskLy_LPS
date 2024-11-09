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
    @OneToOne(mappedBy="person")
    private Login login;
    @ManyToOne
    private Project project;
    @ManyToMany
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
}
