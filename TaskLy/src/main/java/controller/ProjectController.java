/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProjectDAO;
import java.util.List;
import model.Project;

/**
 * @author wekisley
 */

public class ProjectController {
    private ProjectDAO projectDAO;
    
    public ProjectController(){
        this.projectDAO = new ProjectDAO();
    }
    
    public Project createNewProject(String title, String description) throws Exception{
        Project project = new Project(title, description);
        try {
            this.projectDAO.save(project);
        } catch(Exception e) {
            throw new Exception("Não foi possível cria o projeto!", e);
        }
        return project;
    }
    
    public List<Project> getAllProjects() throws Exception{
        try {
            return this.projectDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!");
        }
    }
}