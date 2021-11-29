/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosRegistro;

import Dominio.Cliente;
import ExcepcionesRegistroCliente.EscrituraExcepCliente;
import ExcepcionesRegistroCliente.ExcepCliente;
import ExcepcionesRegistroCliente.LecturaExcepCliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla
 */
public class DatosRegistroImple implements IDatosRegistro{

    @Override
    public boolean existeFichero(String nombreArchivo) throws ExcepCliente {
        File archivo = new File(nombreArchivo);
//me devuelve si el archivo existe
        return archivo.exists();
    }

    @Override
    public String buscarFichero(String nombreArchivo, String buscarCliente) throws LecturaExcepCliente {
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
            //mientras lectura sea disitnto a no encontrar nada
            while(!lectura.equalsIgnoreCase(buscarCliente)){
                i++;
                // Avanzamos en la lectura
                lectura = entrada.readLine();
            }
            resultado = "\nEl cliente: " + buscarCliente + ", está en la posición "+ i;
          //esto es para salir del fichero
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
        return resultado;
    }

    @Override
    public List<Cliente> listar(String nombreArchivo) throws LecturaExcepCliente {
          
  File archivo = new File(nombreArchivo);
        List<Cliente> lista = new ArrayList<Cliente>();
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            
            var lectura = entrada.readLine();
            
            while (lectura != null) {
                String[] product = lectura.split(";");
               Cliente producto = new Cliente( product[0], product[1] ,  product[2], product[3], product[4], product[5] );
                lista.add(producto);
                lectura = entrada.readLine();
            }
            entrada.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                throw new LecturaExcepCliente("Error de lectura listando las productos");
            } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return lista;
        
    }
    @Override
    public void borrarEnFichero(String nombreArchivo,String dniCliente) throws ExcepCliente {
       File archivo = new File(nombreArchivo);
        ArrayList<String> ArrayCliente = new ArrayList<>();

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            //anexar por que queremos añadir. 

            var lectura = entrada.readLine();
            while (lectura != null) {
                //estamos leyendo el archivo original
                //mira si es la linea que quiero borrar
                if (lectura.contains(dniCliente)) {
                    // Avanzamos en la lectura
                    //si es la linea que quiero , la salta
                    lectura = entrada.readLine();
                    continue;//esto itera dentro del while

                }

                //nuevo array para guardar las frutas que si queremos
               ArrayCliente.add(lectura);

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
            for (String c : ArrayCliente) {
                //si no lo encuentra lo sobreescribe
                salida.println(c);
            }

            //tenemos que cerrar las conexiones
            entrada.close();
            salida.close();

            this.listar(nombreArchivo);
            System.out.println("El cliente se ha borrado con exito");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatosRegistroImple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatosRegistroImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearEnFichero(String nombreArchivo) throws ExcepCliente {
      //1.-creamos el fichero
        File archivo = new File(nombreArchivo);
        //2.-creamos el objeto que saldra del fichero
        PrintWriter salida = null;
        try {

            salida = new PrintWriter(new FileWriter(archivo));
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepCliente("Error al Crear el archivo");
        } finally {
            salida.close();
        }
    }

    @Override
    public void escribirEnFichero(Cliente obj, String nombreArchivo, boolean anexar) throws EscrituraExcepCliente {
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

    @Override
    public void modificarUsuario(String dniCliente, String nombreArchivo,Cliente modificacion) {

     var archivo = new File(nombreArchivo);
        Cliente resultado ;
             List<Cliente> lista = new ArrayList<Cliente>();  
            //entrada es el descriptor de lectura
            //Cuando se da la instrucción "leer" al objeto BufferedReader ,
            //utiliza el FileReader Objeto para leer los datos del archivo.
        
        try {
           BufferedReader entrada;
            entrada = new BufferedReader(new FileReader(archivo));
              //nos devuelve una linea de nuestro archivo 
            var lectura = entrada.readLine();
            var i = 0;
            //mientras lectura sea disitnto a no encontrar nada
         
            if(archivo.exists() && lectura.equalsIgnoreCase(dniCliente)){
                  String[] product = lectura.split(";");
                  
               
             lista.add(new Cliente( product[0], product[1] ,  product[2], product[3], product[4], product[5]) );
            }
            System.out.println("El cliente se ha modificado con exito"); 
          //esto es para salir del fichero
           entrada.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatosRegistroImple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatosRegistroImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    
