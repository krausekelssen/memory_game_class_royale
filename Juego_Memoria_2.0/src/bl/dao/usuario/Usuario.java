
package bl.dao.usuario;

import java.sql.Time;
import java.util.Objects;

public class Usuario {
    int posicion;
    String nombre;
    Time tiempo;

    public Usuario() {
    }

    public Usuario(int posicion, String nombre, Time tiempo) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getTiempo() {
        return tiempo;
    }

    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Usuario: " 
                + "Posición: " + posicion + "\n" 
                + "Nombre: " + nombre + "\n" 
                + "Tiempo: " + tiempo;
    }
    
    
}
