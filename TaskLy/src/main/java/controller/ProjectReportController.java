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
import utils.Roles;

/**
 *
 * @author wekisley
 */
public class ProjectReportController {
    private AppStateSingleton appState;
    private ProjectReportDAO projectReportDAO;
    
    public ProjectReportController(){
        this.projectReportDAO = new ProjectReportDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createReport(Project project, String title, String description) throws Exception {
        Person user = this.appState.getUser();
        if(this.appState.userIs(Roles.ADMIN) || user.getProject().getId() == project.getId()){
            ProjectReport report = new ProjectReport(project, user, title, description);
            try {
                this.projectReportDAO.save(report);
            } catch (Exception e) {
                throw new Exception("Não foi possível enviar o relatório!", e);
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public List<ProjectReport> getAllProjectReports() throws Exception{
        try {
            if(this.appState.userIs(Roles.ADMIN)){
                return this.projectReportDAO.getAll();
            } else {
                return this.projectReportDAO.getProjectReportOfPerson(this.appState.getUser());
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível obter os relatórios!", e);
        }
    }
}
