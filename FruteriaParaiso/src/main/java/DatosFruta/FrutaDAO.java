/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosFruta;

import static DatosFruta.Conexion.close;
import static DatosFruta.Conexion.getConnection;
import Dominio.ClasificacionFruta;
import Dominio.Fruta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milla
 */
public class FrutaDAO {
    


/**
 *
 * @author milla
 */


     //*******DECLARO LAS VARIABLES CON LAS SENTENCIAS
     private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos "
            + "  (idProducto,nombre_Producto,cantidad,precio,fecha_compra, tipo_producto) VALUES "
            + "  ( ?, ?, ?, ? ,?, ?) ";
    //el id, no se pone , por que se lo pondre en la condicion
    //el metodo update, va a recibir un objeto que quiere actualizar
    private static final String SQL_UPDATE = "UPDATE productos SET "
            + "nombre_Producto = ?, "
            + "cantidad= ?  "
            + "precio = ?, "
            + "fecha_compra = ?, "
            + "tipo_producto = ?,"
            + "WHERE idProducto = ?";
    private static final String SQL_DELETE = " DELETE FROM productos WHERE idProducto = ?"; 
    
    //*******************Creamos el constructor
  ClasificacionFruta tipo;
    public FrutaDAO() {
    }
    
    //*****************Creamos los metodos
      public List<Fruta> seleccionar() throws SQLException {

            //esto conectara a la base de datos
        Connection conn = null;
        //esto me permititra tener varias consultas , con diferentes parametros
        PreparedStatement stmt = null;
        //esta variable contendra el resultado de la consulta
        ResultSet rs = null;

               //DEBO CREAR EL ARRAY LIST
        List<Fruta> arrayProductos = new ArrayList<>();
        //leer de la tabla para transformarlos en objeto y pasarselo a la capa negocio
        try {
     
            //es un metodo que hemos conseguido de la conexion
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                //leo toda la informacion, con los get de los tipos
                
                int id_producto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombre_Producto");
                 int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
               String fecha_compra = rs.getString("fecha_compra");
               ClasificacionFruta t = ClasificacionFruta.valueOf(rs.getString("tipo_producto"));
             
                arrayProductos.add(new Fruta(id_producto,nombreProducto,cantidad,precio,fecha_compra,t));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //esto es algo que siempre se ejecuta dentro del try
        }finally{
           close(rs) ;
           close(stmt);
        
             close(conn);  
           
           
        }
        return arrayProductos;
    }
        
//-----------------------------------------------------------------------------        
      
    public int insertar(Fruta product) throws SQLException{
        
        Connection conn  = null;
        PreparedStatement stmt = null;
          
        //esto nos va a devolver los registros
        int registros =0;
        
        try{
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
                    //el 1 es por que le paso la primera pocision del interrogante
            stmt.setInt(1,product.getCodigo());
            stmt.setString(2,product.getNombre());
            stmt.setInt(3, product.getCantidad());
            stmt.setDouble(4,product.getPrecio());
            stmt.setString(5,product.getFecha());
           // stmt.setString(6,(product.getTipo()));
           stmt.setString(6,product.getTipo().toString());
      //    preparedStatement.setString(1, MY_ENUM.name());
      
            //para insertar es esto, por que va a modificar el estado de la base de datos
            registros = stmt.executeUpdate(SQL_INSERT);
            
            
//        }catch(SQLException ex){
//            ex.printStackTrace(System.out);
        }finally{
            close(stmt);
           
             close(conn);  
          
          
        }
        return registros;
    }    

//-----------------------------------------------------------------------------------
    public int actualizar(Fruta product) throws SQLException{
    
        Connection conn  = null;
        PreparedStatement stmt = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            //ahora hay que pasarle valores a los interrogantes
            
               
            stmt.setString(1,product.getNombre());
            stmt.setInt(2, product.getCantidad());
            stmt.setDouble(3,product.getPrecio());
            stmt.setString(4,product.getFecha());
         //   stmt.setString(5, tipo.name());
            stmt.setInt(6,product.getCodigo());
        
            
            registros = stmt.executeUpdate();
//        } catch (Exception ex) {
//            ex.printStackTrace(System.out);
        }finally{//estos metodos vienen de conexion
            close(stmt);
        
             close(conn);  
           
        }
        
       return registros; 
    }       
     //------------------------------------------------------------------------
          public int borrar (Fruta product) throws SQLException{
         
        Connection conn  = null;
        PreparedStatement stmt = null;
          
        int registros =0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            //ahora hay que pasarle valores a los interrogantes
             stmt.setInt(1,product.getCodigo());
         
          
            
            registros = stmt.executeUpdate();
//        } catch (Exception ex) {
//            ex.printStackTrace(System.out);
        }finally{//estos metodos vienen de conexion
           
            close(stmt);
             close(conn);  

        }
        
       return registros; 
           
         
     }
}


