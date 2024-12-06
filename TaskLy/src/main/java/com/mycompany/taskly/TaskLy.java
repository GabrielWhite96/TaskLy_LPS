/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskly;

import controller.LoginController;
import controller.PersonController;
import controller.ProjectController;
import dao.ConnectionDB;
import dao.LoginDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import view.CreatePerson;
import view.LoginView;
import view.ProjectsMenu;
import view.CreateProject;
import view.LoginView;
import view.CreateProject;
import view.LoginView;
import view.TesteTable;
import view.ProjectsMenu;

/**
 *
 * @author wekisley
 */
public class TaskLy {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = ConnectionDB.getFactory();
        
//        ProjectController project1 = new ProjectController();;;
//        project1.createNewProject("Comprar 2", "Ir na feira compra pao");
//        
//        ProjectController project2 = new ProjectController();
//        project2.createNewProject("Comprar galinha", "Ir na feira compra pao");

        ProjectsMenu menu = new ProjectsMenu();
        menu.setVisible(true);
        
//        CreateProject project = new CreateProject();
//        project.setVisible(true);
//        TesteTable table = new TesteTable();
//        table.setVisible(true);
//        CreatePerson person = new CreatePerson();
//        person.setVisible(true);
//        CreateProject project = new CreateProject();
//        project.setVisible(true);
//        LoginView loginView = new LoginView();
//        loginView.setVisible(true);
//        LoginController loginController = new LoginController();
//        Login login = loginController.createLogin("555", "555");
//        PersonController personController = new PersonController();
//        personController.createNewUser("teste2", login, "", "21q212", "esqte", "M");
//        factory.close();
    }
}
