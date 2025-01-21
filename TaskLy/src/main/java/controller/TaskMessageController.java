/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TaskMessageDAO;
import java.util.List;
import model.AppStateSingleton;
import model.Task;
import model.TaskMessage;

/**
 *
 * @author wekisley
 */
public class TaskMessageController {
    private TaskMessageDAO taskMessageDAO;
    private AppStateSingleton appState;
    
    public TaskMessageController(){
        this.taskMessageDAO = new TaskMessageDAO();
        this.appState = AppStateSingleton.getInstance();
    }
    
    public void createMessage(Task task, String message) throws Exception{
        TaskMessage taskMessage = new TaskMessage(message, task, this.appState.getUser());
        try {
            this.taskMessageDAO.save(taskMessage);
        } catch (Exception e) {
            throw new Exception("Não foi possível enviar a mensagenm!", e);
        }
    }
    
    public List<TaskMessage> getMessagesOf(Task task) throws Exception{
        List<TaskMessage> messages;
        try {
            messages = this.taskMessageDAO.getMessagesOfTask(task);
        } catch (Exception e){
            throw new Exception("Não foi possível carregar as mensagens!", e);
        }
        return messages;
    }
}
