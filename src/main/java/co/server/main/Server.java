/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.server.main;

import dto.DTOArticulo;
import dto.DTOServicioTecnico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import modelos.ListaDoblementeEnlazadaGenerica;
import utils.ComandosSql;

/**
 *
 * @author Samuel
 */
public class Server {
        
     public static void main(String[] args) {
        //ServerSocket socketServer = null;

        while (true) {
            try {
                ServerSocket socketServer = new ServerSocket(7070);
                System.out.println("El servidor está escuchando...");
                Socket socket = socketServer.accept();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                  
                DTOArticulo articulo = null;
                DTOServicioTecnico servicio = null;
                Object object = objectInputStream.readObject();
                
                int comando = 0;
                
                if (object instanceof DTOArticulo) {
                    articulo = (DTOArticulo) object;
                    comando = articulo.getComando();
                } else {
                    servicio = (DTOServicioTecnico) object;
                    comando = servicio.getComando();
                }
                //System.out.println("SVR: " + comando);
                switch (comando) {

                    case ComandosSql.ARTICULO_CREAR:
                        new DAOArticulo().insertar(articulo);
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;

                    case ComandosSql.ARTICULO_CONSULTAR_POR_ID:
                        object = new DAOArticulo().consultar(articulo.getId());
                        objectOutputStream.writeObject(object); 
                        break;

                    case ComandosSql.ARTICULO_CONSULTAR_TODOS:
                        
                        
                        object = new DAOArticulo().consultarTodos();
                        
                        objectOutputStream.writeObject(object);
                        break;
                    
                    case ComandosSql.ARTICULO_ACTUALIZAR_POR_ID:
                        new DAOArticulo().actualizar(articulo);
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;
                    
                    case ComandosSql.ARTICULO_ELIMINAR_POR_ID:
                        new DAOArticulo().eliminar(articulo.getId());
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;
                    //Aqui
                    case ComandosSql.CATEGORIA_CONSULTAR_TODAS:
                        object = new DAOArticulo().consultarCategorias();
                        
                        objectOutputStream.writeObject(object);
                        break;
                        
                   //Inicio construccion switch servicios.
                     case ComandosSql.SERVICIO_CREAR:
                        new DAOTecnico().insertar(servicio);
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;

                    case ComandosSql.SERVICIO_CONSULTAR_POR_ID:
                        object = new DAOTecnico().consultar(articulo.getId());
                        
                        objectOutputStream.writeObject(object);

                        break;

                    case ComandosSql.SERVICIO_CONSULTAR_TODOS:
                        //AQUI
                        
                        object = new DAOTecnico().consultarTodos();
                        
                        objectOutputStream.writeObject(object);
                        break;
                        
                    
                    case ComandosSql.SERVICIO_ACTUALIZAR_POR_ID:
                        new DAOTecnico().actualizar(articulo);
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;
                    
                    case ComandosSql.SERVICIO_ELIMINAR_POR_ID:
                        new DAOArticulo().eliminar(articulo.getId());
                        objectOutputStream.writeObject(new ListaDoblementeEnlazadaGenerica());
                        break;
 
                }

                socketServer.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
         
        }
    }
} 