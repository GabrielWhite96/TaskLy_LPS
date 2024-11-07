package dao.interfaces;

import model.Login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wekisley
 */
public interface LoginDAOInterface extends DAOInterface {
    public void save(Login login) throws Exception;
    public void update(Login login) throws Exception;
    public Login getByEmail(String email) throws Exception;
}
