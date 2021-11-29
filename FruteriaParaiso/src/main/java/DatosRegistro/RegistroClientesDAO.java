/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosRegistro;

import static DatosFruta.Conexion.*;
import Dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author milla
 */
public class RegistroClientesDAO {
       //*******DECLARO LAS VARIABLES CON LAS SENTENCIAS
     private static final String SQL_SELECT = "SELECT * FROM clientes";
    private static final String SQL_INSERT = "INSERT INTO clientes  "
            + "( codCliente,nombre,primer_apellido,segundo_apellido, correo, telefono) VALUES "
            + " (?, ?, ?, ?,?,?)";
    //el id, no se pone , por que se lo pondre en la condicion
    //el metodo update, va a recibir un objeto que quiere actualizar
    private static final String SQL_UPDATE = "UPDATE clientes SET "
            + "nombre = ?, "
            + "primer_apellido = ? , "
            + "segundo_apellido = ?, "
            + "correo = ?, "
            + "telefono = ?"
            + "WHERE codCliente = ?";
    private static final String SQL_DELETE = " DELETE FROM clientes WHERE codCliente = ?"; 
    
    //*******************Creamos el constructor

    public RegistroClientesDAO() {
    }

    
    //*****************Creamos los metodos
      public List<Cliente> seleccionar() throws SQLException {

            //esto conectara a la base de datos
        Connection conn = null;
        //esto me permititra tener varias consultas , con diferentes parametros
        PreparedStatement stmt = null;
        //esta variable contendra el resultado de la consulta
        ResultSet rs = null;

               //DEBO CREAR EL ARRAY LIST
        List<Cliente> arrayCliente = new ArrayList<>();
        //leer de la tabla para transformarlos en objeto y pasarselo a la capa negocio
        try {
     
            //es un metodo que hemos conseguido de la conexion
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                //leo toda la informacion, con los get de los tipos
                
                String codigo = rs.getString("codCliente");
                String nombreCliente = rs.getString("nombre");
                String primer_apellido = rs.getString("primer_apellido");
                String segundo_apellido = rs.getString("segundo_apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
           
              
             
                arrayCliente.add(new Cliente(codigo,nombreCliente,primer_apellido,segundo_apellido,correo,telefono));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //esto es algo que siempre se ejecuta dentro del try
        }finally{
             close(rs) ;
             close(stmt); 
             close(conn);  
           
           
        }
        return arrayCliente;
    }
        
//-----------------------------------------------------------------------------        
      
    public int insertar(Cliente product) throws SQLException{
        
        Connection conn  = null;
        PreparedStatement stmt= null ;
        
        //esto nos va a devolver los registros
        int registros =0;
        
        try{
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
                     //el 1 es por que le paso la primera pocision del interrogante
            stmt.setString(1,product.getCod());
            stmt.setString(2,product.getNombre());
            stmt.setString(3, product.getApellido1());
            stmt.setString(4,product.getApellido2());
            stmt.setString(5,product.getCorreo());
            stmt.setString(6,product.getTelefono());
      //    preparedStatement.setString(1, MY_ENUM.name());
      
            //para insertar es esto, por que va a modificar el estado de la base de datos
            registros = stmt.executeUpdate();
       
            
//        }catch(SQLException ex){
//            ex.printStackTrace(System.out);
        }finally{
            close(stmt);
            close(conn);  
          
          
        }
        return registros;
    }    

//-----------------------------------------------------------------------------------
    public int actualizar(Cliente product) throws SQLException{
    
        Connection conn  = null;
        PreparedStatement stmt = null;
        int registros =0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            //ahora hay que pasarle valores a los interrogantes
            
               
            stmt.setString(1,product.getNombre());
            stmt.setString(2, product.getApellido1());
            stmt.setString(3,product.getApellido2());
            stmt.setString(4,product.getCorreo());
            stmt.setString(5,product.getTelefono());
            stmt.setString(6,product.getCod());
        
            
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
          public int borrar (Cliente product) throws SQLException{
         
        Connection conn  = null;
        PreparedStatement stmt = null;
          
        int registros =0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            //ahora hay que pasarle valores a los interrogantes
             stmt.setString(1,product.getCod());
         
          
            
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
