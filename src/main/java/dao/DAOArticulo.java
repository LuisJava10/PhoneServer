package dao;

import dto.DTOArticulo;
import dto.DTOCategoria;
import dto.IDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.ListaDoblementeEnlazadaGenerica;
import modelos.ObjetoGenerico;

/**
 *
 * @author jorge
 */
public class DAOArticulo implements IDAO {
    
    private Conexion conexion;

    public DAOArticulo() throws SQLException {
        conexion = new Conexion();
    }    

    //Aqui me conecto a la base de datos, ya que ahi, ya tengo los datos, y la instruccion para ejecutarla en la base de datos. 
    @Override
    public void insertar(IDTO idto) {
        DTOArticulo articulo = (DTOArticulo) idto;
        
        try {
            final String SQL = "INSERT INTO articulos VALUES (DEFAULT, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setString(1, articulo.getNombre());
            pstmt.setDouble(2, articulo.getPrecio());
            pstmt.setInt(3, articulo.getStock());
            pstmt.setInt(4, articulo.getCategoriaId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            final String SQL = "DELETE FROM articulo WHERE id = ? LIMIT 1";
            
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(IDTO idto) {
        DTOArticulo articulo = (DTOArticulo) idto;
        
        try {
            final String SQL = "UPDATE articulo SET nombre = ?, precio = ?, stock = ?, categoria_id = ? WHERE id = ?";
            
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setString(1, articulo.getNombre());
            pstmt.setDouble(2, articulo.getPrecio());
            pstmt.setInt(3, articulo.getStock());
            pstmt.setInt(4, articulo.getCategoriaId());
            pstmt.setInt(5, articulo.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object consultar(int id) {
        try {
            final String SQL = "SELECT * FROM articulo WHERE id = ";
            
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            pstmt.setInt(1, id);
            
            ResultSet rst = pstmt.executeQuery();
            
            if (rst.next()) { //Empezar a leer el registro que tiene la base de datos. Si es true, es porque hay datos y se cumple.
                DTOArticulo articulo = new DTOArticulo();
                articulo.setId(id);
                articulo.setNombre(rst.getString("nombre"));
                articulo.setPrecio(rst.getDouble("precio"));
                articulo.setStock(rst.getInt("stock"));
                articulo.setCategoriaId(rst.getInt("categoria_id"));
                
                return articulo;
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
            final String SQL = "SELECT * FROM articulos";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            
            ResultSet rst = pstmt.executeQuery(); // Va a recorrer cada articulo
            DTOArticulo articulo;
            
            while (rst.next()) {
                articulo = new DTOArticulo();
                articulo.setId(rst.getInt("idarticulos"));
                articulo.setNombre(rst.getString("nombre"));
                articulo.setPrecio(rst.getDouble("precio"));
                articulo.setStock(rst.getInt("stock"));
                articulo.setCategoriaId(rst.getInt("categoria_id"));
                
                lista.adicionarFinalLista(new ObjetoGenerico<DTOArticulo>(articulo));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    public Object consultarCategorias() {
        ListaDoblementeEnlazadaGenerica lista = new ListaDoblementeEnlazadaGenerica();
        
        try {
            final String SQL = "SELECT * FROM categorias";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(SQL);
            
            ResultSet rst = pstmt.executeQuery(); // Va a recorrer cada articulo
            DTOCategoria categoria;
            
            while (rst.next()) {
                categoria = new DTOCategoria();
                categoria.setId(rst.getInt("id"));
                categoria.setNombre(rst.getString("nombre"));
                
                lista.adicionarFinalLista(new ObjetoGenerico<DTOCategoria>(categoria));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }
}
