/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
