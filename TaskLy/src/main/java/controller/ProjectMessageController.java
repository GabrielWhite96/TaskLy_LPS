/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProjectMessageDAO;
import model.AppStateSingleton;
import model.Project;
import model.ProjectMessage;

/**
 *
 * @author wekisley
 */
public class ProjectMessageController {
    private ProjectMessageDAO projectMessageDAO;
    private AppStateSingleton appState;
    
    public ProjectMessageController(){
        this.projectMessageDAO = new ProjectMessageDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createMessage(Project project, String message) throws Exception{
        ProjectMessage projectMessage = new ProjectMessage(message, project, this.appState.getUser());
        try {
            this.projectMessageDAO.save(projectMessage);
        } catch (Exception e) {
            throw new Exception("Não foi possível enviar o relatório!", e);
        }
    }
}
