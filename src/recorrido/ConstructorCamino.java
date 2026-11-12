package recorrido;

import java.util.Random;
import java.util.Stack;
import modelo.Laberinto;
import modelo.Posicion;

public class ConstructorCamino {

    public Stack<Posicion> construir(
            Laberinto laberinto, long semilla) {

        Stack<Posicion> camino = new Stack<>();

        Random azar = new Random(
            semilla ^ 0x1E3A2B7CL
        );

        agregar(laberinto, camino, 0, 0);

        int diagonalFinal = Math.min(
            laberinto.getFilas(),
            laberinto.getColumnas()
        ) - 1;

        avanzarDiagonal(
            laberinto, camino, azar,
            0, 0, diagonalFinal
        );

        laberinto.colocar(0, 0, 'S');

        laberinto.colocar(
            laberinto.getFilas() - 1,
            laberinto.getColumnas() - 1,
            'F'
        );

        return camino;
    }

    private void avanzarDiagonal(
            Laberinto l,
            Stack<Posicion> camino,
            Random azar,
            int fila,
            int columna,
            int limite) {

        if (fila == l.getFilas() - 1 &&
            columna == l.getColumnas() - 1) {
            return;
        }

        if (fila == columna && fila < limite) {

            if (l.obtener(fila, columna) == 'P') {

                agregar(
                    l, camino,
                    fila + 1, columna + 1
                );

            } else {

                if (azar.nextBoolean()) {
                    agregar(
                        l, camino,
                        fila, columna + 1
                    );
                } else {
                    agregar(
                        l, camino,
                        fila + 1, columna
                    );
                }

                agregar(
                    l, camino,
                    fila + 1, columna + 1
                );
            }

            avanzarDiagonal(
                l, camino, azar,
                fila + 1, columna + 1,
                limite
            );

            return;
        }

        boolean bajar =
            fila < l.getFilas() - 1 &&
            (columna == l.getColumnas() - 1 ||
             azar.nextBoolean());

        int nuevaFila = bajar ? fila + 1 : fila;

        int nuevaColumna = bajar
            ? columna : columna + 1;

        agregar(
            l, camino,
            nuevaFila, nuevaColumna
        );

        avanzarDiagonal(
            l, camino, azar,
            nuevaFila, nuevaColumna, limite
        );
    }

    private void agregar(
            Laberinto l,
            Stack<Posicion> camino,
            int fila,
            int columna) {

        if (l.obtener(fila, columna) != 'P') {
            l.colocar(fila, columna, '.');
        }

        camino.push(new Posicion(fila, columna));
    }
}
