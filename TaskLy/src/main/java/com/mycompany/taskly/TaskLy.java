/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskly;

import controller.LoginController;
import controller.PersonController;
import dao.ConnectionDB;
import dao.LoginDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import view.CreatePerson;
import view.CreateProject;
import view.LoginView;
import view.TesteTable;

/**
 *
 * @author wekisley
 */
public class TaskLy {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = ConnectionDB.getFactory();
        
//        CreateProject project = new CreateProject();
//        project.setVisible(true);
        TesteTable table = new TesteTable();
        table.setVisible(true);

//        LoginController loginController = new LoginController();
//        Login login = loginController.createLogin("aaqaaaqqa", "123s4567");
//        PersonController personController = new PersonController();
//        personController.createNewUser("Wekisqqqley", login, "", "21q212", "esqte", "M");
//        int id = loginController.getUserAccount("wekisleysouza.a@gmail.com", "kkjkjjj");
//        System.out.println("Id: " + id);
        factory.close();
    }
}
