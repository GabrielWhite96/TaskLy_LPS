/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProjectDAO;
import java.util.List;
import model.Person;
import model.Project;

/**
 * @author wekisley
 */

public class ProjectController {
    private ProjectDAO projectDAO;
    private PersonController personController;
    
    public ProjectController(){
        this.projectDAO = new ProjectDAO();
        this.personController = new PersonController();
    }
    
    public Project createNewProject(String title, String description) throws Exception {
        Project project = new Project(title, description);
        try {
            this.projectDAO.save(project);
        } catch(Exception e) {
            throw new Exception("Não foi possível criar o projeto!", e);
        }
        return project;
    }
    
    public Project createNewProject(String title, String description, List<Person> persons) throws Exception {
        Project project = new Project(title, description);
        project.addPersons(persons);
        try {
            this.projectDAO.update(project);
        } catch(Exception e) {
            throw new Exception("Não foi possível criar o projeto!", e);
        }
        return project;
    }
    
    public void updateProject(Project project) throws Exception {
        try {
            this.projectDAO.update(project);
        } catch(Exception e) {
            throw new Exception("Não foi possível atualizar o projeto!", e);
        }
    }
    
    public List<Project> getAllProjects() throws Exception{
        try {
            return this.projectDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!");
        }
    }
}