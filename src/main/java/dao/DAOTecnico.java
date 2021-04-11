/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DTOServicioTecnico;
import dto.IDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.ListaDoblementeEnlazadaGenerica;
import modelos.ObjetoGenerico;

/**
 *
 * @author SAMUEL
 */
public class DAOTecnico implements IDAO {

    private Conexion conexion;

    public DAOTecnico() {
        conexion = new Conexion();
    }

    @Override
    public void insertar(IDTO idto) {
        //DTOArticulo articulo = (DTOArticulo) idto;
        DTOServicioTecnico servicios = (DTOServicioTecnico) idto;

        try {
            final String SQL = "INSERT INTO servicios_prestados VALUES (DEFAULT, ?, ?, ?, ?, ?)";

            insertarCliente(servicios.getCliente());

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setString(1, servicios.getTipoServicio());
            pstmt.setString(2, servicios.getFechaIngreso());
            pstmt.setString(3, servicios.getFechaEgreso());
            pstmt.setDouble(4, servicios.getValor());
            pstmt.setInt(5, servicios.getCliente().getIdentifacion());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            final String SQL = "DELETE FROM servicios_prestados WHERE id = ? LIMIT 1";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(IDTO idto) {
        DTOServicioTecnico servicios = (DTOServicioTecnico) idto;

        try {
            final String SQL = "UPDATE servicios_prestados SET idclientes = ?, tipo_de_servicios = ?, fecha_ingreso = ?, fecha_egreso = ?, valor = ? WHERE id = ?";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, servicios.getIdCliente());
            pstmt.setString(2, servicios.getTipoServicio());
            //pstmt.setDate(3, servicios.getFechaIngreso());
            //pstmt.setDate(4, servicios.getFechaEgreso());
            pstmt.setDouble(5, servicios.getValor());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object consultar(int id) {
        try {
            final String SQL = "SELECT * FROM servicios_prestados WHERE id = ?";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rst = pstmt.executeQuery();

            if (rst.next()) { //Empezar a leer el registro que tiene la base de datos. Si es true, es porque hay datos y se cumple.
                DTOServicioTecnico servcio = new DTOServicioTecnico();
                servcio.setIdCliente(id);
                servcio.setTipoServicio(rst.getString("tipo_de_servicios"));
                servcio.setFechaIngreso(rst.getString("fecha_ingreso"));
                servcio.setFechaEgreso(rst.getString("fecha_egreso"));
                servcio.setValor(rst.getInt("valor"));

                DTOCliente cliente = buscarClientePorId(rst.getInt("idclientes"));
                servcio.setCliente(cliente);

                return servcio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object consultarTodos() {

        ListaDoblementeEnlazadaGenerica lista = new ListaDoblementeEnlazadaGenerica();

        try {
            final String SQL = "SELECT * FROM servicios_prestados";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);

            ResultSet rst = pstmt.executeQuery(); //Este metodo que hace? va a recorrer cada articulo
            DTOServicioTecnico tecnico;

            while (rst.next()) {
                tecnico = new DTOServicioTecnico();
                tecnico.setIdCliente(rst.getInt("idclientes"));
                tecnico.setTipoServicio(rst.getString("tipo_de_servicios"));
                tecnico.setFechaIngreso(rst.getString("fecha_ingreso"));
                tecnico.setFechaEgreso(rst.getString("fecha_egreso"));
                tecnico.setValor(rst.getDouble("valor"));

                lista.adicionarFinalLista(new ObjetoGenerico<DTOServicioTecnico>(tecnico));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Cada uno de estos metodos hara una instruccion SQL.
    private DTOCliente buscarClientePorId(int id) {
        try {
            final String SQL = "SELECT * FROM clientes WHERE idclientes = ?";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rst = pstmt.executeQuery();

            if (rst.next()) { //Empezar a leer el registro que tiene la base de datos. Si es true, es porque hay datos y se cumple.
                DTOCliente cliente = new DTOCliente();
                cliente.setIdentifacion(rst.getInt("idclientes"));
                cliente.setNombre(rst.getString("nombre"));
                cliente.setEmail(rst.getString("email"));
                cliente.setTelefono(rst.getString("numero_telefonico"));

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void insertarCliente(DTOCliente cliente) {
        try {
            final String SQL = "INSERT INTO clientes VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, cliente.getIdentifacion());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

