package controller;

import dao.ProjectDAO;
import java.util.ArrayList;
import java.util.List;
import model.AppStateSingleton;
import model.Person;
import model.Project;
import utils.Roles;

/**
 * @author wekisley
 */

public class ProjectController {
    private ProjectDAO projectDAO;
    private PersonController personController;
    private AppStateSingleton appStateSingleton;
    
    public ProjectController(){
        this.projectDAO = new ProjectDAO();
        this.personController = new PersonController();
        this.appStateSingleton = AppStateSingleton.getInstance();
    }
    
    public Project createNewProject(String title, String description) throws Exception {
        if(this.appStateSingleton.userIs(Roles.ADMIN)){
            Project project = new Project(title, description);
            try {
                this.projectDAO.save(project);
            } catch(Exception e) {
                throw new Exception("Não foi possível criar o projeto!", e);
            }
            return project;
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public Project createNewProject(String title, String description, List<Person> persons) throws Exception {
        if(this.appStateSingleton.userIs(Roles.ADMIN)){
            Project project = new Project(title, description);
            project.addPersons(persons);
            try {
                this.projectDAO.update(project);
            } catch(Exception e) {
                throw new Exception("Não foi possível criar o projeto!", e);
            }
            return project;
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void updateProject(Project project) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                this.projectDAO.update(project);
            } catch(Exception e) {
                throw new Exception("Não foi possível atualizar o projeto!", e);
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void updateProject(Project project, List<Person> persons) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                personController.removePersonsOfProject(project);
                project.clearPersons();
                project.addPersons(persons);
                this.projectDAO.update(project);
            } catch(Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public Project find(int id) throws Exception{
        try {
            Project project = this.projectDAO.getById(id);
            if(!this.appStateSingleton.userIs(Roles.EMPLOYEE) || this.appStateSingleton.getUser().getProject().getId() == project.getId()){
                return project;
            } else {
                throw new Exception("Você não tem a permissão necessária!");
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o projeto!");
        }
    }
    
    public List<Project> getAllProjects() throws Exception {
        try {
            if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
                return this.projectDAO.getAll();
            } else if(this.appStateSingleton.getUser().getProject() != null){
                List<Project> projects = new ArrayList<>();
                projects.add(this.appStateSingleton.getUser().getProject());
                return projects;
            }
            return new ArrayList<Project>();
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar os projetos!");
        }
    }
}