package modelo;

public class Laberinto {
    private final int filas;
    private final int columnas;
    private final char[][] celdas;

    public Laberinto(int filas, int columnas) {
        // se establecio un limite o rango en las dimensiones permitidas para el laberinto
        if (filas < 5 || columnas < 5 ||
            filas > 30 || columnas > 30) {
            throw new IllegalArgumentException(
                "Filas y columnas deben estar entre 5 y 30."
            );
        }

        this.filas = filas;
        this.columnas = columnas;
        // Se utiliza una sola matriz para guardar todas las casillas
        this.celdas = new char[filas][columnas];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public boolean esValida(int fila, int columna) {
        // se hace la comprobación de que la posición esté dentro de los límites
        return fila >= 0 && fila < filas &&
               columna >= 0 && columna < columnas;
    }

    public char obtener(int fila, int columna) {
        if (!esValida(fila, columna)) {
            throw new IllegalArgumentException(
                "Casilla fuera del laberinto"
            );
        }

        // se obtiene el contenido de la casilla después de validar sus coordenadas
        return celdas[fila][columna];
    }

    public void colocar(int fila, int columna, char simbolo) {
        if (!esValida(fila, columna)) {
            throw new IllegalArgumentException(
                "Casilla fuera del laberinto"
            );
        }

        celdas[fila][columna] = simbolo;
    }
    //los portales se pueden identificar por las letras que se guardaron en la matriz
    public boolean esPortal(char c) { return c >= 'a' && c <= 'i'; }
    public boolean transitable(int f, int c) {
        
        return esValida(f,c) && (obtener(f,c) & 0x7fff) != '#';
    }
    public char simbolo(int f, int c) {
        // se muestran los portales como P aunque internamente tengan otra letra
        char v = (char)(obtener(f,c) & 0x7fff);
        return esPortal(v) ? 'P' : v;
    }
}
