/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatosFruta;

import Dominio.Fruta;
import ExcepcionesFruta.*;
import java.util.List;

import java.util.TreeMap;

/**
 *
 * @author milla
 */
public interface IDatosF {
    
    //declaro los metodos 
    boolean existeFichero(String nombreArchivo)throws AccesoFrutasExcep;
    String buscarFichero(String nombreArchivo, String buscar)throws LecturaFrutaExcep;
    List<Fruta> listar(String nombreArchivo) throws  LecturaFrutaExcep;
    void borrarEnFichero(String nombreArchivo)throws AccesoFrutasExcep;
    void crearEnFichero(String nombreArchivo)throws AccesoFrutasExcep;
    void escribirEnFichero (Fruta obj, String nombreArchivo, boolean anexar)throws EscrituraFrutasExcep;
    void borrarFruta(String nombreFruta, String nombreArchivo)throws  LecturaFrutaExcep;


}
