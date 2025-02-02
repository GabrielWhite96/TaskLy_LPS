/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import controller.LoginController;
import controller.PersonController;
import controller.ProjectController;
import dao.ConnectionDB;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import model.Login;
import model.Person;
import view.CreateProjectView;
import view.LoginView;
import view.ProjectsMenuView;

/**
 *
 * @author wekisley
 */
public class Tests {
    
    public static void createPersonsWithLogin(int numberEmployees, int numberManagers) throws Exception{
        EntityManagerFactory factory = ConnectionDB.getFactory();
        PersonController personController = new PersonController();
        
        for(int i = 1; i <= numberEmployees; i++){
            Login login = new Login("emailtesteE" + i + "@gmail.com", "1234567");
            personController.createNewUser("Empregado" + i, login, "", "21q212", Roles.EMPLOYEE, "M");
        }
        
        for(int i = 1; i <= numberManagers; i++){
            Login login = new Login("emailtesteG" + i + "@gmail.com", "1234567");
            personController.createNewUser("Gerente" + i, login, "", "21q212", Roles.MANAGER, "M");
        }
        
        factory.close();
    }
    
    public static void createProjectWithPersons() throws Exception{
        EntityManagerFactory factory = ConnectionDB.getFactory();

        PersonController personController = new PersonController();
        List<Person> persons = personController.getAllPersons();
        ProjectController project2 = new ProjectController();
        project2.createNewProject("Comprar galinha", "Ir na feira compra pao", persons);
        
        factory.close();
    }
    
    public static void startInProjectsMenu(){
        ProjectsMenuView projectMenu = new ProjectsMenuView();
        projectMenu.setVisible(true);
    }
    
    public static void startInCreateProject(){
        CreateProjectView projectCreation = new CreateProjectView();
        projectCreation.setVisible(true);
    }
    
    public static void createDefaultAdmin() throws Exception{
        EntityManagerFactory factory = ConnectionDB.getFactory();
        LoginController loginController = new LoginController();
        PersonController personController = new PersonController();
        
        Login login = new Login("admin", "1234567");
        
        System.out.println("login1");
        
        if(!loginController.emailExist(login.getEmail())){
            System.out.println("login2");
            personController.createNewUser(Roles.ADMIN, login, "as", "21q212", Roles.ADMIN, "M");
        }
    }
    
    public static void startAplication() throws Exception {
        createDefaultAdmin();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}
