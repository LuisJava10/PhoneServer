/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.server.main;

import java.net.ServerSocket;

/**
 *
 * @author Samuel
 */
public class Server {
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
} 