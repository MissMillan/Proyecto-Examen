
package Test;


import ComprasNegocio.INegocioCompras;
import ComprasNegocio.NegocioComprasImple;
import DatosFruta.FrutaDAO;
import DatosRegistro.RegistroClientesDAO;
import Dominio.ClasificacionFruta;
import Dominio.Cliente;
import Dominio.Compras;
import Dominio.Fruta;
import RegistroClientes.IRegistro;
import RegistroClientes.RegistroImple;
import VentaFrutas.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author milla
 */
public class Test {
static  Connection conexion = null;
static String fichero1 = "Productos.txt";  
static String fichero2 = "Clientes.txt";
static String fichero3 = "Carrito.txt";
static  IRegistro registro = new RegistroImple();
static  IOfertaFrutas Tienda = new OfertaFrutas();
static  RegistroClientesDAO nuevoCliente = new   RegistroClientesDAO();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //declaramos un array
  
   
        
        menu();

    }

    public static void menu() throws SQLException {
 
 Scanner sn = new Scanner(System.in); 
    
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        

            do {
                System.out.println("");
                System.out.println("\tBienvenido a Fruteria Paraiso");
                System.out.println("\t--------------------------------");       
                System.out.println("\t1. Registrarme como cliente ");  
                System.out.println("\t2. Area de clientes");
                System.out.println("\t3. Area de  productos");
                System.out.println("\t4. Area de compra");
                System.out.println("\t5. Salir");
                System.out.println("Escribe una de las opciones : ");
                opcion = sn.nextInt();
                switch (opcion) {
                  
                    case 1:
                        sn.nextLine();
                        RegistroUsuario();
                  
                        break;
                    case 2:
                      areaClientes();
                        break;
                    case 3:
                  
                        productosFruta();
                        sn.nextLine();
                        break;
                    case 4:
                        agregarCompra();
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } while (opcion <6);
   
              }
        

       
    
    public static void RegistroUsuario(){
    Scanner ent = new Scanner(System.in);
    RegistroClientesDAO nuevoCliente = new   RegistroClientesDAO();
        IRegistro registro = new RegistroImple();
        
       String fichero2 = "Clientes.txt";
        String dni = "";
        String nombreCliente = "";
        String apel1="";
        String apel2="";
        String telefono="";
        String correo = "";
        boolean salir = false;
        int opcion =0; //Guardaremos la opcion del usuario
        try {
            
         do{
        System.out.println("\tInicie su registro de cliente con nosotros: "); 
        System.out.println("\t*****************************************");
        System.out.println("1.- Iniciar Fichero de registro: ");
        System.out.println("2.- Introducir datos personales: ");
        System.out.println("3.- Area de clientes");
        System.out.println("4.- salir");
        System.out.println("Escribe una de las opciones : ");
        opcion =Integer.parseInt( ent.nextLine());
                switch (opcion) {       
                    
                    case 1:       
                        registro.iniciarRegistro(fichero2);
                    break;
                    
                    case 2:
                        System.out.println("Escriba su Codigo de Cliente : ");
                        dni = ent.nextLine();    
                       ent.nextLine();   
                        System.out.println("Escriba su nombre : ");
                        nombreCliente =ent.nextLine();

                        System.out.println("Escriba su primer apellido : ");
                         apel1 = ent.nextLine();

                        System.out.println("Escriba su segundo apellido : ");
                        apel2= ent.nextLine();
                        
                       System.out.println("Escriba su correo : ");
                        correo = ent.nextLine();
                        
                        System.out.println("Escriba su telefono : ");
                        telefono = ent.nextLine();
                   registro.agregarUsuario(new Cliente(dni,nombreCliente,apel1,apel2,correo,telefono), fichero2); 
                   Cliente product = new Cliente(dni,nombreCliente,apel1,apel2,correo,telefono);
                  nuevoCliente.insertar(product);
                    case 3:
                        
                        areaClientes() ;
                        break;
            
                  
                }
                
         } while(opcion <4) ;
         ent.nextLine();
        } catch (Exception e) {
              System.out.println("Debe introducir un numero");
              e.printStackTrace(System.out);
        }
       
        
    }
    public static void areaClientes() throws SQLException{

    Scanner datos = new Scanner(System.in);
      
     
        int opcion2;     
        String nombreCliente = "";
        String apel1="";
        String apel2="";
        String telefono="";
        String correo = "";
        
        do{
            
        
         System.out.println("\tArea de clientes : "); 
        System.out.println("\t****************");
        System.out.println("1.- Listar Clientes ");
        System.out.println("2.- Buscar Usuario ");   
        System.out.println("3.- Borrar Usuario");
        System.out.println("4.- Actualizar la informacion ");
        System.out.println("5.- Listar Ascendente/Descendente por NOMBRE USUARIO");
        System.out.println("6.- salir");
        System.out.println("Escribe una de las opciones : ");
        opcion2 =   Integer.parseInt(datos.nextLine());
  
        switch(opcion2){
                case 1:
                         registro.listarUsuario(fichero2);
                         System.out.println("\n");
                 break;
               case 2:
                 
                        System.out.println("Escriba el nombre cliente que quiere buscar: ");                        
                        String nombreC = datos.nextLine();
                        registro.buscarUsuario(nombreC, fichero2);
                        break;
              
                case 3:
                    datos.nextLine();
                     // registro.listarUsuario(fichero2);
                    System.out.println("Escriba el codigo del cliente  que quiere borrar de nuestro fichero: ");
                    String dniClient =    datos.nextLine();
                    registro.borrarUsuario(dniClient, fichero2);;
                    System.out.println("Desea ser borrado de nuestra base de datos ?");
                    System.out.println("\t1. Si, definitivamente");
                    System.out.println("\t2. No, regresar al Area de clientes");
                    System.out.println("\t Elija una opcion: ");
                  int  opcion3 =   Integer.parseInt(datos.nextLine());
                    switch(opcion3){
                        case 1:
                       System.out.println("Escriba su Codigo de Cliente : ");
                       dniClient  = datos.nextLine();    
                        datos.nextLine();   
                        System.out.println("Escriba su nombre : ");
                        nombreCliente = datos.nextLine();

                        System.out.println("Escriba su primer apellido : ");
                         apel1 = datos.nextLine();

                        System.out.println("Escriba su segundo apellido : ");
                        apel2= datos.nextLine();
                        
                       System.out.println("Escriba su correo : ");
                        correo = datos.nextLine();
                        
                        System.out.println("Escriba su telefono : ");
                        telefono = datos.nextLine();
                                 Cliente product = new Cliente(dniClient,nombreCliente,apel1,apel2,correo,telefono);
                                 nuevoCliente.borrar(product);
                            break; 
                        case 2: 
                            areaClientes();
                            break;
                    }
                
               
                  break;
                case 4:
                  List<Cliente> arrayProductos = nuevoCliente.seleccionar();

        arrayProductos.forEach(p -> {
            System.out.print( p);
        });
                 
                     System.out.println("Desea modificar la informacion: ");
        System.out.println("\nIntroduzca el nuevo codigo del cliente");
         dniClient  = datos.nextLine();   
        datos.nextLine();  
        System.out.println("Introduzca el nuevo nombre del cliente: ");
         nombreCliente = datos.nextLine();
        System.out.println("Introduzca el nuevo primer apellido del cliente: ");
        apel1 = datos.nextLine();
        System.out.println("Introduzca el nuevo segundo apellido del cliente");
        apel2= datos.nextLine();
        System.out.println("Escriba el nuevo correo del cliente: ");
        correo = datos.nextLine();
                        
       System.out.println("Escriba el nuevo  telefono del cliente: ");
        telefono = datos.nextLine();               
        
        System.out.println("¡Informacion actualizada con exito¡");
        int registros =   nuevoCliente.actualizar(new Cliente(dniClient,nombreCliente,apel1,apel2,correo,telefono));
          nuevoCliente.seleccionar();

          List<Cliente> arrayC  = nuevoCliente.seleccionar();

        arrayC.forEach(p -> {
            System.out.print( p);
        });
                        
                        break;
                 case 5:
                          registro.listarUsuario(fichero2);
                          registro.listarAscendente_Descendente(fichero2);
                        break;
                 
                
            
                }
            }while(opcion2<6);
         }
    
    
  
 
    
    
    //------------------------------------------------------------------------------
      public static void productosFruta() throws SQLException{
  
       
       IOfertaFrutas oferta = new OfertaFrutas();
       Scanner teclas = new Scanner(System.in);
     FrutaDAO nuevaFruta = new   FrutaDAO();
           var nombreF = "";
        int codigoF=0;
        int cantidadF = 0;
        double precioF = 0;
        String tipoF = "";
        String fechaF = "";
       int opcion4 =0;
       
       do{
        
          System.out.println("\tArea de Productos");
          System.out.println("\t=================");
          System.out.println("1. Iniciar Tienda");
          System.out.println("2. Agregar producto");
          System.out.println("3. Listar producto");
          System.out.println("4. Borrar producto");
          System.out.println("5. Buscar producto");
          System.out.println("6. Listar cantidad - producto ascen/descen"); 
          System.out.println("7. Salir");
           System.out.println("\tElija una opcion: ");
          opcion4= teclas.nextInt();
          
          switch(opcion4){
              
              case 1:
                  oferta.iniciarTienda(fichero1);
                  break;
              case 2:
                    // oferta.listarFruta(fichero1);
                    //ClasificacionFruta enumFruta = tipoFruta(); 
       
                 oferta.agregarFruta(new Fruta(1, "piña", 10, 2.5, ("09/08/2020"), ClasificacionFruta.ACIDAS), fichero1);
                  oferta.agregarFruta(new Fruta(2, "melocoton", 15, 5.7, ("06/09/2020"), ClasificacionFruta.DULCES), fichero1);
                  oferta.agregarFruta(new Fruta(3, "coco", 17,8.9, ("02/02/2020"), ClasificacionFruta.NEUTRAS), fichero1);
                  oferta.agregarFruta(new Fruta(4, "fresa", 19, 3.5, ("05/08/2020"), ClasificacionFruta.SEMIDULCES), fichero1);
                  oferta.agregarFruta(new Fruta(5, "mora", 7, 6.5, ("01/01/2020"), ClasificacionFruta.SEMIACIDAS), fichero1);
                  oferta.agregarFruta(new Fruta(6, "frambuesa", 18, 2.4, ("02/06/2020"), ClasificacionFruta.SEMIDULCES), fichero1);
                  oferta.agregarFruta(new Fruta(7, "platano", 23, 5.5, ("03/03/2020"), ClasificacionFruta.DULCES), fichero1);
                  
//                    Fruta product = new Fruta(1, "piña", 10, 2.5, ("09/08/2020"), ClasificacionFruta.ACIDAS);
//                    nuevaFruta.insertar(product);
                         teclas.nextLine();
                  break;
              case 3: 
                   oferta.listarFruta(fichero1);
                  break;
              case 4:
                   teclas.nextLine();
                    oferta.listarFruta(fichero1);
                   System.out.println("Escriba el nombre de la fruta que quiere borrar : ");
                   String nameF = teclas.nextLine();
                  oferta.borrarFruta( nameF,fichero1);
                  break;
              case 5:
                      teclas.nextLine();
                    System.out.println("Escriba el nombre de la fruta que quiere buscar : ");
                   String name = teclas.nextLine();
                   oferta.buscarFruta(name,fichero1);
                  break;
              case 6:
                    
                  oferta.listarAscendente_Descendente(fichero1);
                  break;
          }

       }while(opcion4 <7);
          
      }  
         //--------------------------------------------------------------------------
  
 //----------------------------------------------------------------------------------------------------------------
         public static ClasificacionFruta tipoFruta(){
        ClasificacionFruta tipo = null ;
        Scanner entrada = new Scanner(System.in);
          int salir = 6;
          int opcion5=0;
           
       
      

  
        /*Bucle que permite repetir el menu*/
        do{
            System.out.println("1.- Frutas Acidas");
            System.out.println("2.- Frutas Semiacidas");
            System.out.println("3.- Frutas Dulces");
            System.out.println("4.- Frutas semidulces");
            System.out.println("5.- Frutas Neutras");
            System.out.println("6.- Salir");
            System.out.print("Ingrese una opcion: ");
             opcion5 = entrada.nextInt();
            
            /*Switch con cada uno de los casos para las diferentes opciones del menu*/
            switch (opcion5) {
                case 1:
                    
                   return tipo.ACIDAS;
                 
                case 2:
                  return tipo.SEMIACIDAS;
              
                case 3:
                      return tipo.DULCES;
                case 4:
                      return tipo.SEMIDULCES;
                      
                case 5:
                      return tipo.NEUTRAS;       
                default:
                    System.out.print("Opcion no valida!");
            }
        }while (opcion5<6) ;

       return tipo;
    }
    

    
   //---------------------------------------------------------- 
    public static void agregarCompra() throws SQLException{
       
         //  String fichero3 = "Carrito.txt";
        Scanner ingreso = new Scanner(System.in);
        var nombreF = "";
        int codigoF=0;
        int cantidadF = 0;
        double precioF = 0;
        String tipoF = "";
        String fechaF = "";
        int opcion6=0;

        
        do{
            
   
        System.out.println("\n");
        System.out.println("\tDesea finalizar su compra: ");
        System.out.println("\t========================");
        System.out.println("1. Iniciar fichero de compra");
        System.out.println("2. Agregar a la compra ");
        System.out.println("3. Listar la compra");
        System.out.println("4. Borrar un producto de tu compra final");
        System.out.println("5. ¿Cuantas frutas te llevas en total?");
        System.out.println("6. ¿Que fruta tiene la mayor cantidad?");
        System.out.println("7. Visualizar el total a pagar ");
        System.out.println("8. Salir");
        System.out.println("Escribe una de las opciones : ");
        opcion6 = Integer.parseInt(ingreso.nextLine());
        
        switch(opcion6){
            case 1:
                Tienda.iniciarTienda(fichero3);
                break;
                
            case 2:
                       Tienda.listarFruta(fichero1);
                        System.out.println("\nEscriba el codigo de la fruta que quiere : ");
                        codigoF = ingreso.nextInt();
                           ingreso.nextLine();
                                     
                        System.out.println("Escriba el nombre de la fruta que quiere agregar: ");
                        nombreF = ingreso.nextLine();

                        System.out.println("Escriba la cantidad de fruta a agregar: ");
                        cantidadF = ingreso.nextInt();

                        System.out.println("Escriba el precio de fruta a agregar: ");
                        precioF =ingreso.nextDouble();
                       ingreso.nextLine();
                        System.out.println("Escriba la fecha en la que agrego la fruta (dd/MM/yyyy) : ");
                        fechaF = ingreso.nextLine();
                        System.out.println("\t Tipos de frutas a escoger en nuestra tienda");  
                        System.out.println("\t--------------------------------");
                         ClasificacionFruta enumFruta = tipoFruta(); 
               Tienda.agregarFruta(new Fruta(codigoF,nombreF,cantidadF,precioF,(fechaF),enumFruta), fichero3);
                 System.out.println("¡Compra agregada con exito!");
                break;
            case 3: 
                
                Tienda.listarFruta(fichero3);
                break;
       
            case 4: 
                System.out.println("Escriba el nombre de la fruta que quiere borrar");
               var nomeF = ingreso.nextLine();
                Tienda.borrarFruta(nomeF, fichero3);
                break;
            case 5:
                Tienda.contadorFrutas(fichero3);
                break;
            case 6: 
                Tienda.mayorNumProducto(fichero3);
                break;
            case 7: 
                 compraFinal();
             //   Tienda.calcularTotalprecio(fichero3);
                 
                break;
        }
     
        
        }while(opcion6 < 8);
    }
    
    //-------------------------------------------------------------------------------------
      public static void compraFinal() throws SQLException {
             INegocioCompras Final = new NegocioComprasImple();
             String fichero4 = "compraFinal.txt";
            Scanner f = new Scanner(System.in);
            int codFinal=0;
            String codCliente="";
            String nombreCliente="";
            
            int opcion8 =0;
            do{
        System.out.println("\n");
        System.out.println("\tFACTURA: ");
        System.out.println("\t=======");
        System.out.println("1. Iniciar fichero de facturas");
        System.out.println("2. Agregar a la factura final");
        System.out.println("3. Listar las facturas finales");
        System.out.println("4. Borrar una factura");
        System.out.println("5. Buscar una factura");
        System.out.println("6. Salir");
        System.out.println("Escribe una de las opciones : ");
        opcion8 = Integer.parseInt(f.nextLine());
                
        
        
        switch(opcion8){
            
            case 1:
                Final.iniciarTienda(fichero4);
                break;
                
            case 2:
                System.out.println("Agrege el Id de su compra: ");
                codFinal = f.nextInt();
       List<Cliente> arrayProductos = nuevoCliente.seleccionar();

        arrayProductos.forEach(p -> {
            System.out.print( p);
        });
                System.out.println("\nEscriba su Codigo de Cliente : ");
              int dniClient  = f.nextInt();  
              f.nextLine();
               System.out.println("Escriba el nombre del cliente : ");
               String nombreCLient  = f.nextLine();   
               
               Final.agregarCompra(new Compras(codFinal,dniClient,nombreCLient),fichero4);
                Tienda.calcularTotalprecio(fichero3);
                break;
            case 3:
                Final.listarCompra(fichero4);
                break;
            case 4:
                break;
            case 5:
                break;
        }
            }while(opcion8 >5);
      }
        
    
    

}
