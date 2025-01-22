/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Getter;
import utils.ClockInDescriptions;

/**
 *
 * @author wekisley
 */
public class ClockInWorkSingleton {
    private static ClockInWorkSingleton instance = null;
    @Getter
    private ClockIn clockIn = null;
    @Getter
    private ClockIn clockInRelax = null;
    private boolean working = false;
    private boolean relaxing = false;

    private ClockInWorkSingleton() {}
    
    public static ClockInWorkSingleton getInstance(){
        if(instance == null){
            instance = new ClockInWorkSingleton();
        }
        return instance;
    }
    
    public String getClockInStart(){
        return this.clockIn.getStartHour();
    }
    
    public String getClockInEnd(){
        return this.clockIn.getEndHour();
    }
    
    public String getClockInStartPause(){
        return this.clockInRelax.getStartHour();
    }
    
    public String getClockInEndPause(){
        return this.clockInRelax.getEndHour();
    }
    
    public void start() {
        if(!this.working && !this.relaxing){
            this.working = true;
            this.clockIn = new ClockIn(ClockInDescriptions.WORKING);
            this.clockIn.start();
        }
    }
    
    public void stop() {
        if(this.clockIn != null){
            this.working = false;
            this.clockIn.stop();
        }
    }
    
    public void resetTurn(){
        this.clockIn = null;
    }
    
    public void startPause(){
        if(!this.relaxing){
            this.relaxing = true;
            this.clockInRelax = new ClockIn(ClockInDescriptions.RELAXING);
            this.clockInRelax.start();
        }
    }
    
    public void stopPause(){
        if(this.clockInRelax != null){
            this.relaxing = false;
            this.clockInRelax.stop();
        }
    }
    
    public void resetPause(){
        this.clockInRelax = null;
    }
}
