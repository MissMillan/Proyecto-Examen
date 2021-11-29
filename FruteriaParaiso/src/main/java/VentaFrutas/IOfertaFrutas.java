/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package VentaFrutas;

import Dominio.Fruta;

/**
 *
 * @author milla
 */
public interface IOfertaFrutas {
    
    void agregarFruta(Fruta fruta, String nombreArchivo);
    void listarFruta(String nombreArchivo);
    void buscarFruta(String buscarF,String nombreArchivo);
    void iniciarTienda(String nombreArchivo);
     void calcularTotalprecio(String nombreArchivo);
    void borrarFruta(String nombreFruta,String nombreArchivo);
    void mayorNumProducto(  String nombreArchivo);
    void contadorFrutas(String nombreArchivo);
    void listarAscendente_Descendente(String nombreArchivo);
 
}
