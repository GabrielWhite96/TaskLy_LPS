/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wekisley
 */
public class ClockInWorkSingleton {
    private static ClockInWorkSingleton instance = null;
    private ClockIn clockIn = null;
    private String status = "stopped";
    private AppStateSingleton appState = null;

    private ClockInWorkSingleton() {}
    
    public static ClockInWorkSingleton getInstance(){
        if(instance == null){
            instance = new ClockInWorkSingleton();
        }
        return instance;
    }
    
    private void setAtributes(){
        if(this.clockIn == null){
            this.appState = AppStateSingleton.getInstance();
            this.clockIn = new ClockIn(this.appState.getUser());
        }
    }
    
    public String getClockInStart(){
        return "xx:xx";
    }
    
    public String getClockInEnd(){
        return "";
    }
    
    public String getClockInStartPause(){
        return "";
    }
    
    public String getClockInEndPause(){
        return "";
    }
    
    public ClockIn getClockIn(){
        this.setAtributes();
        return this.clockIn;
    }
    
    public void start(){
        this.status = "started";
    }
    
    public void stop(){
        this.status = "stopped";
        
    }
    
    public void startPause(){
        this.status = "paused";
        
    }
    
    public void stopPause(){
        this.status = "paused";
        
    }
}
