/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    ProjectController projectController;
    
    public TaskController(){
        this.taskDAO = new TaskDAO();
        this.projectController = new ProjectController();
    }
    
    public void createTask(String title, String description, List<Person> persons, Project project) throws Exception{
        Task task = new Task(title, description, project);
        task.addPersons(persons);
        project.addTask(task);
        try {
            projectController.updateProject(project);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
}
