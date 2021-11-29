/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;
import VentaFrutas.OfertaFrutas;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author milla
 */
public class Compras  {
   private int idCompra;
    private int codCliente;
    private String nombreCliente;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
    
    

    public Compras(int idCompra, int codCliente, String nombreCliente) {
        this.idCompra = idCompra;
        this.codCliente = codCliente;
        this.nombreCliente = nombreCliente;
    }
    
    
    
    
    

 public String imprimir(){
       
        return  this.idCompra+ "  ;  "  + this.codCliente + "   ;   "+ this.nombreCliente ;
    }

  
   
   
}
