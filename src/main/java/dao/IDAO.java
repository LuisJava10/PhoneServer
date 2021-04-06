/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.IDTO;

/**
 *
 * @author Samuel
 */
public interface IDAO {

    public void insertar(IDTO idto);

    public void eliminar(int id);

    public void actualizar(IDTO idto);

    public Object consultar(int id);

    public Object consultarTodos();
}
    
    

