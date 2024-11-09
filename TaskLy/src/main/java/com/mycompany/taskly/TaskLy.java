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
import view.LoginView;

/**
 *
 * @author wekisley
 */
public class TaskLy {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = ConnectionDB.getFactory();
        
        CreatePerson person = new CreatePerson();
        person.setVisible(true);

//        LoginController loginController = new LoginController();
//        Login login = loginController.createLogin("aaa", "1234567");
//        PersonController personController = new PersonController();
//        personController.createNewUser("Wekisley", login, "", "21212", "este", "M");
//        int id = loginController.getUserAccount("wekisleysouza.a@gmail.com", "kkjkjjj");
//        System.out.println("Id: " + id);
//        factory.close();
    }
}
