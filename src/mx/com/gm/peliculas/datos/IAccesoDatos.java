package mx.com.gm.peliculas.datos;

import java.util.List;
import mx.com.gm.peliculas.domain.*;
import mx.com.gm.peliculas.excepciones.*;
/**
 * @author hever.fierro
 * Interface para manejar datos, desde la creacion, lectura y escritura.
 **/
public interface IAccesoDatos {
    
    boolean existe(String nombreRecurso) throws AccesoDatosEX;
    
    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEX;
    
    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;
    
    String buscar (String nombreRecurso, String buscar) throws LecturaDatosEX;
    
    void crear(String nombreRecurso) throws AccesoDatosEX;
    
    void borrar(String nombreRecurso) throws AccesoDatosEX;
}
