/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ComprasNegocio;

import Dominio.Compras;

/**
 *
 * @author milla
 */
public interface INegocioCompras {
      
    void agregarCompra(Compras compra, String nombreArchivo);
    void listarCompra(String nombreArchivo);
    void buscarCompra(int buscarCompra,String nombreArchivo);
    void iniciarTienda(String nombreArchivo);
    void borrarCompra(int codCompra,String nombreArchivo);
  
}
