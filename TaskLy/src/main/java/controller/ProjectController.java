package controller;

import dao.ProjectDAO;
import java.util.List;
import model.Person;
import model.Project;
import model.ProjectMessage;

public class ProjectController {
    private ProjectDAO projectDAO;
    private PersonController personController;
    
    public ProjectController(){
        this.projectDAO = new ProjectDAO();
        this.personController = new PersonController();
    }
    
    public void sendMessage(Person person, Project project, String message) throws Exception{
//        ProjectMessage projectMessage = new ProjectMessage();
//        projectMessage.setPerson(person);
//        projectMessage.setProject(project);
//        project.addMessage(projectMessage);
//        try {
//            this.projectDAO.update(project);
//        } catch (Exception e) {
//            throw new Exception("Não foi possível enviar mensagem!", e);
//        }
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
    
    public void updateProject(Project project, List<Person> persons) throws Exception {
        try {
            personController.removePersonsOfProject(project);
            project.clearPersons();
            project.addPersons(persons);
            this.projectDAO.update(project);
        } catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public Project find(int id) throws Exception{
        try {
            return this.projectDAO.getById(id);
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o usuário!");
        }
    }
    
    public Project find(Project project) throws Exception{
        try {
            return this.projectDAO.getById(project.getId());
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o usuário!");
        }
    }
    
    public List<Project> getAllProjects() throws Exception {
        try {
            return this.projectDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!");
        }
    }
}