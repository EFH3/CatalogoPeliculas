package mx.com.gm.peliculas.presentacion;

import java.util.Scanner;
import mx.com.gm.peliculas.negocio.CatalogoPeliculasImpl;
import mx.com.gm.peliculas.negocio.ICatalogoPeliculas;

/**
 *
 * @author hever.fierro
 */
public class CatalogoPeliculasPresentacion {

    public static void main(String[] args) {
        var opcion = -1;
        Scanner consola = new Scanner(System.in);
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        /*Con esto -
        logramos el bajo acoplamiento y la alta cuestion. 
        Bajo Acoplamiento: Usamos la menor cantidad de relaciones entre las clases
        tal como la capa de datos, servicio(negocio) y presentacion
        
        Alta cuestion: Hacer q cada clase y paquete haga su chamba tal como-
        desarrollamos
        Cada capa tiene su propia responsabilidad.*/

        while (opcion != 0) {
            System.out.println("Elige la opcion requerida: \n"
                    + "1.-Iniciar catalogo peliculas\n"
                    + "2.-Agregar Pelicula\n"
                    + "3.-Listar Peliculas\n"
                    + "4.-Buscar Pelicula\n"
                    + "0.-Salir");
            
            try{
                opcion = Integer.parseInt(consola.nextLine());   
            } catch(RuntimeException ex){
                ex.printStackTrace(System.out);
                throw new NumberFormatException ("Por favor, introduzca un numero en consola. " + ex.getMessage());
            }

            switch (opcion) {
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.print("Introduce el nomnbre de una pelicula a agregar: ");
                    String nombrePelicula = consola.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Introduce el nombre de la pelicula a buscar: ");
                    var busqueda = consola.nextLine();
                    catalogo.buscarPelicula(busqueda);
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no reconocida");
                    break;
            }
        }
    }
}
