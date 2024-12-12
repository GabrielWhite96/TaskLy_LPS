/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wekisley
 */
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy="login")
    private Person person;
    @Column(unique = true)
    @Setter
    private String email;
    @Setter
    private String password;
    
    public Login(){
        
    }
    
    public Person validatePassword(String password){
        if(this.password.equals(password)){
            return this.person;
        }
        return null;
    }
    
    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }
}
