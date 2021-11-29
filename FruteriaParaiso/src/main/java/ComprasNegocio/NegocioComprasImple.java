/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComprasNegocio;

import DatosCompra.DatosCompra;
import DatosCompra.IDatosCompra;
import Dominio.Compras;
import ExcepcionesCompra.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milla
 */
public class NegocioComprasImple implements INegocioCompras{
    //creamos un objeto que accedera a la capa de datos 
    private IDatosCompra datosCompra;
    
    //--------------------------------------------
       //constructor
    public NegocioComprasImple() {
        this.datosCompra = new DatosCompra();
    }

    @Override
    public void agregarCompra(Compras compra, String nombreArchivo) {
             try {
            if (this.datosCompra.existeFichero(nombreArchivo)) {
                this.datosCompra.escribirEnFichero(compra, nombreArchivo, true);
                //   this.datos.escribir(new Peliculas(nombrePelicula), nombreCatalogo, true);
            } else {
                System.out.println("Compra no inicializado");
            }
        } catch (EscrituraComprasExcep ex) {
            System.out.println("Error al Agregar la compra");
            ex.printStackTrace(System.out);
        } catch (AccesoComprasExcep ex) {
            System.out.println("Error al Agregar la compra");
            ex.printStackTrace(System.out);
        }
    }
//--------------------------------------------------------------------------------------------
    @Override
    public void listarCompra(String nombreArchivo) {
              List<Compras> ArrayFrutas = new ArrayList<>();

         
        try {
          
            ArrayFrutas = this.datosCompra.listar(nombreArchivo);
                  System.out.println("\tLISTADO DE COMPRAS");
            for (int i = 0; i < ArrayFrutas.size(); i++) {
                
          
                System.out.println(ArrayFrutas.get(i).imprimir());
                
               
            }
        } catch (LecturaComprasExcep ex) {
    System.out.println("Error al listar la compra");
    ex.printStackTrace(System.out);
        }
    }
    //-----------------------------------------------------------------------------------

    @Override
    public void buscarCompra(int buscarCompra, String nombreArchivo) {

        List<Compras> ArrayFrutas = new ArrayList<>();
        try {
            ArrayFrutas = this.datosCompra.listar(nombreArchivo);
            int j = 1;

            for (int i = 0; i < ArrayFrutas.size(); i++) {
                if (ArrayFrutas.get(i).getIdCompra() == buscarCompra) {
                    System.out.println("La compra " + ArrayFrutas.get(i).getIdCompra() + " esta en la pocision: " + (i + 1));
                }

            }
        } catch (LecturaComprasExcep ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error al buscar el la compra");
        }
    }

    @Override
    public void iniciarTienda(String nombreArchivo) {
       try {
            this.datosCompra.crearEnFichero(nombreArchivo);
        } catch (AccesoComprasExcep ex) {
            System.out.println("Error al Iniciar el Archivo");
        }
    }

    @Override
    public void borrarCompra(int codCompra, String nombreArchivo) {
      
        try {
            this.datosCompra.borrarCompra(codCompra, nombreArchivo);
        } catch (LecturaComprasExcep ex) {
            System.out.println("Error al borrar la compra en la capa de negocio");
        }
    }

  

}
