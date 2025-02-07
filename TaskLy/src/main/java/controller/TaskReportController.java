/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TaskReportDAO;
import java.util.List;
import model.AppStateSingleton;
import model.Person;
import model.Task;
import model.TaskReport;
import utils.Roles;

/**
 *
 * @author wekisley
 */
public class TaskReportController {
    private AppStateSingleton appState;
    private TaskReportDAO taskReportDAO;
    
    public TaskReportController(){
        this.taskReportDAO = new TaskReportDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createReport(Task task, String title, String description) throws Exception {
        Person user = this.appState.getUser();
        if(this.appState.userIs(Roles.ADMIN) || user.getTask().getId() == task.getId()){
            TaskReport report = new TaskReport(task, user, title, description);
            try {
                this.taskReportDAO.save(report);
            } catch (Exception e) {
                throw new Exception("Não foi possível enviar o relatório!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public List<TaskReport> getAllTaskReports() throws Exception{
        try {
            if(this.appState.userIs(Roles.ADMIN)){
                return this.taskReportDAO.getAll();
            } else {
                return this.taskReportDAO.getTaskReportOfPerson(this.appState.getUser());
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível obter os relatórios!", e);
        }
    }
}
