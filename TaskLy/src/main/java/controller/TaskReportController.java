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
        TaskReport report = new TaskReport(task, user, title, description);
        try {
            this.taskReportDAO.save(report);
        } catch (Exception e) {
            throw new Exception("Não foi possível enviar o relatório!", e);
        }
    }
    
    public List<TaskReport> getAllTaskReports() throws Exception{
        try {
            return this.taskReportDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível obter os relatórios!", e);
        }
    }
}
