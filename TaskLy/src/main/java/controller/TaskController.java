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
    
    public TaskController(){
        this.taskDAO = new TaskDAO();
    }
    
    public void createTask(String title, String description, Project project) throws Exception{
        Task task = new Task(title, description, project);
        try {
            taskDAO.save(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
    
    public void createTask(String title, String description, Project project, List<Person> persons) throws Exception{
        Task task = new Task(title, description, project);
        task.addPerson(persons);
        try {
            taskDAO.update(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
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
