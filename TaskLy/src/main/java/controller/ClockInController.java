/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClockInDAO;
import model.AppStateSingleton;
import model.ClockIn;

/**
 *
 * @author wekisley
 */
public class ClockInController {
    private ClockInDAO clockInDAO;
    private AppStateSingleton appState;
    
    public ClockInController(){
        this.clockInDAO = new ClockInDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createClockIn(ClockIn clockIn) throws Exception{
        clockIn.setPerson(this.appState.getUser());
        try {
            this.clockInDAO.save(clockIn);
        } catch (Exception e){
            throw new Exception("Não foi possível criar o ponto!");
        }
    }
}
