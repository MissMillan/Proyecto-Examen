
package DatosCompra;

import DatosFruta.DatosF;
import DatosRegistro.DatosRegistroImple;
import Dominio.Compras;
import ExcepcionesCompra.AccesoComprasExcep;
import ExcepcionesCompra.EscrituraComprasExcep;
import ExcepcionesCompra.LecturaComprasExcep;
import ExcepcionesFruta.LecturaFrutaExcep;
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
public class DatosCompra implements IDatosCompra {

    @Override
    public boolean existeFichero(String nombreArchivo) throws AccesoComprasExcep {
              File archivo = new File(nombreArchivo);

              return archivo.exists();
    }


    @Override
    public List<Compras> listar(String nombreArchivo) throws LecturaComprasExcep {
            var archivo = new File(nombreArchivo);

       
        
         String[] frutaArray = new String[3];// aqui es donde estara el id, nombre, descripcion y fecha
      List <Compras> articulos = new ArrayList<>();
  
           

 
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
       articulos.add(new Compras(Integer.parseInt(frutaArray[0]),Integer.parseInt(frutaArray[1]),frutaArray[2])) ;
  

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
            System.out.println("Error de Lectura Listando las compras");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("Error de Lectura Listando las Compras");
        } 
        return articulos;
    }

    @Override
    public void borrarEnFichero(String nombreArchivo) throws AccesoComprasExcep {
      //1.-creamos el fichero
        File archivo = new File(nombreArchivo);
        //2.-declaramos el metodo delete
        try {
            archivo.delete();
        } catch (Exception e) {
            System.out.println("El archivo no se ha podido borrar");
        }
    }

    @Override
    public void crearEnFichero(String nombreArchivo) throws AccesoComprasExcep {
            //1.-creamos el fichero
        File archivo = new File(nombreArchivo);
        //2.-creamos el objeto que saldra del fichero
        PrintWriter salida = null;
        try {

            salida = new PrintWriter(new FileWriter(archivo));
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoComprasExcep("Error al Crear el archivo");
        } finally {
            salida.close();
        }
    }

    @Override
    public void escribirEnFichero(Compras obj, String nombreArchivo, boolean anexar) throws EscrituraComprasExcep {
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
    public void borrarCompra(int codCompra, String nombreArchivo) throws LecturaComprasExcep {
               File archivo = new File(nombreArchivo);
        ArrayList<String> ArrayFrutas = new ArrayList<>();

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            //anexar por que queremos añadir. 

            var lectura = entrada.readLine();
            while (lectura != null) {
                //estamos leyendo el archivo original
                //mira si es la linea que quiero borrar
                if (lectura.contains((codCompra))) {
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
            System.out.println("La compra se ha borrado con exito");

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }
    
}
