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

/**
 *
 * @author wekisley
 */
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int userId;
    @Column(unique = true)
    private String email;
    private String password;
    
    public Login(){
        
    }
    
    public int validatePassword(String password){
        if(this.password.equals(password)){
            System.out.println(password + " = " + this.userId);
            return this.userId;
        }
        return 0;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
}
