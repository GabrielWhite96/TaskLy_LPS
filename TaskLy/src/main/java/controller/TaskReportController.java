/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProjectReportDAO;
import java.util.List;
import model.AppStateSingleton;
import model.Person;
import model.Project;
import model.ProjectReport;

/**
 *
 * @author wekisley
 */
public class TaskReportController {
    private AppStateSingleton appState;
    private ProjectReportDAO projectReportDAO;
    
    public TaskReportController(){
        this.projectReportDAO = new ProjectReportDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createReport(Project project, String title, String description) throws Exception {
        Person user = this.appState.getUser();
        ProjectReport report = new ProjectReport(project, user, title, description);
        try {
            this.projectReportDAO.save(report);
        } catch (Exception e) {
            throw new Exception("Não foi possível enviar o relatório!", e);
        }
    }
    
    public List<ProjectReport> getAllProjectReports() throws Exception{
        try {
            return this.projectReportDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível obter os relatórios!", e);
        }
    }
}
