/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author wekisley
 */
public interface DAOInterface<T> {
    public T getById(int id) throws Exception;
    public void delete(int id) throws Exception;
    public List<T> getAll() throws Exception;
    public void save(T login) throws Exception;
    public void update(T login) throws Exception;
}
