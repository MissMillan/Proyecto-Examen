/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatosCompra;

import Dominio.Compras;
import ExcepcionesCompra.*;
import java.util.List;

/**
 *
 * @author milla
 */
public interface IDatosCompra {
        
    //declaro los metodos 
    boolean existeFichero(String nombreArchivo)throws AccesoComprasExcep;
    List<Compras> listar(String nombreArchivo) throws  LecturaComprasExcep;
    void borrarEnFichero(String nombreArchivo)throws AccesoComprasExcep;
    void crearEnFichero(String nombreArchivo)throws AccesoComprasExcep;
    void escribirEnFichero (Compras obj, String nombreArchivo, boolean anexar)throws EscrituraComprasExcep;
     void borrarCompra(int CodCompra, String nombreArchivo)throws  LecturaComprasExcep;
}
