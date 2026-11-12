package modelo;

public class Laberinto {
    private final int filas;
    private final int columnas;
    private final char[][] celdas;

    public Laberinto(int filas, int columnas) {
        if (filas < 5 || columnas < 5 ||
            filas > 30 || columnas > 30) {
            throw new IllegalArgumentException(
                "Filas y columnas deben estar entre 5 y 30."
            );
        }

        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new char[filas][columnas];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public boolean esValida(int fila, int columna) {
        return fila >= 0 && fila < filas &&
               columna >= 0 && columna < columnas;
    }

    public char obtener(int fila, int columna) {
        if (!esValida(fila, columna)) {
            throw new IllegalArgumentException(
                "Casilla fuera del laberinto"
            );
        }

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
}