/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RegistroClientes;

import Dominio.Cliente;

/**
 *
 * @author milla
 */
public interface IRegistro {
        void iniciarRegistro(String nombreArchivo);
        void agregarUsuario(Cliente usuario, String nombreArchivo);
        void buscarUsuario(String buscarCliente,String nombreArchivo);
        void modificarUsuario(String dniCliente,String nombreArchivo, Cliente modificado);
        void listarUsuario(String nombreArchivo);
        void borrarUsuario(String dniCliente,String nombreArchivo);
        void listarAscendente_Descendente(String nombreArchivo);
}
