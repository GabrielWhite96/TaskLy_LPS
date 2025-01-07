/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ClockInController;

/**
 *
 * @author wekisley
 */
public class ClockInWorkSingleton {
    private static ClockInWorkSingleton instance = null;
    private ClockIn clockIn = null;
    private ClockIn clockInRelax = null;
    private boolean working = false;
    private boolean relaxing = false;
    private AppStateSingleton appState = null;
    private ClockInController clockInController = null;

    private ClockInWorkSingleton() {}
    
    public static ClockInWorkSingleton getInstance(){
        if(instance == null){
            instance = new ClockInWorkSingleton();
        }
        return instance;
    }
    
    private void setAtributes(){
        if(this.clockInController == null){
            this.appState = AppStateSingleton.getInstance();
            this.clockInController = new ClockInController();
        }
    }
    
    public String getClockInStart(){
        this.setAtributes();
        return "XX:XX";
    }
    
    public String getClockInEnd(){
        this.setAtributes();
        return "XX:XX";
    }
    
    public String getClockInStartPause(){
        this.setAtributes();
        return "XX:XX";
    }
    
    public String getClockInEndPause(){
        this.setAtributes();
        return "XX:XX";
    }
    
    public void start() throws Exception{
        if(this.relaxing){
            this.stopPause();
        }
        this.setAtributes();
        if(!this.working){
            this.working = true;
            this.clockIn = new ClockIn(this.appState.getUser(), "Working");
            this.clockIn.start();
        }
    }
    
    public void stop() throws Exception {
        if(this.relaxing){
            this.stopPause();
        }
        if(this.clockIn != null){
            this.setAtributes();
            this.working = false;
            this.clockIn.stop();
            this.clockInController.createClockIn(this.clockIn);
            this.clockIn = null;
        }
    }
    
    public void startPause(){
        if(!this.relaxing){
            this.setAtributes();
            this.relaxing = true;
            this.clockInRelax = new ClockIn(this.appState.getUser(), "Relaxing");
            this.clockInRelax.start();
        }
    }
    
    public void stopPause() throws Exception{
        if(this.clockInRelax != null){
            this.setAtributes();
            this.relaxing = false;
            this.clockInRelax.stop();
            this.clockInController.createClockIn(this.clockInRelax);
            this.clockInRelax = null;
        }
    }
}
