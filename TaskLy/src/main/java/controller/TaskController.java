package controller;

import dao.TaskDAO;
import java.util.List;
import model.AppStateSingleton;
import model.Person;
import model.Project;
import model.Task;
import utils.Roles;

/**
 *
 * @author wekisley
 */
public class TaskController {
    private TaskDAO taskDAO;
    private PersonController personController;
    private AppStateSingleton appStateSingleton;
    
    public TaskController(){
        this.taskDAO = new TaskDAO();
        this.personController = new PersonController();
        this.appStateSingleton = AppStateSingleton.getInstance();
    }
    
    public void createTask(String title, String description, Project project) throws Exception{
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            Task task = new Task(title, description, project);
            try {
                taskDAO.save(task);
            } catch (Exception e){
                throw new Exception("Não foi possível criar a tarefa!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void createTask(String title, String description, Project project, List<Person> persons) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            Task task = new Task(title, description, project);
            task.addPerson(persons);
            try {
                taskDAO.update(task);
            } catch (Exception e){
                throw new Exception("Não foi possível criar a tarefa!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void updateTask(Task task) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                taskDAO.update(task);
            } catch (Exception e){
                throw new Exception("Não foi possível criar a tarefa!");
            } 
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void updateTask(Task task, List<Person> persons) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                personController.removePersonsOfTask(task);
                task.clearPersons();
                task.addPerson(persons);
                taskDAO.update(task);
            } catch (Exception e){
                throw new Exception("Não foi possível atualizar a tarefa!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public Task getTask(Task task) throws Exception{
        try {
            return this.taskDAO.getById(task.getId());
        } catch (Exception e) {
            throw new Exception("Não foi possível obter a tarefa!");
        }
    }
    
    public List<Task> getAllTasks() throws Exception{
        try {
            return this.taskDAO.getAll();
        } catch (Exception e) {
            throw new Exception("Não foi possível obter todas as tarefas!");
        }
    }
    
    public List<Task> getTasksOf(Project project) throws Exception{
        Person user = this.appStateSingleton.getUser();
        if(this.appStateSingleton.userIs(Roles.ADMIN) || user.getProject().getId() == project.getId()){
            try {
                return this.taskDAO.getTasksOfProject(project);
            } catch (Exception e) {
                throw new Exception("Não foi possível encontrar as tasks!");
            }
        } else {
            throw new Exception("Você não pertence ao projeto!");
        }
    }
}
