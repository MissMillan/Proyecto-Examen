/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosFruta;

import Dominio.ClasificacionFruta;
import Dominio.Fruta;
import ExcepcionesFruta.AccesoFrutasExcep;
import ExcepcionesFruta.EscrituraFrutasExcep;
import ExcepcionesFruta.LecturaFrutaExcep;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla
 */
public class DatosF implements IDatosF {

Fruta obj;


    //si el fichero existe me devolvera un booleano
    @Override
    public boolean existeFichero(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        return archivo.exists();
    }

    //-----------------------------------------------------------------------------
    @Override
    public String buscarFichero(String nombreArchivo, String buscar) throws LecturaFrutaExcep {
        //creamos un nuevo archivo
        var archivo = new File(nombreArchivo);
        var resultado = "";
        try {
            //entrada es el descriptor de lectura
            //Cuando se da la instrucción "leer" al objeto BufferedReader ,
            //utiliza el FileReader Objeto para leer los datos del archivo.
            var entrada = new BufferedReader(new FileReader(archivo));
            //nos devuelve una linea de nuestro archivo 
            var lectura = entrada.readLine();
            var i = 0;
            //mientras lectura sea distinto a no encontrar nada
            while (!lectura.equalsIgnoreCase(buscar)) {
                i++;
                // Avanzamos en la lectura
                lectura = entrada.readLine();
            }
            resultado = "\nLa fruta: " + buscar + ", está en la posición " + i;
            //esto es para salir del fichero
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return resultado;

    }

    //------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    @Override
    public void borrarEnFichero(String nombreArchivo) throws AccesoFrutasExcep {
        //1.-creamos el fichero
        File archivo = new File(nombreArchivo);
        //2.-declaramos el metodo delete
        try {
            archivo.delete();
        } catch (Exception e) {
            System.out.println("El archivo no se ha podido borrar");
        }

    }

    //----------------------------------------------------------------------------
    @Override
    public void crearEnFichero(String nombreArchivo) throws AccesoFrutasExcep {
        //1.-creamos el fichero
        File archivo = new File(nombreArchivo);
        //2.-creamos el objeto que saldra del fichero
        PrintWriter salida = null;
        try {

            salida = new PrintWriter(new FileWriter(archivo));
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoFrutasExcep("Error al Crear el archivo");
        } finally {
            salida.close();
        }
    }

    @Override
    public List<Fruta> listar(String nombreArchivo) throws LecturaFrutaExcep {
        var archivo = new File(nombreArchivo);
ClasificacionFruta enumerado = null;
       
        
         String[] frutaArray = new String[6];// aqui es donde estara el id, nombre, descripcion y fecha
      List <Fruta> articulos = new ArrayList<>();
  
           

 
        try {
            //entrada es el descriptor de lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            //nos devuelve una linea de nuestro archivo 
            var lectura = entrada.readLine(); // string - {nombre;cantidad;precio;fecha}
            while (lectura != null) {

                //Añado cada fruta a mi listado de frutas
               
              frutaArray = lectura.split(";");//-----> ahora aqui esta la informacion
            //esto es un array de string
            //parseInt recibe un string y lo convierte a entero
  articulos.add(new Fruta(Integer.parseInt(frutaArray[0]),frutaArray[1],Integer.parseInt(frutaArray[2]),
          Double.parseDouble(frutaArray[3]),frutaArray[4],enumerado.DULCES)) ;
  

                //ArrayFrutas.add(Frutainicio);
                // Avanzamos en la lectura
                lectura = entrada.readLine();
                // String array[] = lectura.split(";");

            }
            entrada.close();
            //----------------------------------------------------------    

            //-------------------------------------------------------
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("Error de Lectura Listando las Frutas");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("Error de Lectura Listando las Frutas");
        } 
        return articulos;
    }

    @Override
    public void borrarFruta(String nombreFruta, String nombreArchivo) throws LecturaFrutaExcep {
        File archivo = new File(nombreArchivo);
        ArrayList<String> ArrayFrutas = new ArrayList<>();

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            //anexar por que queremos añadir. 

            var lectura = entrada.readLine();
            while (lectura != null) {
                //estamos leyendo el archivo original
                //mira si es la linea que quiero borrar
                if (lectura.contains((nombreFruta))) {
                    // Avanzamos en la lectura
                    //si es la linea que quiero , la salta
                    lectura = entrada.readLine();
                    continue;//esto itera dentro del while

                }

                //nuevo array para guardar las frutas que si queremos
                ArrayFrutas.add(lectura);

                // String array[] = lectura.split(";");
                //cuando termine el while tiene que pasar a la siguiente
                //pasa arriba y lee la siguiente linea
                lectura = entrada.readLine();
            }

            //creamos dos escritores para el archivo
            var salida = new PrintWriter(new FileWriter(nombreArchivo, true));
            //borrara todo
            var borrador = new PrintWriter(new FileWriter(nombreArchivo, false));
            //esto deberia borrarlo todo
            //todo lo que hay en el archivo
            borrador.print("");
            //esto va a recorrer por cada fruta que exista, for of
            //por cada cosa que hemos guaradao l imprima
            for (String fruta : ArrayFrutas) {
                //si no lo encuentra lo sobreescribe
                salida.println(fruta);
            }

            //tenemos que cerrar lasd conexiones
            entrada.close();
            salida.close();

            this.listar(nombreArchivo);
            System.out.println("La fruta se ha borrado con exito");

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

    }
    
        
        
    @Override
    public void escribirEnFichero(Fruta obj, String nombreArchivo, boolean anexar) throws EscrituraFrutasExcep {
        File archivo = new File(nombreArchivo);
        try {
            if (archivo.exists()) {
                //necesitamos escribir sobre el archivo que ya esta creado
                var salida = new PrintWriter(new FileWriter(nombreArchivo, anexar));
                salida.println(obj.imprimir());
                salida.close();
            }

        } catch (IOException ex) {
            System.out.println("No se pudo escribir sobre el archivo");
        }

    }



 
}
