/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author milla
 */
public class Fruta {
    //atributos
    private int codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String fecha;
    private ClasificacionFruta tipo;
    //------------------------------------------------------------------------
    //constructores
    public Fruta() {


    }

    public Fruta(int codigo) {
        this.codigo = codigo;
    }
    
    public String imprimir(){
        
        return  this.codigo+ ";" + this.nombre + ";" + this.cantidad + ";"+this.precio + ";" + this.fecha+ ";"+this.tipo;
    }
    
  
  public String getfecha_temporada_toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.format(fecha);
    }

    public Fruta(int codigo, String nombre, int cantidad, double precio, String fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Fruta(int codigo, String nombre, int cantidad, double precio, String fecha, ClasificacionFruta tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
        this.tipo = tipo;
    }
    


    //---------------------------------------------------------------------------
        public int getCodigo() {    
        return codigo;
    }
    public void setCodigo(int codigo) {    
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ClasificacionFruta getTipo() {
        return tipo;
    }

    public void setTipo(ClasificacionFruta tipo) {
        this.tipo = tipo;
    }


  

  

 
//-----------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Fruta{" + "codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", fecha=" + fecha + ", tipo=" + tipo + '}';
    }

 




   


}
