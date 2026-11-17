
package modelo;

public class Posicion {
    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna) {
        // Guardamos las coordenadas de una casilla para poder usarlas en la pila.
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        // Muestro las coordenadas de una forma más fácil de leer.
        return "(" + fila + ", " + columna + ")";
    }
}
