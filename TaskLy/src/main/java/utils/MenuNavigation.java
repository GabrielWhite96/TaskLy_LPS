/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import view.ProjectsMenu;
import view.TasksMenu;

/**
 *
 * @author wekisley
 */
public class MenuNavigation {
    
    public static void goToProjectsMenu(javax.swing.JFrame jFrame){
        ProjectsMenu projectsMenu = new ProjectsMenu();
        projectsMenu.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToTasksMenu(javax.swing.JFrame jFrame){
        TasksMenu tasksMenu = new TasksMenu();
        tasksMenu.setVisible(true);
        jFrame.dispose();
    }
}
