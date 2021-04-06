/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class DTOArticulo implements IDTO{
    
    private static final long serialVersionUID = 123456789L;
    
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private int categoriaId;

    private int comando;
    
    public DTOArticulo(){
        
    }
    
    public DTOArticulo(int pId, String nombre, double precio, int stock) {
        this.id = pId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String producto) {
        this.nombre = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getComando() {
        return comando;
    }

    public void setComando(int comando) {
        this.comando = comando;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public void mensajePrueba(){
        
        JOptionPane.showMessageDialog(null, "Solo prueba");
    }
    
    public void mensaje(){
         JOptionPane.showMessageDialog(null, "Nuevo inge");
    }

    @Override
    public String insertar() {
        return "INSERT INTO ARTICULOS (NOMBRE, PRECIO, STOCK) VALUES ("+nombre+",'"+precio+"','"+stock+")";
    }

    @Override
    public String eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consultarPorID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consultarTodos() {
        return "SELECT * FROM articulo";
    }
    
}
