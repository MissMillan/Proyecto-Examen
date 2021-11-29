/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VentaFrutas;

import DatosFruta.DatosF;
import DatosFruta.IDatosF;
import Dominio.Fruta;
import ExcepcionesFruta.AccesoFrutasExcep;
import ExcepcionesFruta.EscrituraFrutasExcep;
import ExcepcionesFruta.LecturaFrutaExcep;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author milla
 */
public class OfertaFrutas implements IOfertaFrutas {

//creamos un objeto que accedera a la capa de datos 
    private IDatosF datosFruta;
   

    //--------------------------------------------------------------------------
    //constructor
    public OfertaFrutas() {
        this.datosFruta = new DatosF();
    }

  

    //----------------------------------------------------------------------------
    @Override
    public void agregarFruta(Fruta fruta, String nombreArchivo) {
        try {
            if (this.datosFruta.existeFichero(nombreArchivo)) {
                this.datosFruta.escribirEnFichero(fruta, nombreArchivo, true);
                //   this.datos.escribir(new Peliculas(nombrePelicula), nombreCatalogo, true);
            } else {
                System.out.println("Oferta no inicializado");
            }
        } catch (EscrituraFrutasExcep ex) {
            System.out.println("Error al Agregar la fruta");
            ex.printStackTrace(System.out);
        } catch (AccesoFrutasExcep ex) {
            System.out.println("Error al Agregar la fruta");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarFruta(String nombreArchivo) {

        List<Fruta> ArrayFrutas = new ArrayList<>();
         String fechaF = "";
        
         SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
         
        try {
          
            ArrayFrutas = this.datosFruta.listar(nombreArchivo);
                  System.out.println("\tLISTADO DE FRUTAS");
            for (int i = 0; i < ArrayFrutas.size(); i++) {
                
          
                System.out.println(ArrayFrutas.get(i).getCodigo()+ "-" + ArrayFrutas.get(i).getNombre() + " - " + ArrayFrutas.get(i).getCantidad()
                  + " - " + ArrayFrutas.get(i).getPrecio() + " - " + ArrayFrutas.get(i).getFecha()+ " - "+ ArrayFrutas.get(i).getTipo());
                
               
            }
        } catch (LecturaFrutaExcep ex) {
    System.out.println("Error al listar la fruta");
    ex.printStackTrace(System.out);
        }
//            ArrayFrutas.forEach(pelicula -> {
//            System.out.println("\n"+pelicula.getNombre());

    }

    //--------------------------------------------------------------------------
    @Override
    public void buscarFruta(String buscarF, String nombreArchivo) {

        List<Fruta> ArrayFrutas = new ArrayList<>();
        try {
            ArrayFrutas = this.datosFruta.listar(nombreArchivo);
            int j = 1;

            for (int i = 0; i < ArrayFrutas.size(); i++) {
                if (ArrayFrutas.get(i).getNombre().equalsIgnoreCase(buscarF)) {
                    System.out.println("La fruta " + ArrayFrutas.get(i).getNombre() + " esta en la pocision: " + (i + 1));
                }

            }
        } catch (LecturaFrutaExcep ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error al buscar el la Fruta");
        }
    }
    //--------------------------------------------------------------------------

    @Override
    public void iniciarTienda(String nombreArchivo) {

   
        try {
            this.datosFruta.crearEnFichero(nombreArchivo);
        } catch (AccesoFrutasExcep ex) {
            System.out.println("Error al Iniciar el Archivo");
        }
    }
    //--------------------------------------------------------------------------

    @Override
    public void calcularTotalprecio(String nombreArchivo) {
        
        List<Fruta> ArrayFrutas = new ArrayList<>();
        double mult = 0;
        double finalTotal= 0;
        try {
            ArrayFrutas = this.datosFruta.listar(nombreArchivo);
            System.out.println("\t SU RECIBO : ");
            System.out.println("\t==============");
            for (int i = 0; i < ArrayFrutas.size(); i++) {
                mult = ArrayFrutas.get(i).getCantidad() * ArrayFrutas.get(i).getPrecio();
                System.out.println("El total a pagar por : " + ArrayFrutas.get(i).getNombre() + " es: " + mult);
                finalTotal = finalTotal + mult;
            }
            System.out.println("Su factura final es : "+      finalTotal);

        } catch (LecturaFrutaExcep ex) {

        }

    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Override
    public void mayorNumProducto(String nombreArchivo) {
        
        int maxCantidadFruta = 0;
        int indice = 0;
        int i;
        List<Fruta> ArrayFrutas = new ArrayList<>();
        try {
            ArrayFrutas = this.datosFruta.listar(nombreArchivo);
            for (i = 0; i < ArrayFrutas.size(); i++) {
                if (ArrayFrutas.get(i).getCantidad() > maxCantidadFruta) {
                    maxCantidadFruta = ArrayFrutas.get(i).getCantidad();
                    //me tengo que guardar la pocision
                    indice = i;

                }

            }
            System.out.println("La fruta : " + ArrayFrutas.get(indice).getNombre() + " - tiene la mayor cantidad : " + maxCantidadFruta);
        } catch (LecturaFrutaExcep ex) {
            System.out.println("Error al bsucar la mayor cantidad de frutas");
        }

    }

    @Override
    public void borrarFruta(String nombreFruta, String nombreArchivo) {

        try {
            this.datosFruta.borrarFruta(nombreFruta, nombreArchivo);
        } catch (LecturaFrutaExcep ex) {
            System.out.println("Error al borrar fruta");
        }

    }


    @Override
    public void contadorFrutas(String nombreArchivo) {
      
      
        int numeroFrutas=0;
        List<Fruta> ArrayFrutas = new ArrayList<>();

        try {
            ArrayFrutas = this.datosFruta.listar(nombreArchivo);

            for (int i = 0; i < ArrayFrutas.size(); i++) {
             numeroFrutas = ArrayFrutas.size();
            
             
          }
            System.out.println("El numero total de frutas para vender es: "+ numeroFrutas);
        }catch (LecturaFrutaExcep ex) {
            System.out.println("Error al contar las frutas");
     }
    }

    @Override
    public void listarAscendente_Descendente(String nombreArchivo) {
        //Creamos el arrayList
        List <Fruta> listaFrutas = new ArrayList<>();
        try {
            //Vamos a añadir el listado de frutas, para ellos hay que llamar
            // a listar de la capa de datos. 
            listaFrutas = this.datosFruta.listar(nombreArchivo);
            System.out.println("\n\n\tLISTADO ASCENDENTE POR CANTIDAD DE FRUTA");
            Collections.sort(listaFrutas, new Comparator<Fruta>(){
             
           
                public int compare(Fruta p1, Fruta p2){
                    //aqui es donde se comparan los objetos segun la cantidad de fruta
                       return new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
                }
                
            });
                 System.out.println(""+listaFrutas);
          //*******************************************************************************************
          
            System.out.println("\n\n\tLISTADO DESCENDENTE POR CANTIDAD DE FRUTA");
            Collections.sort(listaFrutas, new Comparator<Fruta>(){
             
           
                public int compare(Fruta p2, Fruta p1){
                    return new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
                }
                
            });
                 System.out.println(""+listaFrutas);
        } catch (LecturaFrutaExcep ex) {
            
        }
       
          
    }

 
}



        
