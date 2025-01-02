/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wekisley
 */
public class AppStateSingleton {
    private static AppStateSingleton instance = null;
    private Person user = null;
    
    private AppStateSingleton(){}
    
    public static AppStateSingleton getInstance(){
        if(instance == null){
            instance = new AppStateSingleton();
        }
        return instance;
    }
    
    public void setUser(Person user){
        this.user = user;
    }
    
    public Person getUser(){
        return user;
    }
}
