/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatosRegistro;

import Dominio.Cliente;
import ExcepcionesRegistroCliente.*;
import java.util.List;

/**
 *
 * @author milla
 */
public interface IDatosRegistro {
        //declaro los metodos 
    boolean existeFichero(String nombreArchivo)throws ExcepCliente;
    String buscarFichero(String nombreArchivo, String buscarCliente)throws LecturaExcepCliente;
    List<Cliente> listar(String nombreArchivo) throws  LecturaExcepCliente;
    void borrarEnFichero(String nombreArchivo,String dniCliente)throws ExcepCliente;
    void crearEnFichero(String nombreArchivo)throws ExcepCliente;
    void escribirEnFichero (Cliente obj, String nombreArchivo, boolean anexar)throws EscrituraExcepCliente;
    void modificarUsuario(String dniCliente,String nombreArchivo,Cliente modificacion);
 
}
