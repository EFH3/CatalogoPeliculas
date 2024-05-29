package mx.com.gm.peliculas.excepciones;

//Clase padre de excepciones q extiende de la clase Exception
public class AccesoDatosEX extends Exception{
    public AccesoDatosEX (String mensaje){
        super(mensaje);
    }
}
