/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 *
 * @author wekisley
 */
@Entity
@Data
public class ProjectMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;
    private String content;
    
    public ProjectMessage(){}
    
    public ProjectMessage(String message, Project project, Person person){
        this.content = message;
        this.project = project;
        this.person = person;
    }
}
