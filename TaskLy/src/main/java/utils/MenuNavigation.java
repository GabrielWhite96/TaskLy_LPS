/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import view.ClockInView;
import view.PersonsMenuView;
import view.ProjectsMenuView;
import view.ReportProjectsMenuView;
import view.ReportTasksMenuView;
import view.TasksMenuView;

/**
 *
 * @author wekisley
 */
public class MenuNavigation {
    
    public static void goToClockIn(javax.swing.JFrame jFrame){
        ClockInView clockInView = new ClockInView();
        clockInView.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToProjectsMenu(javax.swing.JFrame jFrame){
        ProjectsMenuView projectsMenu = new ProjectsMenuView();
        projectsMenu.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToTasksMenu(javax.swing.JFrame jFrame){
        TasksMenuView tasksMenu = new TasksMenuView();
        tasksMenu.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToPersonsMenu(javax.swing.JFrame jFrame){
        PersonsMenuView tasksMenu = new PersonsMenuView();
        tasksMenu.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToReportProjectsMenu(javax.swing.JFrame jFrame){
        ReportProjectsMenuView reportProjectsMenu = new ReportProjectsMenuView();
        reportProjectsMenu.setVisible(true);
        jFrame.dispose();
    }
    
    public static void goToReportTasksMenu(javax.swing.JFrame jFrame){
        ReportTasksMenuView reportProjectsMenu = new ReportTasksMenuView();
        reportProjectsMenu.setVisible(true);
        jFrame.dispose();
    }
}
