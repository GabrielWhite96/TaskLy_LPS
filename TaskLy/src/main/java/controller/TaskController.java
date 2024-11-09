/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TaskDAO;
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
    
    public void createTask(String title, String description) throws Exception{
        Task task = new Task(title, description);
        try {
            task = taskDAO.save(task);
        } catch (Exception e){
            throw new Exception("Não foi possível criar a tarefa!");
        }
    }
}
