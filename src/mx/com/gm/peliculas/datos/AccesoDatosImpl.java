package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEX {
        var archivo = new File(nombreArchivo);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lecturaLinea = entrada.readLine();
            while (lecturaLinea != null) {
                Pelicula pelicula = new Pelicula(lecturaLinea);
                peliculas.add(pelicula);
                lecturaLinea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEX("Excepcion al listar peliculas (BufferedReader) " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEX("Excepcion al listar peliculas (readLine) " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //salida.println(pelicula.toString());
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito la informacion al archivo "+ pelicula);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Excepcion al escribir peliculas:"+ ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEX {
        var archivo = new File(nombreArchivo);
        String busquedaPel = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lecturaLinea = entrada.readLine();
            var indice = 1;
            while(lecturaLinea != null){
                if(buscar != null && buscar.equalsIgnoreCase(lecturaLinea)){
                    busquedaPel = "Se encontro la pelicula " + lecturaLinea + " en el indice "+ indice;
                    break;
                }
                lecturaLinea = entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEX("Excepcion al buscar peliculas (BufferedReader) " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEX("Excepcion al buscar peliculas (readLine) " + ex.getMessage());
        }
        return busquedaPel;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEX {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosEX("Excepcion al crear el archivo" + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) {
        var archivo = new File(nombreArchivo);
        if (archivo.exists())
            archivo.delete();
        System.out.println("Se ha borrado el archivo.");
    }
}
