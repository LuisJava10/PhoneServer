/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Samuel
 */
public interface IDTO extends Serializable{
    
    public String insertar();

    public String eliminar();

    public String actualizar();

    public String consultar();

    public String consultarPorID();
    
    public String consultarTodos();
    
}
