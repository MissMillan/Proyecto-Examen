/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author milla
 */
public class Cliente {
    private String cod;
    protected String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String telefono;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
        public String imprimir(){
        
        return  this.cod+ ";" + this.nombre + ";" + this.apellido1 + ";"+this.apellido2 + ";" + this.correo+ ";"+ this.telefono;
    }

    @Override
    public String toString() {
        return "  Cliente : " + " cod =" + cod + " nombre = " + nombre + ", apellido1 = " + apellido1 + " "
                + "apellido2 = " + apellido2 + ", correo = " + correo + ", telefono = " + telefono + " --- " + "\n";
    }

    public Cliente(String cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

  
    
    

    public Cliente(String cod, String nombre, String apellido1, String apellido2, String correo, String telefono) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
