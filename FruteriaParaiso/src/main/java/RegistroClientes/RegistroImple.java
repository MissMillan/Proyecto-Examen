/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistroClientes;

import DatosRegistro.*;
import Dominio.Cliente;
import ExcepcionesRegistroCliente.ExcepCliente;
import ExcepcionesRegistroCliente.LecturaExcepCliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//creamos un objeto que accedera a la capa de datos 

/**
 *
 * @author milla
 */
public class RegistroImple implements IRegistro{
    private IDatosRegistro cliente;
    
    
    //creo el constructor
    
    public RegistroImple() {
        this.cliente = new DatosRegistroImple();

    }

    @Override
    //inicio el fichero de CLIENTES
    public void iniciarRegistro(String nombreArchivo) {
            
        try {
            this.cliente.crearEnFichero(nombreArchivo);
        } catch (ExcepCliente ex) {
            ex.printStackTrace(System.out);
        System.out.println("Error al Iniciar el Archivo de clientes");
        }
       
    }

    @Override
    public void agregarUsuario(Cliente usuario, String nombreArchivo) {
        try {
            if (this.cliente.existeFichero(nombreArchivo)) {
                this.cliente.escribirEnFichero(usuario, nombreArchivo, true);
                //   this.datos.escribir(new Peliculas(nombrePelicula), nombreCatalogo, true);
            } else {
                System.out.println("usuario no inicializado");
            }
        } catch (ExcepCliente ex) {
            System.out.println("Error al Agregar el usuario"); 
        }
    }

    @Override
    public void buscarUsuario(String buscarCliente, String nombreArchivo) {
    
        List<Cliente> ArrayClientes = new ArrayList<>();  
        
        try {
          
          ArrayClientes  = this.cliente.listar(nombreArchivo);
           

            for (int i = 0; i <  ArrayClientes.size(); i++) {
                if ( buscarCliente.equalsIgnoreCase(ArrayClientes.get(i).getNombre())) {
                    System.out.println("El cliente:  " +  ArrayClientes.get(i).getNombre() + " esta en la pocision: " + (i + 1));
                }

            }
        } catch (LecturaExcepCliente ex) {
            System.out.println("Error al buscar el cliente");
            ex.printStackTrace(System.out);
        }
       
    }

    @Override
    public void listarUsuario(String nombreArchivo) {
            List<Cliente> ArrayCliente = new ArrayList<>(); 
            
       try {
           ArrayCliente = this.cliente.listar(nombreArchivo);
              System.out.println("\tLISTADO DE CLIENTES");
                  for (int i = 0; i < ArrayCliente.size(); i++) {
                
              
                System.out.println(ArrayCliente.get(i).getCod()+ "-" +
                        ArrayCliente.get(i).getNombre() + " - " + 
                        ArrayCliente.get(i).getApellido1() + " - " +
                        ArrayCliente.get(i).getApellido2() + " - " +
                        ArrayCliente.get(i).getCorreo()+ " - "+
                        ArrayCliente.get(i).getTelefono());
                
               
            }
        } catch (LecturaExcepCliente ex) {
      System.out.println("Error al listar los clientes");
  //  ex.printStackTrace(System.out); 
        }
            
      
    }

    @Override
    public void borrarUsuario(String nombreCliente, String nombreArchivo) {
           try {
            this.cliente.borrarEnFichero(nombreArchivo, nombreCliente);
        } catch (LecturaExcepCliente ex) {
            System.out.println("Error al borrar cliente en la capa de negocio");
                ex.printStackTrace(System.out); 
        } catch (ExcepCliente ex) {
           System.out.println("Error al borrar cliente en la capa de negocio");
              ex.printStackTrace(System.out); 
        }
     
    }

    @Override
    public void listarAscendente_Descendente( String nombreArchivo) {
          //Creamos el arrayList
     
        
           List<Cliente> ArrayCliente = new ArrayList<>();  
        try {
            ArrayCliente =  this.cliente.listar(nombreArchivo);
            
           System.out.println("\n\nDESCENDENTE NOMBRE");
            
        Collections.sort(ArrayCliente, new Comparator<Cliente>() {

            @Override
            public int compare(Cliente p2, Cliente p1) {
                return p2.getNombre().compareToIgnoreCase(p1.getNombre());
            }
        });
        System.out.println(ArrayCliente);
        
       
          
           //*******************************************************************************************
           
            System.out.println("\n\nASCENDENTE NOMBRE");
            Collections.sort(ArrayCliente, new Comparator<Cliente>(){

           
                public int compare(Cliente p2,Cliente p1){
                    return new String(p1.getNombre()).compareTo(new String(p2.getNombre()));
                }
                
            });
                 System.out.println(ArrayCliente);

            
    }
          catch (LecturaExcepCliente ex) {
        
        }
    }

    @Override
    public void modificarUsuario(String dniCliente, String nombreArchivo,Cliente modificado) {
            List<Cliente> ArrayCliente = new ArrayList<>();  
       
        try {
            ArrayCliente  = this.cliente.listar(nombreArchivo);
            this.cliente.modificarUsuario(dniCliente, nombreArchivo, modificado);
            
            
            
        } catch (LecturaExcepCliente ex) {
            Logger.getLogger(RegistroImple.class.getName()).log(Level.SEVERE, null, ex);
        }
             

      
        
    }

   
    
}
