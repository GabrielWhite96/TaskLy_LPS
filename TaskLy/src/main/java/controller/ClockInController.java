/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClockInDAO;
import java.util.List;
import model.AppStateSingleton;
import model.ClockIn;
import model.Person;

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
    
    public List<ClockIn> getClockInOf(Person person) throws Exception{
        List<ClockIn> clockIns;
        try {
            clockIns = this.clockInDAO.getClockInOfPerson(person);
        }catch (Exception e){
            throw new Exception("Erro ao obter horarios de pessoa", e);
        }
        return clockIns;
    }
}
