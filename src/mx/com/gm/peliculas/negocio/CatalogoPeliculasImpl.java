package mx.com.gm.peliculas.negocio;

import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        try {
            boolean anexar = this.datos.existe(NOMBRE_RECURSO);
            this.datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEX ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            /*for (Pelicula pelicula: peliculas) {
                System.out.println(pelicula);
            } Ciclo forEach.*/
            peliculas.forEach(pelicula -> {
                System.out.println(pelicula);
            });
        } catch (AccesoDatosEX ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        try {
            String resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
            if (resultado != null) {
                System.out.println("resultado = " + resultado);
            } else {
                System.out.println("Sin resultados.\n");
            }
        } catch (AccesoDatosEX ex) {
            System.out.println("Error de acceso datos en el metodo buscarPelicula");
            //System.out.println(ex.getClass().getSimpleName());
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)) {
                this.datos.borrar(NOMBRE_RECURSO);
                this.datos.crear(NOMBRE_RECURSO);
            } else {
                this.datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEX ex) {
            /*Y aunque los demas metodos pueden arrojar
            excepciones simplemente al procesar la excepcion mas generica puede capturar cualquiera  de las excepciones :)*/
            System.out.println("Error de acceso a datos en el metodo iniciarCatalogoPeliculas()");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void borrarCatalogoPeliculas() {
        try {
            datos.borrar(NOMBRE_RECURSO);
        } catch (AccesoDatosEX ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }
}
