/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskly;

import controller.LoginController;
import dao.ConnectionDB;
import dao.LoginDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import view.LoginView;

/**
 *
 * @author wekisley
 */
public class TaskLy {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = ConnectionDB.getFactory();
        
        LoginView loginScreen = new LoginView();
        loginScreen.setVisible(true);

//        LoginController loginController = new LoginController();
//        int id = loginController.getUserAccount("wekisleysouza.a@gmail.com", "kkjkjjj");
//        System.out.println("Id: " + id);
    }
}
