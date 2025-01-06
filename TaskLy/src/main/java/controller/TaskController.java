package controller;

import dao.TaskDAO;
import java.util.List;
import model.Person;
import model.Project;
import model.Task;

/**
 *
 * @author wekisley
 */
public class TaskController {
    TaskDAO taskDAO;
    PersonController personController;
    
    public TaskController(){
        this.taskDAO = new TaskDAO();
        this.personController = new PersonController();
    }
    
    public void createTask(String title, String description, Project project) throws Exception{
        Task task = new Task(title, description, project);
        try {
            taskDAO.save(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
    
    public void createTask(String title, String description, Project project, List<Person> persons) throws Exception {
        Task task = new Task(title, description, project);
        task.addPerson(persons);
        try {
            taskDAO.update(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
    
    public void updateTask(Task task) throws Exception {
        try {
            taskDAO.update(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
    
    public void updateTask(Task task, List<Person> persons) throws Exception {
        try {
            personController.removePersonsOfTask(task);
            task.clearPersons();
            task.addPerson(persons);
            taskDAO.update(task);
        } catch (Exception e){
            throw new Exception("Não foi possível atualizar a tarefa!");
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
}
